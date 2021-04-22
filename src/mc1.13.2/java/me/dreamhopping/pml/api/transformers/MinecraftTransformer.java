package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import me.dreamhopping.pml.api.util.TransformerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MinecraftTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/Minecraft");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            if (methodNode.name.equals("runTick")) {
                methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("Minecraft"), "runTick", "()V"));
            } else if (methodNode.name.equals("runGameLoop")) {
                methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("Minecraft"), "runGameLoop", "()V"));
            }
        }
        return classNode;
    }
}
