package com.hard;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String modulePath = "c:/modules";

        /**
         * Создаем загрузчик модулей.
         */
        EntityClassLoader classLoader = new EntityClassLoader(modulePath, ClassLoader.getSystemClassLoader());

        /**
         * Получаем список доступных модулей.
         */
        File dir = new File(modulePath);
        String[] modules = dir.list();

        /**
         * Загружаем и исполняем каждый модуль.
         */
        for (String module : modules) {
            try {
                String moduleName = module.split(".class")[0];
                Class clazz = classLoader.loadClass("com.hard." + moduleName);
                IEntity execute = (IEntity) clazz.newInstance();

                execute.execute();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

interface IEntity {
    void execute();
}

class Entity implements IEntity {
    @Override
    public void execute() {
        System.out.println("Entity " + this.getClass() + " Hello World");
    }
}

class EntityClassLoader extends ClassLoader {
    /**
     * Путь до директории с модулями.
     */
    private String path;

    public EntityClassLoader(String path, ClassLoader parent) {
        super(parent);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            /**
             * Получем байт-код из файла и загружаем класс в рантайм
             */
            byte[] bytes = fetchClassFromFS(path + name + ".class");

            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            return super.findClass(name);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }

    private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        File file = new File(path);

        InputStream inputStream = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File inputStream too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        while (offset < bytes.length && (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }

        // Close the input stream and return bytes
        inputStream.close();

        return bytes;
    }
}
