package com.github.hunter524.gradle.task.relation

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

/**
 * Consumer 的 inputFile 依赖 Producer 的 outputFile 时 outputFile 会在编译生成的 xxxTask_Decorated 携带该task信息，当被Consumer
 * 的 inputFile 依赖时则会自动建立 ConsumerTask 依赖 ProducerTask 的依赖关系。
 *
 * 修改　inputFile 为　inputFiles 为　ListProperty
 */
open class ConsumerTask :DefaultTask() {
//    @InputFile
//    var inputFile: RegularFileProperty = project.objects.fileProperty()

    @InputFiles
    var inputFiles:ListProperty<RegularFile> = project.objects.listProperty(RegularFile::class.java)

    @TaskAction
    fun doWriteOutput() {
        println("read input")
        inputFiles.get().forEach{
            var asFile = it.asFile
            var readText = asFile.readText()
            println("inputFile Type: ${it.javaClass.canonicalName}")
            println("Readed Input:$readText")
        }
    }
}