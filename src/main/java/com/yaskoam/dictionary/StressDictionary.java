package com.yaskoam.dictionary;

import java.util.Map;

/**
 * @author Q-YAA
 */
public class StressDictionary {

    private Map<String, String> dictionaryMap;

    public StressDictionary(String dictionaryFilePath) {
        dictionaryMap = DictionaryUtils.read(dictionaryFilePath);
    }

    public String getWordWithStress(String word) {
        return dictionaryMap.get(word.toLowerCase());
    }
}
