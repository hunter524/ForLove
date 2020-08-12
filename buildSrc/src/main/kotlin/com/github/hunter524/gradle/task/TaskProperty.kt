package com.github.hunter524.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class TaskProperty:DefaultTask{

//    使用 var 声明的属性可以创建 无法使用 val 声明创建 Task 的只读属性
    abstract var url:String
    abstract var urlProperty:Property<String>
    abstract var objectFactory:ObjectFactory

// 构造参数前两个参数通过创建 task 时传递进入的参数进行创建 最后一个参数通过依赖注入由 gradle 提供
    @Inject
    constructor(url: String, urlProperty: Property<String>,objectFactory: ObjectFactory) : super() {
        this.url = url
        this.urlProperty = urlProperty
        this.objectFactory = objectFactory
    }


    @TaskAction
    fun doTaskAction() {
        println("TaskProperty Do Task Action: url: ${url} urlProperty: ${urlProperty.get()}")
        println("ObjectFactory Class: ${objectFactory::class.java.canonicalName} Hash Code: ${objectFactory.hashCode()}")
    }
}