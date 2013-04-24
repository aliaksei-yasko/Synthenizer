package com.yaskoam.sound;

import java.util.Arrays;
import java.util.List;

/**
 * @author Q-YAA
 */
public class WavUtils {

    public static WavFile mergeFiles(WavFile... wavFiles) {
        return mergeFiles(Arrays.asList(wavFiles));
    }

    public static WavFile mergeFiles(List<WavFile> wavFiles) {
        WavFile resultWavFile = wavFiles.get(0);
        for (int i = 1; i < wavFiles.size(); i++) {
            resultWavFile = resultWavFile.merge(wavFiles.get(i));
        }

        return resultWavFile;
    }
}
