package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import me.dreamhopping.pml.api.util.TransformerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class InGameHudTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/gui/hud/InGameHud");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            if (methodNode.name.equals("addChatMessage")) {
                InsnList list = new InsnList();
                list.add(new VarInsnNode(Opcodes.ALOAD, 2));
                list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("InGameHud"), "addChatMessage", "(Lnet/minecraft/text/Text;)V"));

                methodNode.instructions.insert(list);
            } else if (methodNode.name.equals("render")) {
                for (AbstractInsnNode node = methodNode.instructions.getLast(); node != null; node = node.getPrevious()) {
                    if (node.getOpcode() == Opcodes.RETURN) {
                        MethodInsnNode renderGameInsn = new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("InGameHud"), "render", "()V");
                        methodNode.instructions.insertBefore(node, renderGameInsn);

                        break;
                    }
                }
            }
        }

        return classNode;
    }
}
