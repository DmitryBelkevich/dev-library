#include <iostream>
#include "com_hard_Entity.h"

JNIEXPORT void JNICALL Java_com_hard_Entity_print1(JNIEnv *env, jobject object) {
    std::cout << "print1" << std::endl;
}

JNIEXPORT void JNICALL Java_com_hard_Entity_print2(JNIEnv *env, jclass clazz) {
    std::cout << "print2" << std::endl;
}