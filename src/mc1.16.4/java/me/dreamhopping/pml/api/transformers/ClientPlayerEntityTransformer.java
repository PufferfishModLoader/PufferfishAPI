package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.api.util.TransformerUtils;
import me.dreamhopping.pml.mods.launch.loader.RuntimeTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ClientPlayerEntityTransformer implements RuntimeTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/network/ClientPlayerEntity");
    }

    public ClassNode transform(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            switch (methodNode.name) {
                case "sendChatMessage": {
                    InsnList list = new InsnList();
                    list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("ClientPlayerEntity"), "sendChatMessage", "(Ljava/lang/String;)V"));

                    methodNode.instructions.insert(list);
                    break;
                }
                case "dropSelectedItem": {
                    InsnList list = new InsnList();
                    list.add(new VarInsnNode(Opcodes.ILOAD, 1));
                    list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("ClientPlayerEntity"), "dropSelectedItem", "(Z)V"));

                    methodNode.instructions.insert(list);
                    break;
                }
                case "requestRespawn": {
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("ClientPlayerEntity"), "respawn", "()V"));
                }
            }
        }

        return classNode;
    }
}
