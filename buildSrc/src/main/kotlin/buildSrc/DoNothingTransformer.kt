package buildSrc

import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider

abstract class DoNothingTransformer:TransformAction<TransformParameters.None> {

    @get:org.gradle.api.artifacts.transform.InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override fun transform(outputs: TransformOutputs) {
        var inputFile = inputArtifact.orNull?.asFile
        println("input : $inputFile")
        var outPutFile = outputs.file("file.jar")
//        相对路径则要求转换之后的文件需要以该文件命名进行输出
        println("out put dir relative:${outputs.dir("dir").absolutePath} file relative: ${outPutFile.absolutePath}")
//        绝对路径要求指向的路径需要为输出文件
//        println("out put dir absolute: ${outputs.dir("/home/hunter/gradle_transformer")}")
//        inputFile?.copyTo(outPutFile,true)


    }
}