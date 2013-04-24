package com.yaskoam.synthesizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.yaskoam.sound.WavFile;
import com.yaskoam.sound.WavUtils;
import com.yaskoam.synthesizer.processors.AllophoneProcessor;

/**
 * @author Q-YAA
 */
public class Synthesizer {

    public static void main(String[] args) {

        String allophoneBaseDirName = args[0];
        String allophoneTextFileName = args[1];
        String resultFileName = args[2];

        AllophoneProcessor allophoneProcessor = new AllophoneProcessor(allophoneBaseDirName);
        List<WavFile> wavFileList = allophoneProcessor.process(getAllophoneText(allophoneTextFileName));

        WavFile resultWavFile = WavUtils.mergeFiles(wavFileList);
        resultWavFile.write(resultFileName);
    }

    private static String getAllophoneText(String allophoneTextFileName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(allophoneTextFileName);
            return IOUtils.toString(inputStream);
        }
        catch (IOException e) {
            throw new IllegalStateException("Can't load phoneme text file.", e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
