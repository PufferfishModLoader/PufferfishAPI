package me.dreamhopping.pml.api.util;

public abstract class TransformerUtils {
    public static String getImplementationClass(String clazz) {
        return "me/dreamhopping/pml/api/transformers/impl/" + clazz + "TransformerImpl";
    }
}
