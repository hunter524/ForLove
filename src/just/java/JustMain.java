import okio.BufferedSource;
import okio.Okio;

import java.io.File;
import java.nio.charset.Charset;

// 单独定义的 SourceSet 无法使用在main 中引入的 implementation api 等依赖
// 需要使用 justImplementation
// 执行 compile<SourceSetName>Java process<SourceSetName>Resource 可以编译指定 SourceSet 的资源文件和源码
// Todo:// 如何生成指定 SourceSet 的 jar?
public class JustMain {
    public static void main(String[] args) throws Throwable{
        BufferedSource buffer = Okio.buffer(Okio.source(new File("/home/hunter/IdeaProjects/ForLove/tmp.txt")));
        String content = buffer.readUtf8();
        System.out.println("Content:"+content);
        buffer.buffer().writeString("1111",Charset.defaultCharset());
//        buff.writeString("\n Append", Charset.defaultCharset());
//        buff.flush();
    }
}
