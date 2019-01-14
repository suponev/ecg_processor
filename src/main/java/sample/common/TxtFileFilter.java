package sample.common;

import java.io.File;
import java.io.FilenameFilter;

public class TxtFileFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        //String[] tmp = name.split(".");
        String t = name.substring(name.length() - 3, name.length());
        if (!t.equals("txt")) {
            return false;
        }
        return true;
    }
}
