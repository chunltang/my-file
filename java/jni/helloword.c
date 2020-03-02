#include "helloworld.h"
#include <stdio.h>
 
JNIEXPORT void JNICALL Java_HelloWorld_sayHello(JNIEnv *a, jobject b, jstring c)
{
    printf("Hello，JNI");
}