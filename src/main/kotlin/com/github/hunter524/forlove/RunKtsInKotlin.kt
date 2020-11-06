package com.github.hunter524.forlove

import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory
import java.io.File

// https://github.com/s1monw1/KtsRunner
/**
 * 使用 kotlin 的 script-runtime/script-util/compiler-embeddable/scripting-compiler-embeddable 组件完成在 Kotlin 代码中运行脚本的工作
 * kotlin 使用了 javax.script.ScriptEngine 和 javax.script.ScriptEngineManager
 *
 */
fun main() {
    var engineByExtension =  KotlinJsr223JvmLocalScriptEngineFactory().scriptEngine
//    var engineByExtension = ScriptEngineManager(Thread.currentThread().contextClassLoader).getEngineByExtension("kts")
    var readText = File("/home/hunter/IdeaProjects/ForLove/kts/simple_kotlin_script.kts").readText()
    println("readText:\n$readText")
    println("eval kt script start @${System.currentTimeMillis()}")
//    运行一个 hello world 脚本编译需要 6s 和脚本的复杂度没有太大关系
    engineByExtension.eval(readText)
    println("eval kt script end @${System.currentTimeMillis()}")

}