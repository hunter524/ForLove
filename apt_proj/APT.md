# APT 使用手册

- 在 /resources/META-INF/services 建立 javax.annotation.processing.Processor 文件，其中包含对应的 APT 实现类 如:
com.github.hunter524.forlove.HelloProcessor

- 自己实现 HelloProcessor 继承自 javax.annotation.processing.AbstractProcessor。并利用 java 提供的 API 解析当前源码中的类/注解文件结构。
并且利用 javapoet 等源码生成辅助库生成相关源码文件输出。通常在现有的源码中会利用反射获取相应的生成类便于调用。

- /resources/META-INF/services 中的 apt 配置文件可以通过 [google auto 库中的 AutoService][https://github.com/google/auto/] 组件自动生成。


## TODO
- 分析 lombok 对于 apt 技术的使用(lombok.core.AnnotationProcessor)。分析 lombok 中对于 asm 库的使用。