package com.yaskoam.dictionary;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * @author Q-YAA
 */
public class IntonationDictionary {

    Map<String, String> dictionaryMap;

    public IntonationDictionary(String dictionaryFilePath) {
        dictionaryMap = DictionaryUtils.read(dictionaryFilePath);
    }

    public List<String> getAvailableIntonationSymbols() {
        return Lists.newArrayList(dictionaryMap.keySet());
    }

    public String getIntonationReplacement(String intonationSymbol) {
        return dictionaryMap.get(intonationSymbol);
    }
}
