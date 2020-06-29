/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.github.hunter524.forlove

import com.google.common.primitives.Ints
import javassist.ClassPool
import jdk.internal.org.objectweb.asm.ClassWriter
import okio.ByteString
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.zip.CRC32

class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}
fun main(args: Array<String>) {
    println(App().greeting)
    println("Thread is Daemon :${Thread.currentThread().isDaemon}")
//    testCWD()
//    testCodePoint()
    testAppendReplace()

    println("${encrypt("451795172")}")
}

private fun encrypt(str: String): String {
    var crC32time = CRC32()
    crC32time.update(str.toByteArray())
    return ByteString.of(*Ints.toByteArray(crC32time.value.toInt())).base64()
}

// 通过相对路径构建 File
// CWD(current working directory ) 当前工作目录则为当前执行 java 命令运行 jar 包的目录
// 构建的相对路径的 File 则是相对 CWD 的文件
fun testCWD() {
    val file = File("src")
    println("File AbsolutePath: ${file.absolutePath}")
}

fun testDaemonThread() {
    var thread = Thread {
        while (true) {
            runCatching {
                Thread.sleep(1000)
            }
        }
    }
//    thread.isDaemon = true
    thread.isDaemon = false
    thread.start()
}

fun testCodePoint() {
    var s = "严"
    var codePointAt = s.codePointAt(0)
    println("严 codePoint: ${Integer.toHexString(codePointAt)}")
//    String#getChars 获得即为 Unicode 字符集该字对应的编码
    s.chars().forEach {
        println("${Integer.toBinaryString(it)}\n")
    }
//    String#getBytes 对应的即为指定编码之后的byte流
    var byteArray = s.toByteArray()
    byteArray.forEach {
        var binaryString = Integer.toBinaryString(it.toInt())
        println(binaryString.substring(24))
    }
}

// 用于读取 正则表达式替换后的结果
fun testAppendReplace(){
    val p: Pattern = Pattern.compile("cat")
    val m: Matcher = p.matcher("one cat two cats in the yard")
    val sb = StringBuffer()
    while (m.find()) {
        m.appendReplacement(sb, "dog")
    }
    m.appendTail(sb)
    println(sb.toString())
}
