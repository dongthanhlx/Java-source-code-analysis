package Data;


import java.io.*;
import java.util.*;

public class FileList {
    private String path;

    public FileList(String path) {
    	setPath(path);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<File> getList() {  
	File directory = new File(getPath());
        File[] listT = directory.listFiles();
        ArrayList<File> listF = new ArrayList<File>();
        for (int i=0; i<listT.length; i++) {
            File file = listT[i];
            if (file.isFile() && file.getName().endsWith(".java")) {             
                listF.add(file);
            }
        }
        return listF;
    }

}

