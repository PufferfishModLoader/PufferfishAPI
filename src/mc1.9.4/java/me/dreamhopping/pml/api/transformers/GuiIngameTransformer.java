package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import me.dreamhopping.pml.api.util.TransformerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class GuiIngameTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/gui/GuiIngame");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            if (methodNode.name.equals("renderGameOverlay")) {
                for (AbstractInsnNode node = methodNode.instructions.getLast(); node != null; node = node.getPrevious()) {
                    if (node.getOpcode() == Opcodes.RETURN) {
                        MethodInsnNode renderGameInsn = new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("GuiIngame"), "renderGameOverlay", "()V");
                        methodNode.instructions.insertBefore(node, renderGameInsn);

                        break;
                    }
                }
            }
        }

        return classNode;
    }
}
