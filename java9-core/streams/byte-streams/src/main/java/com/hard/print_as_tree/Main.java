package com.hard.print_as_tree;

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
    public void inspect(File file, int offset) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                print(f, offset + 1);
            }
        }
    }

    private void print(File file, int offset) {
        String tab = getOffset(offset);

        if (!file.isDirectory()) {
            System.out.println(tab + file.getName());
            return;
        }

        System.out.println(tab + "[" + file.getName() + "]");

        inspect(file, offset);
    }

    private String getOffset(int offset) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < offset; i++) {
            stringBuilder.append("|   ");
        }

        stringBuilder.append("+--");

        return stringBuilder.toString();
    }
}
