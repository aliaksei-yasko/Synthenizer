package com.yaskoam.synthesizer.processors;

import java.util.List;

import com.yaskoam.dictionary.IntonationDictionary;

/**
 * @author Q-YAA
 */
public class IntonationProcessor {

    private IntonationDictionary intonationDictionary;

    public IntonationProcessor(IntonationDictionary intonationDictionary) {
        this.intonationDictionary = intonationDictionary;
    }

    public String process(String text) {
        text = text.replace("«", "").replace("»", "");

        List<String> intonationSymbols = intonationDictionary.getAvailableIntonationSymbols();

        for (String intonationSymbol : intonationSymbols) {
            text = text.replace(intonationSymbol, " " + intonationDictionary.getIntonationReplacement(intonationSymbol) + " ");
        }

        return text;
    }
}
