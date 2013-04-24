package com.yaskoam.synthesizer.processors;

import com.yaskoam.dictionary.PhoneticDictionary;

/**
 * @author Q-YAA
 */
public class PhoneticProcessor {

    private PhoneticDictionary phoneticDictionary;

    public PhoneticProcessor(PhoneticDictionary phoneticDictionary) {
        this.phoneticDictionary = phoneticDictionary;
    }

    public String process(String text) {
        text = " " + text + " ";

        char[] letters = text.toCharArray();

        StringBuilder phonemeText = new StringBuilder();

        for (int i = 1; i < letters.length - 1; i++) {
            String letter = Character.toString(letters[i]);
            String leftLetter = Character.toString(letters[i - 1]);
            String rightLetter = Character.toString(letters[i + 1]);

            String moreSuitablePhoneme = phoneticDictionary.getMoreSuitablePhoneme(letter, leftLetter, rightLetter);

            if (moreSuitablePhoneme != null) {
                phonemeText.append(moreSuitablePhoneme);
                phonemeText.append(",");
            }
        }

        return phonemeText.deleteCharAt(phonemeText.length() - 1).toString();
    }

}
