package me.dreamhopping.pml.api.bindings.client.gui

interface FontRenderer {
    fun drawString(text: String?, x: Int, y: Int, color: Int): Int
    fun drawString(text: String?, x: Float, y: Float, color: Int, drawShadow: Boolean): Int
    fun drawStringWithShadow(text: String?, x: Float, y: Float, color: Int): Int
    fun getStringWidth(string: String?): Int
    fun getCharWidth(character: Char): Int
    fun trimStringToWidth(string: String?, width: Int): String?
    fun trimStringToWidth(string: String?, width: Int, var2: Boolean): String?
    fun drawSplitString(var0: String?, var1: Int, var2: Int, var3: Int, var4: Int)
    fun splitStringWidth(var0: String?, var1: Int): Int
    var unicodeFlag: Boolean
    fun listFormattedStringToWidth(var0: String?, var1: Int): List<String?>?
}