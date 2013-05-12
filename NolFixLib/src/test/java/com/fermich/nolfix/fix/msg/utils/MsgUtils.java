package com.fermich.nolfix.fix.msg.utils;

public class MsgUtils {

    public static boolean compareIgnoringWhiteSpaces(String src, String pattern) {
        String srcWithoutWhitespaces = src.replaceAll("['\n', '\t', '\r', ' ']", "");
        String patternWithoutWhitespaces = pattern.replaceAll("['\n', '\t', '\r', ' ']", "");
        return srcWithoutWhitespaces.equals(patternWithoutWhitespaces);
    }

}
