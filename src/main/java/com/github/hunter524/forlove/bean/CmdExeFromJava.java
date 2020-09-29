package com.github.hunter524.forlove.bean;

import okio.ByteString;
import sun.nio.ch.IOUtil;

import java.io.*;

public class CmdExeFromJava {
    //    执行 java 命令 输出的结果会输出在 err 中
    //    执行 git 命令则输出在 output 中
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("git","--version");
        processBuilder.command("java", "-version");
        Process start = processBuilder.start();
        start.waitFor();
        InputStream inputStream = start.getErrorStream();
        byte[] bytes = new byte[4096];
        int read = inputStream.read(bytes, 0, 4096);
        System.out.println("Exit Value:"+start.exitValue()+"Read Count:" + read + "Content:" + new String(bytes));
    }
}
