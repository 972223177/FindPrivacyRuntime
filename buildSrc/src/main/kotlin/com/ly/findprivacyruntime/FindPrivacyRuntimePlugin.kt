package com.ly.findprivacyruntime

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.ly.findprivacyruntime.core.FindPrivacyClassVisitorFactory
import org.gradle.api.Plugin
import org.gradle.api.Project

class FindPrivacyRuntimePlugin:Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.getByType(AndroidComponentsExtension::class.java).onVariants{
            it.instrumentation.transformClassesWith(FindPrivacyClassVisitorFactory::class.java,InstrumentationScope.ALL){}
            it.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
        }
    }
}