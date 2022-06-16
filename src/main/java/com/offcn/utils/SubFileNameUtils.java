package com.offcn.utils;

public class SubFileNameUtils {
    public static String subFileName(String url) {
        return url.substring(url.lastIndexOf("\\") + 1);
    }
}
