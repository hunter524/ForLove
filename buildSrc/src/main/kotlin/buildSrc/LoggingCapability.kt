package buildSrc

import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

// 检查是否具有相同功能的库的依赖，如果有则抛出异常
open class LoggingCapability:ComponentMetadataRule {
    val loggingModules = setOf("log4j","log4j-over-slf4j")
    override fun execute(context: ComponentMetadataContext) {
        context.details.run {
            if (loggingModules.contains(id.name)){
                allVariants {
                    it.withCapabilities{
                        it.addCapability("log4j","log4j",id.version)
                    }
                }
            }
        }
    }
}