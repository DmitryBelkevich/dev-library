package com.hard;

import org.junit.Test;

import java.io.*;

public class InputOutputStreamTest {
    public static class ByteArrayInputOutputStreamTest {
        @Test
        public void should_read_from_bytes_array_and_write_to_bytes_array() {
            // byte[] -> byte[] -> byte[]

            String str = "Hello World" + "\n"
                    + "Hello World";
            byte[] bytes = str.getBytes();

            /**
             * InputStream
             */

            // read from byte[]

            InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            try {
                byteArrayInputStream.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /**
             * OutputStream
             */

            // write to byte[]

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                byteArrayOutputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (byteArrayOutputStream != null)
                        byteArrayOutputStream.flush();

                    if (byteArrayOutputStream != null)
                        byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(byteArrayOutputStream.toString());
            byte[] bytes2 = byteArrayOutputStream.toByteArray();
        }
    }

    public static class FileInputOutputStreamTest {
        @Test
        public void should_read_from_file_and_write_to_bytes_array_and_write_to_file() {
            // file -> byte[] -> file
            // (FileInputStream.read -> ByteArrayOutputStream.write) ( -> FileOutputStream.write)

            /**
             * InputStream
             */

            // read from file

            File file1 = new File("c:/000.txt");

            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            /**
             * OutputStream
             */

            // write to byte[]

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                byte[] buffer = new byte[4096];
                int read;

                while ((read = fileInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (byteArrayOutputStream != null)
                        byteArrayOutputStream.close();

                    if (fileInputStream != null)
                        fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            byte[] bytes = byteArrayOutputStream.toByteArray();

            // write to file

            File file2 = new File("c:/001.txt");

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null)
                        fileOutputStream.flush();

                    if (fileOutputStream != null)
                        fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Test
        public void should_read_from_file_and_write_to_console() {
            // read from file

            File file = new File("c:/000.txt");

            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // init reader

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // write to console

            String str = null;
            try {
                while ((str = bufferedReader.readLine()) != null) {
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null)
                        bufferedReader.close();

                    if (inputStreamReader != null)
                        inputStreamReader.close();

                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Filter

    public static class ObjectInputOutputStreamTest {
        @Test
        public void should_read_from_object_and_write_to_bytes_array_and_write_to_object() {
            // object -> byte[] -> object

            Entity entity = new Entity(1, "Hello World");

            // encode (serialization) from Object to byte[]

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                objectOutputStream.writeObject(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (objectOutputStream != null) {
                        objectOutputStream.flush();
                        objectOutputStream.close();
                    }

                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(byteArrayOutputStream.toString());
            byte[] bytes = byteArrayOutputStream.toByteArray();

            // decode (deserialization) from byte[] to Object

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Entity entity2 = null;
            try {
                entity2 = (Entity) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (objectInputStream != null)
                        objectInputStream.close();

                    if (byteArrayInputStream != null)
                        byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(entity2);
        }
    }

    // Piped
}

class Entity implements Serializable {
    private int id;
    private String str;

    public Entity(int id, String str) {
        this.id = id;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", str='" + str + '\'' +
                '}';
    }
}
