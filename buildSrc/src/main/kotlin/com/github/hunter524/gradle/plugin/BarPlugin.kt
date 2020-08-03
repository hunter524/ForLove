package com.github.hunter524.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class BarPlugin:Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("barExt",BarExtension::class.java,"defaultHome","defaultSite",BarContent("DefaultTitle","DefaultBoady"))
        project.tasks.register("BarTask"){
            it.doFirst{
                var barExtension = project.extensions.getByName("barExt") as BarExtension
                println("BarTask Done Home: ${barExtension.home} Site: ${barExtension.site} Content: ${barExtension.content.toString()}")
            }
        }
    }
}

open class BarExtension(var home:String,var site:String,var content: BarContent)
data class BarContent(var title:String,var boady:String)