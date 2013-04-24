package com.yaskoam.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.core.Is.is;

/**
 * @author Q-YAA
 */
public class PhoneticDictionary {

    private Map<LetterContext, String> dictionaryMap = new HashMap<LetterContext, String>();

    private List<LetterContext> availableContexts = new ArrayList<LetterContext>();

    public PhoneticDictionary(String dictionaryFilePath) {
        createDictionaryMap(DictionaryUtils.read(dictionaryFilePath));
    }

    public String getMoreSuitablePhoneme(String letter, String leftLetter, String rightLetter) {
        LetterContext moreSuitableContext = findMoreSuitableContext(letter, leftLetter, rightLetter);
        return dictionaryMap.get(moreSuitableContext);
    }

    private void createDictionaryMap(Map<String, String> map) {

        for (String key : map.keySet()) {
            LetterContext context = new LetterContext(key);
            dictionaryMap.put(context, map.get(key));
            availableContexts.add(context);
        }
    }

    private LetterContext findMoreSuitableContext(String letter, String leftLetter, String rightLetter) {
        List<LetterContext> letterContexts = findContextsForLetter(letter);

        LetterContext resultLetterContext = getSutableContext(letterContexts, leftLetter, rightLetter);
        if (resultLetterContext != null) {
            return resultLetterContext;
        }

        resultLetterContext = getSutableContext(letterContexts, leftLetter, ".");
        if (resultLetterContext != null) {
            return resultLetterContext;
        }

        resultLetterContext = getSutableContext(letterContexts, ".", rightLetter);
        if (resultLetterContext != null) {
            return resultLetterContext;
        }


        resultLetterContext = getSutableContext(letterContexts, ".", ".");
        if (resultLetterContext != null) {
            return resultLetterContext;
        }

        return null;
    }

    private List<LetterContext> findContextsForLetter(String letter) {
        return filter(having(on(LetterContext.class).isContextForLetter(letter), is(true)), availableContexts);
    }

    private LetterContext getSutableContext(List<LetterContext> letterContexts, String leftLetter, String rightLetter) {

        for (LetterContext currentLetterContext : letterContexts) {

            if (currentLetterContext.getLeftContext().contains(leftLetter)
                && currentLetterContext.getRightContext().contains(rightLetter)) {

                return currentLetterContext;
            }
        }

        return null;
    }
}
