package com.intelij.AllAboutPlugin.CustomLanguageHighliter

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import com.intellij.psi.TokenType

class MyLexer : LexerBase() {
    private var buffer: CharSequence = ""
    private var tokenStart = 0
    private var tokenEnd = 0
    private var tokenType: IElementType? = null

    private val KEYWORDS = setOf("if", "else", "for", "while", "return", "fun", "robin","boom")

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.tokenStart = startOffset
        this.tokenEnd = startOffset
        this.tokenType = null
        advance()
    }

    override fun advance() {
        if (tokenEnd >= buffer.length) {
            tokenType = null
            return
        }

        tokenStart = tokenEnd

        // Handle whitespace
        if (buffer[tokenStart].isWhitespace()) {
            while (tokenEnd < buffer.length && buffer[tokenEnd].isWhitespace()) {
                tokenEnd++
            }
            tokenType = TokenType.WHITE_SPACE
            return
        }

        // Extract next token
        while (tokenEnd < buffer.length && !buffer[tokenEnd].isWhitespace()) {
            tokenEnd++
        }

        val word = buffer.subSequence(tokenStart, tokenEnd).toString()

        tokenType = when {
            word in KEYWORDS -> MyTokenTypes.KEYWORD
            word.startsWith("\"") && word.endsWith("\"") -> MyTokenTypes.STRING
            word.all { it.isDigit() } -> MyTokenTypes.NUMBER
            else -> MyTokenTypes.IDENTIFIER // Ensure all tokens have a type
        }
    }

    override fun getState(): Int = 0
    override fun getTokenType(): IElementType? = tokenType
    override fun getTokenStart(): Int = tokenStart
    override fun getTokenEnd(): Int = tokenEnd
    override fun getBufferSequence(): CharSequence = buffer
    override fun getBufferEnd(): Int = buffer.length
}
