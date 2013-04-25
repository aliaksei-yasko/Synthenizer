package com.yaskoam.dictionary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Strings;

/**
 * @author Q-YAA
 */
public class DictionaryUtils {

    public static Map<String, String> read(String dictionaryFilePath) {

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(dictionaryFilePath);
            List<String> lines = IOUtils.readLines(inputStream);

            Map<String, String> dictionaryMap = new HashMap<String, String>();

            for (String line : lines) {

                if (!Strings.isNullOrEmpty(line)) {

                    String from = line.split("\\->")[0].trim().replace("'", "");
                    String to = line.split("\\->")[1].trim().replace("'", "");

                    dictionaryMap.put(from, to);
                }
            }

            return dictionaryMap;
        }
        catch (IOException e) {
            throw new IllegalStateException(String.format("Can't read dictionary file '%s'", dictionaryFilePath), e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
