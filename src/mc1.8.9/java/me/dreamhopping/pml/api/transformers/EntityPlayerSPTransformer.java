package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.api.util.TransformerUtils;
import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class EntityPlayerSPTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/entity/EntityPlayerSP");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            switch (methodNode.name) {
                case "sendChatMessage": {
                    InsnList list = new InsnList();
                    list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("EntityPlayerSP"), "sendChatMessage", "(Ljava/lang/String;)V"));

                    methodNode.instructions.insert(list);
                    break;
                }
                case "dropOneItem": {
                    InsnList list = new InsnList();
                    list.add(new VarInsnNode(Opcodes.ILOAD, 1));
                    list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("EntityPlayerSP"), "dropItem", "(Z)V"));

                    methodNode.instructions.insert(list);
                    break;
                }
                case "respawnPlayer": {
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("EntityPlayerSP"), "respawn", "()V"));
                    break;
                }
            }
        }

        return classNode;
    }
}
