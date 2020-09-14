package com.github.hunter524.forlove

import AptProccedClass
import AptProccedClassRepository
import com.google.common.io.Files
import java.io.File
import java.util.*

// 由于 java.util.Properties#store 会生成时间戳
// 因此 gradle 不使用 Properties 进行 Properties 文件的生成 而是使用 WriteProperties 这个task 进行 Properties 文件的生成。
fun main() {
    var properties = Properties()
    properties["key"] = "value"
    var file = File("/home/hunter/IdeaProjects/ForLove/generate.properties")
    if (!file.exists()){
        file.createNewFile()
    }
    var outputStream = file.outputStream()
    properties.store(outputStream,"comments")
    println("Writed")
    AptProccedClassRepository().save(AptProccedClass())
}