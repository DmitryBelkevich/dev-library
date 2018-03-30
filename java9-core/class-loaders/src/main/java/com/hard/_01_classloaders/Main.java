package com.hard._01_classloaders;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();

        System.out.println(classLoader);
        // java-8: sun.misc.Launcher$AppClassLoader
        // java-9: jdk.internal.loader.ClassLoaders$AppClassLoader
    }
}

/**
 * Java 8
 *
 * Extension class loader:
 * static class sun.misc.Launcher.ExtClassLoader
 *      extends class java.net.URLClassLoader
 *           extends class java.security.SecureClassLoader
 *               extends abstract class java.lang.ClassLoader
 *
 * System class loader:
 * static class sun.misc.Launcher.AppClassLoader
 *      extends class java.net.URLClassLoader
 *           extends class java.security.SecureClassLoader
 *               extends abstract class java.lang.ClassLoader
 *
 * -------------
 *
 * Java 9
 *
 * Bootstrap class loader:
 * class jdk.internal.loader.ClassLoaders.BootClassLoader
 *      extends class jdk.internal.loader.BuiltinClassLoader
 *          extends class java.security.SecureClassLoader
 *              extends abstract class java.lang.ClassLoader
 *
 * Extension class loader:
 * class jdk.internal.loader.ClassLoaders.PlatformClassLoader
 *      extends class jdk.internal.loader.BuiltinClassLoader
 *          extends class java.security.SecureClassLoader
 *              extends abstract class java.lang.ClassLoader
 *
 * System class loader:
 * class jdk.internal.loader.ClassLoaders.AppClassLoader
 *      extends class jdk.internal.loader.BuiltinClassLoader
 *          extends class java.security.SecureClassLoader
 *              extends abstract class java.lang.ClassLoader
 */


