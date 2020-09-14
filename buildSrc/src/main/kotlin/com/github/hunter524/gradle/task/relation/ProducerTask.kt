package com.github.hunter524.gradle.task.relation

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * TODO:// 需要分析源码才能解析，当 ConsumerTask#inputFile 消耗 ProducerTask#outputFile 时会将ConsumerTask 与 ProducerTask 进行关联，
 * TODO:// 并且确定执行顺序。推测是是创建该类型的 Task 时为 getOutPutFile 获得到的 RegularFileProperty 等属性方法添加了 Task 相关的信息。
 */
open class ProducerTask :DefaultTask() {
    @OutputFile
    var outPutFile:RegularFileProperty = project.objects.fileProperty()

    @TaskAction
    fun doWriteOutput() {
        println("write out put")
        var asFile = outPutFile.get().asFile
        asFile.writeText("Hello Bar OutPut File:　${asFile.name}")
    }
}