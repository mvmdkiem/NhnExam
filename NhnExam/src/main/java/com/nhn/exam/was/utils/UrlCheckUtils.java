package com.nhn.exam.was.utils;

import java.util.List;

/**
 * 
 * @author Kim TaeHouyng
 *
 * @param <T> Object 
 * @param <V> String 
 * 
 */
public class UrlCheckUtils {
	private static List<String> filter;

    public static boolean checkBlockedExtension(String url) {
    	if(url == null) {
    		return false;
        }
        
        if(url.indexOf(".") > -1) {
            String[] strArr = url.split("\\.");
            return filter.contains(strArr[1]);
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
