package com.yaskoam.synthesizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.yaskoam.dictionary.IntonationDictionary;
import com.yaskoam.dictionary.PhoneticDictionary;
import com.yaskoam.dictionary.StressDictionary;
import com.yaskoam.sound.WavFile;
import com.yaskoam.sound.WavUtils;
import com.yaskoam.synthesizer.processors.AcousticProcessor;
import com.yaskoam.synthesizer.processors.IntonationProcessor;
import com.yaskoam.synthesizer.processors.PhoneticProcessor;
import com.yaskoam.synthesizer.processors.TextProcessor;

/**
 * @author Q-YAA
 */
public class Synthesizer {

    private static final String INTONATION_DICTIONARY_FILE_NAME = "base/dictionary/intonationDictionary.txt";

    private static final String STRESS_DICTIONARY_FILE_NAME = "base/dictionary/stressDictionary.txt";

    private static final String PHONETIC_DICTIONARY_FILE_NAME = "base/dictionary/phoneticDictionary.txt";

    private static final String ALLOPHONE_BASE_DIR_NAME = "base/allophoneBase";

    public static void main(String[] args) {

        //String textFileName = args[0];
        //String resultFileName = args[1];

        String textFileName = "text/text.txt";
        String resultFileName = "result.wav";

        IntonationDictionary intonationDictionary = new IntonationDictionary(INTONATION_DICTIONARY_FILE_NAME);
        StressDictionary stressDictionary = new StressDictionary(STRESS_DICTIONARY_FILE_NAME);
        PhoneticDictionary phoneticDictionary = new PhoneticDictionary(PHONETIC_DICTIONARY_FILE_NAME);

        TextProcessor textProcessor = new TextProcessor(stressDictionary);
        String textWithStress = textProcessor.process(readText(textFileName));

        IntonationProcessor intonationProcessor = new IntonationProcessor(intonationDictionary);
        String intonationText = intonationProcessor.process(textWithStress);

        System.out.println(intonationText);

        PhoneticProcessor phoneticProcessor = new PhoneticProcessor(phoneticDictionary);
        String phonemeText = phoneticProcessor.process(intonationText);

        System.out.println(phonemeText);

        AcousticProcessor acousticProcessor = new AcousticProcessor(ALLOPHONE_BASE_DIR_NAME);
        List<WavFile> wavFileList = acousticProcessor.process(phonemeText);

        WavFile resultWavFile = WavUtils.mergeFiles(wavFileList);
        resultWavFile.write(resultFileName);
    }

    private static String readText(String textFilePath) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(textFilePath);
            return IOUtils.toString(inputStream);
        }
        catch (IOException e) {
            throw new IllegalStateException(String.format("Can't read text file '%s'.", textFilePath), e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
