package com.hard;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final String rootDir = ".";

        File file = new File(rootDir);

        try {
            System.out.println("path:" + "[" + file.getCanonicalPath() + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileManager fileManager = new FileManager();

        fileManager.inspect(file, 0);
    }
}

class FileManager {
    public void inspect(File file, int level) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                print(f, level + 1);
            }
        }
    }

    private void print(File file, int level) {
        String branch = drawBranch(level);

        if (!file.isDirectory()) {
            System.out.println(branch + file.getName());
            return;
        }

        System.out.println(branch + "[" + file.getName() + "]");

        inspect(file, level);
    }

    private String drawBranch(int level) {
        StringBuilder stringBuilder = new StringBuilder();

        int offset = 3;

        // drawing other branches
        for (int i = 1; i < level; i++) {
            stringBuilder.append("|");

            for (int j = 0; j < offset; j++)
                stringBuilder.append(" ");
        }

        // drawing this branches
        stringBuilder.append("+");

        for (int i = 0; i < offset; i++)
            stringBuilder.append("-");

        return stringBuilder.toString();
    }
}
