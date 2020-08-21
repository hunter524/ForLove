package com.github.hunter524.gradle.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.TaskAction;

/**
 * TODO://只读属性还是无法在脚本中进行使用
 */
public abstract class ReadOnlyTaskProperty extends DefaultTask {
    public abstract Property<String> getNameProperty();

    @TaskAction
    public void sayHelloAction(){
        System.out.println("Hello My Name is:"+getNameProperty().getOrElse("No Name!"));
    }
}
