package com.nhn.exam.was.utils;

import java.util.List;

/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class UrlCheckUtils {
    public static boolean checkBlockedExtension(String url, List<String> filter) throws ArrayIndexOutOfBoundsException {
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
