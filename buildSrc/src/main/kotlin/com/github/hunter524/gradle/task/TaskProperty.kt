package com.github.hunter524.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class TaskProperty:DefaultTask{

//    使用 var 声明的属性可以创建 无法使用 val 声明创建 Task 的只读属性
//    声明的属性会被 gradle 自动实现，因此称为 gradle 管理的属性。使用者可以在配置该task时设置这些属性，便于task执行时根据不同的属性值执行不同的任务
    abstract var url:String
    abstract var urlProperty:Property<String>
    abstract var objectFactory:ObjectFactory

// 构造参数前两个参数通过创建 task 时传递进入的参数进行创建 最后一个参数通过依赖注入由 gradle 提供
    /**
     * 支持输入的参数
     * ObjectFactory : 创建 Property,DomainObjectContainer，自定义对象参数注入等功能
     *
     * ProjectLayout ：获得当前的 Project 目录，当前 Project 的 build 目录。其他获取 DirectoryProperty,ConfigurableFileCollection 等的功能
     * 均被废弃，由 ObjectFactory 进行替代。
     *
     * ProviderFactory: 通过 Callable 创建 Provider 返回给调用者，从而使用惰性配置功能
     * 该 ProviderFactory 可以通过 Project#getProviders 获得，也可以通过 @javax.inject.Inject 注解标记通过依赖注入，注入进入 Task 内部
     *
     * WorkerExecutor: 配合 WorkQueue 执行器进行工作任务的执行。
     */
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