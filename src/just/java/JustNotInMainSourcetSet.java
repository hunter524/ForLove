import okio.BufferedSource;
import okio.Okio;

import java.io.File;
import java.nio.charset.Charset;

// 单独定义的 SourceSet 无法使用在main 中引入的 implementation api 等依赖
// 需要使用 justImplementation
// 执行 compile<SourceSetName>Java process<SourceSetName>Resource 可以编译指定 SourceSet 的资源文件和源码

// 使用自定义 jar 任务指定输入目录，即可打包该sourceSet 下的 java class 文件
//tasks.register<Jar>("jarJust"){
//        this.from(sourceSets.getByName("just").output)
//        }

// 查询到 java 插件默认的 jar 任务，将 just SourceSet#outPut 添加进入任务 则 jar 任务也会打包 just 目录下的 java class 文件
//tasks.getByName("jar"){
//        (this as Jar).from(sourceSets.getByName("just").output)
//        }

// 创建 just SourceSet 实现在项目内部的依赖隔离，和代码隔离，但是通过简单的配置,依旧可以将代码打包进入同一个jar
// 比 gradle 多项目的构建更加的轻便。

public class JustNotInMainSourcetSet {
    public static void main(String[] args) throws Throwable{
        BufferedSource buffer = Okio.buffer(Okio.source(new File("/home/hunter/IdeaProjects/ForLove/tmp.txt")));
        String content = buffer.readUtf8();
        System.out.println("Content:"+content);
        buffer.buffer().writeString("1111",Charset.defaultCharset());
//        buff.writeString("\n Append", Charset.defaultCharset());
//        buff.flush();

    }
}
