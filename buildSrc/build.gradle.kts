repositories {
    mavenCentral()
    google()
}
plugins{
    `kotlin-dsl`
}

dependencies{
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle:7.2.1"){
        exclude("org.ow2.asm")
    }
    implementation("org.ow2.asm:asm:9.3")
    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("org.ow2.asm:asm-util:9.3")
}

gradlePlugin{
    plugins {
        create("FindPrivacyRuntime"){
            this.id = "findPrivacyRuntime"
            implementationClass = "com.ly.findprivacyruntime.FindPrivacyRuntimePlugin"
        }
    }
}