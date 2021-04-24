package me.dreamhopping.pml.api.util

object TransformerUtils {
    @JvmStatic
    fun getImplementationClass(clazz: String) = "me/dreamhopping/pml/api/transformers/impl/${clazz}TransformerImpl"
}