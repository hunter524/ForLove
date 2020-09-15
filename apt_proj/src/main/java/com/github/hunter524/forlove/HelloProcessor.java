package com.github.hunter524.forlove;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Set;


@SupportedAnnotationTypes("com.github.hunter524.forlove.Entity")
/**
 * APT 工具通常是
 * 结合 JavaPeot 库进行使用，用于生成源码文件
 * 结合 javassit,asm 库进行字节码操作，生成 class 文件
 *
 */
public class HelloProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        TypeElement entityAnnotation = processingEnv.getElementUtils().getTypeElement("com.github.hunter524.forlove.Entity");
        if (!annotations.equals(Collections.singleton(entityAnnotation))) {
            return false;
        }

        // tag::isolating-annotation-processor[]
        Set<? extends Element> entities = roundEnv.getElementsAnnotatedWith(entityAnnotation);
        for (Element entity : entities) {
            createRepository((TypeElement) entity);
        }
        // end::isolating-annotation-processor[]
        return true;
    }

    private void createRepository(TypeElement entity) {
        Name entityName = entity.getQualifiedName();
        String repositoryName = entityName + "Repository";
        try {
            JavaFileObject repository = processingEnv.getFiler().createSourceFile(repositoryName, entity);
            Writer writer = repository.openWriter();
            writer.write("public class " + repositoryName + " {");
            writer.write("  public void save(" + entityName + " entity) {");
            writer.write("System.out.println(\"hello apt"+entityName+" append append append!\");");
            writer.write("  }");
            writer.write("}");
            writer.close();
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Could not create " + repositoryName + ": " + e);
        }
    }
}
