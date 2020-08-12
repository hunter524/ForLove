package com.github.hunter524.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * 自定义的 Task 需要是 open 便于运行时生成继承该类的类进行*魔法*
 * 如果需要使自定义的 Task 能进行 UptoDate(Incremental) 增量构建检测，则需要同时定义 Input 和 Output 属性
 */
open class LatestArtifactTask : DefaultTask() {
    @Input
    public var url: String = ""

    @Input
    public var artifact: String = ""

    @OutputFile
    var outputFile:File = File("/home/hunter/IdeaProjects/ForLove/dep.txt")

    @TaskAction
    private fun doFetchLates() {
        println("LatestArtifactTask Done! Url: $url Artifact: $artifact")
    }
}