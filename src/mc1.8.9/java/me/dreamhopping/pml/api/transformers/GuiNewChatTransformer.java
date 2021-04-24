package me.dreamhopping.pml.api.transformers;

import me.dreamhopping.pml.api.util.TransformerUtils;
import me.dreamhopping.pml.launch.transformer.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class GuiNewChatTransformer implements ClassTransformer {
    public boolean willTransform(String name) {
        return name.equals("net/minecraft/client/gui/GuiNewChat");
    }

    public ClassNode transformClass(ClassNode classNode) {
        for (MethodNode methodNode : classNode.methods) {
            if (methodNode.name.equals("printChatMessage")) {
                InsnList list = new InsnList();
                list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, TransformerUtils.getImplementationClass("GuiNewChat"), "printChatMessage", "(Lnet/minecraft/util/IChatComponent;)V"));

                methodNode.instructions.insert(list);
                break;
            }
        }

        return classNode;
    }
}
