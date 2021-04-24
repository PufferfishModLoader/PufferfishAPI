package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.api.util.TransformerUtils;
import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class MinecraftClientTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/MinecraftClient");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            switch (methodNode.name) {
                case "getWindowTitle":
                    InsnList list = new InsnList();
                    list.add(new LdcInsnNode(" | PufferfishModLoader (dev/1.0)"));
                    list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;"));

                    // Find the "toString" call and insert before that
                    for (AbstractInsnNode node = methodNode.instructions.getLast(); node != null; node = node.getPrevious()) {
                        if (node.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                            MethodInsnNode castedNode = (MethodInsnNode) node;
                            if (castedNode.owner.equals("java/lang/StringBuilder") && castedNode.name.equals("toString") && castedNode.desc.equals("()Ljava/lang/String;")) {
                                methodNode.instructions.insertBefore(castedNode, list);
                            }
                        }
                    }

                    break;
                case "render":
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("MinecraftClient"), "render", "()V"));
                    break;
                case "tick":
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("MinecraftClient"), "tick", "()V"));
                    break;
            }
        }

        return classNode;
    }
}
