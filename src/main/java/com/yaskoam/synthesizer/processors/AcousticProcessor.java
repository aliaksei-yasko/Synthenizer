package com.yaskoam.synthesizer.processors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.SuffixFileFilter;

import com.yaskoam.sound.WavFile;

/**
 * @author Q-YAA
 */
public class AcousticProcessor {

    private static final String ALLOPHONE_FILE_SUFFIX = ".wav";

    private String allphoneBaseDirName;

    public AcousticProcessor(String allophoneBaseDirName) {
        this.allphoneBaseDirName = allophoneBaseDirName;
    }

    public List<WavFile> process(String allophoneText) {
        File allophoneBaseDir = new File(allphoneBaseDirName);

        allophoneText = allophoneText.replaceAll("\\s+", "");
        String[] allophones = allophoneText.split(",");

        List<WavFile> resultsFile = new ArrayList<WavFile>();
        for (String allophone : allophones) {
            for (String fileName : allophoneBaseDir.list(new SuffixFileFilter(ALLOPHONE_FILE_SUFFIX))) {

                if (isFileAllophone(fileName, allophone)) {
                    resultsFile.add(new WavFile(allophoneBaseDir + File.separator + fileName));
                    break;
                }
            }
        }

        return resultsFile;
    }

    private boolean isFileAllophone(String fileName, String allophone) {
        return fileName.startsWith(allophone);
    }
}
