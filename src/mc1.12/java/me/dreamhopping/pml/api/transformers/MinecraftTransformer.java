package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import me.dreamhopping.pml.api.util.TransformerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class MinecraftTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/Minecraft");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            switch (methodNode.name) {
                case "createDisplay":
                    InsnList list = new InsnList();
                    list.add(new LdcInsnNode("Minecraft 1.12 | PufferfishModLoader (dev/1.0)"));

                    // Find the "toString" call and insert before that
                    for (AbstractInsnNode node = methodNode.instructions.getLast(); node != null; node = node.getPrevious()) {
                        if (node.getOpcode() == Opcodes.INVOKESTATIC) {
                            MethodInsnNode castedNode = (MethodInsnNode) node;
                            if (castedNode.owner.equals("org/lwjgl/opengl/Display") && castedNode.name.equals("setTitle") && castedNode.desc.equals("(Ljava/lang/String;)V")) {
                                // Remove the previous "Minecraft (version)" LDC
                                methodNode.instructions.remove(node.getPrevious());
                                methodNode.instructions.insertBefore(castedNode, list);
                            }
                        }
                    }

                    break;
                case "runTick":
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("Minecraft"), "runTick", "()V"));
                    break;
                case "runGameLoop":
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("Minecraft"), "runGameLoop", "()V"));
                    break;
            }
        }

        return classNode;
    }
}
