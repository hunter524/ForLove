//使用 -I <file_path> --init-script <file_path> 通过命令行指定 init 脚本

// init.script 脚本在用户的build.gradle.kt 执行前执行
// 执行 init 脚本时 setting 脚本也没有被执行,因此无法获得 rootProject 等 Project 对象
// 但是当执行编译相关脚本时 Gradle 对象已经被构建,因此 init.script 内部可以添加 gradle 脚本

// 脚本的执行顺序: init.gradle.kts => settings.gradle.kts = > (此前为 init 阶段) => build.gradle.kts => (此前为 Configuration
// 阶段) => 执行 gradle 命令指定的task => (此前为 execution 阶段)

println("load cmd init")
println("project ${gradle.startParameter.taskNames}")

gradle.rootProject {
    println("from init listener root project loaded")
}