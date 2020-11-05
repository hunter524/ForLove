package buildSrc

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SimpleClassGroovy  extends DefaultTask{
    @TaskAction
    void doActionFirst(){
        System.out.println("buildSrc.SimpleClassGroovy =======> doActionFirst");
    }
}
