package buildSrc

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class SimpleTaskKt : DefaultTask() {
    @TaskAction
    fun doActionFirst() {
        println("SimpleTask=============> doActionFirst")
    }
}