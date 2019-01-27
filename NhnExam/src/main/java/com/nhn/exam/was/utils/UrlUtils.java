package com.nhn.exam.was.utils;

import java.util.List;

public class UrlUtils {
    public static boolean checkBlockedExtension(String url, List<String> filter) throws ArrayIndexOutOfBoundsException {
    	System.out.println("url test : " + url);
    	if(url == null) {
    		return false;
        }
        
        if(url.indexOf(".") > -1) {
            String[] fileToken = url.split("\\.");
            return filter.contains(fileToken[1]);
        }
        
        if (url.indexOf("../") > -1) {
            return true;
        }
        
        return false;
    }

    public static boolean checkBlockRootPath(String fileName) {
        if(fileName == null) {
            return false;
        }
        if (fileName.indexOf("../") > -1) {
            return true;
        }
        return false;
    }
}
