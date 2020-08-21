package com.github.hunter524.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

open class ProviderPropertyTask : DefaultTask {
    val nameProperty: Property<String>
    val messageProvider: Provider<String>

    @Inject
    constructor(objectFactory: ObjectFactory) {
        this.nameProperty = objectFactory.property(String::class.java)
        this.messageProvider = nameProperty.flatMap {
            project.provider {
                println("call provider callable")
                "Hello $it"
            }
        }
    }

    @TaskAction
    fun printHello() {
        println("Print Message From ProviderPropertyTask")
        println(messageProvider.getOrElse("No Message"))
    }
}