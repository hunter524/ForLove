// 便于复制到 <user_home>/.gradle (gradle_user_home)目录
//该 init 脚本和 <user_home>/.gradle/init.d/ 目录下的脚本均会执行两次
//因为该项目下面包含一个 buildSrc 项目，buildSrc 项目是作为一个单独的项目构建，因此 init 脚本会被执行两次，

println("init gradle from root project!")