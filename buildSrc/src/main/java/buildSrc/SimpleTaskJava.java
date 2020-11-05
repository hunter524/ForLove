package buildSrc;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public abstract class SimpleTaskJava extends DefaultTask {

    @TaskAction
    public void doActionFirst(){
        System.out.println("SimpleTaskJava=======> doActionFirst");
    }
}
