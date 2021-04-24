package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.api.util.TransformerUtils;
import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ClientPlayNetworkHandlerTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/network/ClientPlayNetworkHandler");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            if (methodNode.name.equals("onGameJoin")) {
                InsnList list = new InsnList();
                list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                list.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/client/network/ClientPlayNetworkHandler", "connection", "Lnet/minecraft/network/ClientConnection;"));
                list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/network/ClientConnection", "isLocal", "()Z"));
                list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                list.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/client/network/ClientPlayNetworkHandler", "connection", "Lnet/minecraft/network/ClientConnection;"));
                list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/network/ClientConnection", "getAddress", "()Ljava/net/SocketAddress;"));
                list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("ClientPlayNetworkHandler"), "onGameJoin", "(ZLjava/net/SocketAddress;)V"));

                methodNode.instructions.insert(list);
            } else if (methodNode.name.equals("onDisconnected")) {
                InsnList list = new InsnList();
                list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("ClientPlayNetworkHandler"), "onDisconnected", "(Lnet/minecraft/text/Text;)V"));

                methodNode.instructions.insert(list);
            }
        }

        return classNode;
    }
}
