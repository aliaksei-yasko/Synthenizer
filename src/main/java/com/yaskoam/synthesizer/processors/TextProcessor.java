package com.yaskoam.synthesizer.processors;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.yaskoam.dictionary.StressDictionary;

import static ch.lambdaj.Lambda.join;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

/**
 * @author Q-YAA
 */
public class TextProcessor {

    private StressDictionary stressDictionary;

    public TextProcessor(StressDictionary stressDictionary) {
        this.stressDictionary = stressDictionary;
    }

    public String process(String text) {
        List<String> stressWords = transform(newArrayList(text.split(" ")), new StressTransformFunction());
        return join(stressWords, " ");
    }

    private class StressTransformFunction implements Function<String, String> {

        @Override
        public String apply(@Nullable String input) {
            String wordWithStress = stressDictionary.getWordWithStress(input.trim());
            return wordWithStress != null ? wordWithStress : input.trim();
        }

        @Override
        public boolean equals(@Nullable Object object) {
            return equals(object);
        }
    }

}
