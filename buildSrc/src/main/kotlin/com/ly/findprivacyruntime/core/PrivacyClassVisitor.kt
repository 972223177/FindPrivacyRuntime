package com.ly.findprivacyruntime.core

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.GETSTATIC
import org.objectweb.asm.Opcodes.INVOKEVIRTUAL

class PrivacyClassVisitor(cv: ClassVisitor) : ClassVisitor(Opcodes.ASM9, cv) {


    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val mv = cv.visitMethod(access, name, descriptor, signature, exceptions)

        return object : MethodVisitor(api, mv) {
            override fun visitMethodInsn(
                opcode: Int,
                owner: String?,
                name: String?,
                descriptor: String?,
                isInterface: Boolean
            ) {
                if (PrivacyUtils.contain(owner ?: "", name ?: "")) {
                    mv.visitFieldInsn(
                        GETSTATIC,
                        "com/ly/findprivacyruntime/FindPrivacyUtils",
                        "INSTANCE",
                        "Lcom/ly/findprivacyruntime/FindPrivacyUtils;"
                    )
                    mv.visitLdcInsn(name ?: "")
                    mv.visitMethodInsn(
                        INVOKEVIRTUAL,
                        "com/ly/findprivacyruntime/FindPrivacyUtils",
                        "insertStacktrace",
                        "(Ljava/lang/String;)V",
                        false
                    )
                }

                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
            }
        }
    }


}