package com.fermich.nolfix.fix.msg.utils;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    public String readFile(String xmlFile) throws IOException {
        InputStream is = openLocalFile(xmlFile);
        String fileContent = getStreamContentAsString(is);
        is.close();
        return fileContent;
    }

    private InputStream openLocalFile(String fileName) {
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    private String getStreamContentAsString(InputStream in) {
        try {
            return CharStreams.toString(new InputStreamReader(in));
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
