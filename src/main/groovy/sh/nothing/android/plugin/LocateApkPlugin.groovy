package sh.nothing.android.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LocateApkPlugin implements Plugin<Project> {
    @Override
    void apply(Project target) {
        target.android.applicationVariants.all { v ->
            v.assemble.doLast {
                println v.outputs.first().outputFile
            }
            target.task("locate${v.name.capitalize()}") << {
                ["open", "-R", v.outputs.first().outputFile].execute()
            }
            target.task("assembleAndLocate${v.name.capitalize()}", dependsOn: v.assemble) << {
                ["open", "-R", v.outputs.first().outputFile].execute()
            }
        }
    }
}

