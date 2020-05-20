//使用 -I <file_path> --init-script <file_path> 通过命令行指定 init 脚本
// <user_home>/.gradle
// <user_home>/.gradle/init.d
// <GRADLE_HOME>/init.d
// init.gradle 脚本可以放置于上述位置,执行顺序由上向下进行执行

// init.script 脚本在用户的build.gradle.kt 执行前执行
// 执行 init 脚本时 setting 脚本也没有被执行,因此无法获得 rootProject 等 Project 对象
// 但是当执行编译相关脚本时 Gradle 对象已经被构建,因此 init.script 内部可以获取 gradle 对象,通过 gradle 对象可以监听 project 的创建,配置
// 等不同阶段,同时可以配置 Project 对象属性.

// init.script 脚本无法访问 buildSrc 项目中的内容,因为 buildSrc 项目的构建在 init.script 执行之后,但是在 setting.gradle 和 build.gradle
// 执行之前,因此 setting.gradle 和 build.gradle 可以正常访问 buildSrc 中的内容.

// 脚本的执行顺序: init.gradle.kts => settings.gradle.kts = > (此前为 init 阶段) => build.gradle.kts => (此前为 Configurationgradle c
// 阶段) => 执行 gradle 命令指定的task => (此前为 execution 阶段)

//TODO// 配置init.script 构建脚本的依赖并没有生效
//this.buildscript{
//    repositories {
//        google()
//        mavenCentral()
//        jcenter()
//        maven {
//            setUrl("https://maven.aliyun.com/repository/google")
//        }
//        maven {
//            setUrl("https://maven.aliyun.com/repository/gradle-plugin")
//        }
//    }
//    dependencies{
//        classpath("com.google.code.gson:gson:2.3.1")
//    }
//}
println("load cmd init")
println("project ${gradle.startParameter.taskNames}")

//TODO// 由于依赖并没有生效,因此该处无法使用GSON
//var gson = com.google.gson.Gson()
//var map = gson.fromJson("{\"key\":\"value\"}", Map::class.java)
//println("${map["key"]}")

gradle.rootProject {
    println("from init listener root project loaded")
}

allprojects {
    println("All Project: ${this.name}")
}

// 配置init.script 脚本的编译依赖


