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
        println("out put dir :${outputs.dir("dir").absolutePath} file ${outPutFile.absolutePath}")

        inputFile?.copyTo(outPutFile,true)


    }
}