#include <jni.h>
#include <stdexcept>
extern "C" JNIEXPORT jlong JNICALL Java_com_offlinetranslator_ai_CTranslate2Native_create(JNIEnv*, jclass, jstring, jstring, jstring) { throw std::runtime_error("CTranslate2 native library is not linked"); }
extern "C" JNIEXPORT void JNICALL Java_com_offlinetranslator_ai_CTranslate2Native_destroy(JNIEnv*, jclass, jlong) {}
extern "C" JNIEXPORT jobjectArray JNICALL Java_com_offlinetranslator_ai_CTranslate2Native_translate(JNIEnv* env, jclass, jlong, jobjectArray, jstring) { jclass c=env->FindClass("java/lang/UnsupportedOperationException"); env->ThrowNew(c,"Link CTranslate2 Android native library"); return nullptr; }
extern "C" JNIEXPORT jobjectArray JNICALL Java_com_offlinetranslator_ai_CTranslate2Native_translateBatch(JNIEnv* env, jclass, jlong, jobjectArray, jstring) { jclass c=env->FindClass("java/lang/UnsupportedOperationException"); env->ThrowNew(c,"Link CTranslate2 Android native library"); return nullptr; }
