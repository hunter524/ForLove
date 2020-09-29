package com.github.hunter524.forlove.bean;

import java.io.*;

public class RedirectStrandardInOut {
    public static void main(String[] args) throws IOException {
        File outPutFile = new File("/home/hunter/IdeaProjects/ForLove/src/main/java/com/github/hunter524/forlove/bean/out.txt");
        if (!outPutFile.exists()){
            outPutFile.createNewFile();
        }
        outPutFile.delete();
        outPutFile.createNewFile();
        System.setOut(new PrintStream(new FileOutputStream(outPutFile)));
        System.out.println("Redirect Output to file out.txt");
    }
}
