package buildSrc

import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule
import javax.inject.Inject

// 检查是否具有相同功能的库的依赖，如果有则抛出异常
open class LoggingCapability @Inject constructor(val prjName: String) : ComponentMetadataRule {
    val loggingModules = setOf("log4j", "log4j-over-slf4j")

    override fun execute(context: ComponentMetadataContext) {
        println("Component Meta Data Rule:${context.details.id} ProjectName: ${prjName}")
        context.details.run {
            if (loggingModules.contains(id.name)) {
                allVariants {
                    it.withCapabilities {
                        it.addCapability("log4j", "log4j", id.version)
                    }
                }
            }
        }
    }
}