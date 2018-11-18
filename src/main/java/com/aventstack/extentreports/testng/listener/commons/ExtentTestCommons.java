package com.aventstack.extentreports.testng.listener.commons;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestCommons {

    public static void assignGroups(ExtentTest test, String[] groups) {
        if (groups.length > 0) {
            for (String g : groups) {
                if (g.startsWith("d:") || g.startsWith("device:")) {
                    String d = g.replace("d:", "").replace("device:", "");
                    test.assignDevice(d);
                } else if (g.startsWith("a:") || g.startsWith("author:")) {
                    String a = g.replace("a:", "").replace("author:", "");
                    test.assignAuthor(a);
                } else if (g.startsWith("t:") || g.startsWith("tag:")) {
                    String t = g.replace("t:", "").replace("tag:", "");
                    test.assignCategory(t);
                } else {
                    test.assignCategory(g);
                }
            }
        }
    }

}
