package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * Java 8
 *
 * static class sun.misc.Launcher.AppClassLoader
 *      extends class java.net.URLClassLoader
 *           extends class java.security.SecureClassLoader
 *               extends abstract class java.lang.ClassLoader
 *
 * static class sun.misc.Launcher.ExtClassLoader
 *      extends class java.net.URLClassLoader
 *           extends class java.security.SecureClassLoader
 *               extends abstract class java.lang.ClassLoader
 *
 * -------------
 *
 * Java 9
 *
 * class jdk.internal.loader.ClassLoaders.BootClassLoader
 *      extends class jdk.internal.loader.BuiltinClassLoader
 *          extends class java.security.SecureClassLoader
 *              extends abstract class java.lang.ClassLoader
 *
 * class jdk.internal.loader.ClassLoaders.PlatformClassLoader
 *      extends class jdk.internal.loader.BuiltinClassLoader
 *          extends class java.security.SecureClassLoader
 *              extends abstract class java.lang.ClassLoader
 *
 * class jdk.internal.loader.ClassLoaders.AppClassLoader
 *      extends class jdk.internal.loader.BuiltinClassLoader
 *          extends class java.security.SecureClassLoader
 *              extends abstract class java.lang.ClassLoader
 */


