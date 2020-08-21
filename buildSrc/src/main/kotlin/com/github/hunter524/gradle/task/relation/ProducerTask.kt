package com.github.hunter524.gradle.task.relation

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

open class ProducerTask :DefaultTask() {
    @OutputFile
    var outPutFile:RegularFileProperty = project.objects.fileProperty()

    @TaskAction
    fun doWriteOutput() {
        println("write out put")
        var asFile = outPutFile.get().asFile
        asFile.writeText("Hello Bar OutPut File")
    }
}