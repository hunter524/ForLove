import buildSrc.BuildSrcPlugin

println("load buile.gradle.kts")
/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */
/**
 * gradle assemble 命令在kotlin java 项目中可以构建所有产品 zip tar jar xxx.sh xxx.bat
 * 其中tar zip 文件解压 既可以通过运行 其bin 目录中的 .bat .sh 脚本即可以运行该项目
 * IV scan report :https://gradle.com/s/cuy3ka3cnt5oq  https://gradle.com/s/g5jjmmj4yjg5s
 */
plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
//    scan 组件不需要显示声明 scan 依赖
//    id("com.gradle.build-scan").version("3.3.1")
    // Apply the application plugin to add support for building a CLI application.
    application
    `maven-publish`
    maven
    idea
}

apply {
    type(BuildSrcPlugin::class)
}


repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.alibaba:easyexcel:2.2.3")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application
    mainClassName = "com.github.hunter524.forlove.AppKt"
    version = "0.0.1"
    group = "com.github.hunter524"
}


// fat-jar/uber-jar
// 相同意思 均为包含被依赖的 jar 一个 jar
// uber 原为德语 意为 above over 超过 simple 只包含当前项目 jar 的 jar
// https://docs.gradle.org/current/userguide/working_with_files.html#sec:creating_uber_jar_example
// 通过 jar 任务 解压被依赖的 jar 文件，将 jar 中的内容拷贝到当前生成的 jar 文件中
tasks.register<Jar>("fatJar") {

    manifest {
        attributes("Main-Class" to "com.github.hunter524.forlove.AppKt")
    }

    archiveClassifier.set("fat")

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

// 分析task 被多次执行的原因 A->C B->C
// 同时执行 A B 任务 C 只被执行了一次
tasks.create("A") {
    doFirst {
        println("A")
    }
}

tasks.create("B") {
    doFirst {
        println("B")
    }
}

tasks.create("C") {
    doFirst {
        println("C")
    }
}

tasks.create("D") {
    doFirst {
        println("D")
    }
}
// 同时运行 A B C 任务时 Task 的运行顺序生效
// 只运行 C 任务时 A B 不运行
// 建立下述 依赖关系之后 运行 A 任务 B C 任务也均会被运行 (B->C->A)
tasks["C"].mustRunAfter(tasks["B"])
afterEvaluate {
    tasks.getByName("A").dependsOn("B")
    tasks.getByName("A").dependsOn("C")
    tasks.getByName("C").dependsOn("D")
    tasks.getByName("B").dependsOn("D")

}
tasks.create("print") {
    doFirst {
        var sysProperties = System.getProperties()
        println(sysProperties.keys)

        var projectProperties = project.extensions.extraProperties.properties
        println(projectProperties.keys)

        println("bar: ${project.extensions.extraProperties["org.gradle.project.bar"]} " +
                "bar from project#properties:${project.properties["org.gradle.project.bar"]}")

        println("foo: ${project.extensions.extraProperties["ORG_GRADLE_PROJECT_foo"]} " +
                "foo from project#properties:${project.properties["ORG_GRADLE_PROJECT_foo"]}")

    }
}

//扩展方法结合编译器魔法,实现 provideDelegate 委托属性的创建
val taskCreateByBy by tasks.registering {
    doFirst {
        println("taskCreate By By!")
    }
    doLast {
        println("Property from task: ${this.extra["p"]}")
    }
}
// task#extensions#extraProperties (扩展方法委托至该字段)
// 该属性每个 Task 均有自己的 Extensions
tasks["taskCreateByBy"].extra["p"] = "v"

//创建指定类型的task并且返回的数据为 TaskProvider
//并且指定创建的 Task 类型为 Copy 类型
//创建该 task 的闭包内部使用的 from into 还是处于配置该类型的task阶段
//外层 into 指定根目录,内层目录使用 /开始 为相对外层根目录的子目录
// 使用相对目录则是转化为绝对目录，再使用绝对目录相对于 into 的根目录进行复制操作

var taskProvider = tasks.register<Copy>("copySub") {

    from(file("/home/hunter/IdeaProjects/ForLove/src/main/kotlin/com/github/hunter524/forlove/App.kt")) {
//        into(file("src"))
        into(file("/src"))
    }

    from(file("src/test")) {
//        into(file("test2"))
        into(file("/test2"))
    }
    into(file("/home/hunter/kl/"))
}

tasks.register<Zip>("zipSub"){
//    basename-appedix-version-class.ext
    archiveBaseName.set("basename")
    archiveAppendix.set("appedix")
    archiveVersion.set("0.0.1")
    archiveClassifier.set("class")
    archiveExtension.set("ext")
    destinationDirectory.set(file("ziptest/zip"))

    from("ziptest/src")
}

//ExtraPropertiesExtension 扩展了 invoke 方法实现为该属性提供一个初始值
val archivesDirPath by extra { "$buildDir/archives" }

//解压zip 指定目录下的文件进入 ziptest/uzip 目录
tasks.register<Copy>("unZipSub"){
    from(zipTree("ziptest/zip/basename-appedix-0.0.1-class.ext")){
        include("libs/**")
        eachFile{
            relativePath = RelativePath(true,*relativePath.segments.drop(1).toTypedArray())
        }
        includeEmptyDirs = false
    }
    into("ziptest/uzip")
}

// jar 包实际上就是一个class文件和资源文件的压缩包
// TODO:// 但是重新压缩文件生成 jar 包无法运行
tasks.register<Copy>("unZipJar"){
    from(zipTree("ziptest/jar/javassist-3.27.0-GA.jar"))
    into("ziptest/uzip")
}

//TODO:// Any 扩展的 withGroovyBuilder 方法，为 kotlin 提供了 groovy 语法编写脚本的支持
tasks.register("antTask"){
    doFirst{
        ant.withGroovyBuilder {
            "move"("file" to "${buildDir}/reports", "todir" to "${buildDir}/toArchive")
        }
    }
}

//使用 kotlin 委托属性获取 task (虽然该处的 tasks#getting 不是 Map)
project.afterEvaluate {
//    val copySub by tasks.existing
//    println("copySub Name:${copySub.name}")
}

// 验证 Project#file 相关的 api
tasks {
    register("file") {
        doFirst {
            var gitignore = file(".gitignore")
            println("git ignore path: ${gitignore.absolutePath}")
            println("git ignore contents: ${gitignore.readLines()}")
        }
    }
}

// 通过 Annotation 注解定义的 uptodate 任务
// 执行完一次 该任务后更改 .gitignore 会导致 up-to-date 失效,该任务重新运行
tasks.register("uptodate", UptodateTask::class, File("/home/hunter/IdeaProjects/ForLove/.gitignore"), File("/home/hunter/IdeaProjects/ForLove/.gitignore"))
open class UptodateTask @javax.inject.Inject constructor(@get:org.gradle.api.tasks.InputFile val input: File, @get:org.gradle.api.tasks.OutputFile val outPut: File) : DefaultTask() {

    @org.gradle.api.tasks.TaskAction
    fun printExe() {
        println("UptodateTask printExe")
    }
}

// 通过运行时 api 定义 增量构建任务
tasks.register("rtUptodate",DefaultTask::class){
    doFirst{
        println("rtUptodate executed")
    }
    outputs.file("/home/hunter/IdeaProjects/ForLove/.gitignore")
    inputs.file("/home/hunter/IdeaProjects/ForLove/.gitignore")
}

// 自行进行 up-to-date 检测，有该文件即可认为up-to-date
tasks.register("selfCheckUptoDate"){
    outputs.upToDateWhen {
        file("uptodate.cfg").exists()
    }
    doFirst{
        println("selfCheckUptoDate Executed")
    }
}

//批量的根据 Task 名称的规则执行任务，该任务在运行该task之前并不存在，是被根据规则动态创建的
//如该处的域名则可以根据执行的任务名称动态的获取和更改
tasks.addRule("Pattern:Ping<ID>"){
    val taskName = this;
    if(startsWith("ping")){
        task(taskName){
            doLast{
//                exec{
//                    commandLine("wget","https://guyuesh2.online:8443")
//                }
                println("ping ${this.name.substring(4)}")
            }
        }
    }
}
// 依赖的任务也可以根据 Tasks#Rule 动态进行生成
tasks.register("groupPing"){
    dependsOn("pingServer1","pingServer2")
}

//build.gradle script 内部调用的方法会被委托到 Project 对象上进行调用
//内部的this 指针指向的是 Script 引用
//(kotlin 为生成的子类继承了 KotlinBuildScript 其通过继承 ProjectDelegate 通过其扩展方法实现了脚本内部方法调用委托到 Project 对象上)


println("This From Script type is ${this::class.java} super Class is ${this::class.java.superclass}")

// FileCollection FileTree Api

// 验证一个 FileCollection 可以被惰性求值两次
// 即 srcDir 改变了 再次迭代 FileCollection 会获得不同的结果
tasks.register("fileCollection"){
    doFirst{
        var srcDir:File?= null
//        kotlin 该处传递的 Closure 代表一个 Provider
        val filecl = layout.files ({
            srcDir?.listFiles()
        })
        srcDir = file("src")
        filecl.map { relativePath(it) }.sorted().forEach { println("lazy dir 1: ${it}") }

        srcDir = file("ziptest")
        filecl.map { /*relativePath(it)*/ it}.sorted().forEach { println("lazy dir 2: ${it}") }


        println("files:${filecl.files} \n toList:${filecl.toList()} \n asPath:${filecl.asPath}")
    }
}