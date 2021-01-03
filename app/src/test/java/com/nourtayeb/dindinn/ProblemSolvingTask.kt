package com.nourtayeb.dindinn

import org.junit.Test


// The disadvantages of ASCII conversion:
// -  ASCII table only contains english letters, so we cannot scale to support other languages.
// - Char is represented with 16 bit, while its conversion to int is represented with 32 bit (unnecessary space allocated)
class ProblemSolvingTask {
    fun replaceCharacters(string: String, n: Int): String {
        val realShift = n % 26
        return String(string.map { convertChar(it, realShift) }.toCharArray())
    }

    fun convertChar(char: Char, shift: Int): Char {
        return if(char in 'A'..'Z'){
            convertCharUpperCase(char,shift)
        }else{
            convertCharLowerCase(char,shift)
        }
    }
    fun convertCharLowerCase(char: Char, shift: Int): Char {
        val initialShift = char + shift
        return if (initialShift in 'a'..'z') {
            initialShift
        } else {
            if(initialShift>'z') {
                initialShift - 26
            }else{
                initialShift + 26
            }
        }
    }
    fun convertCharUpperCase(char: Char, shift: Int): Char {
        val initialShift = char + shift
        return if (initialShift in 'A'..'Z') {
            initialShift
        } else {
            if(initialShift>'Z') {
                initialShift - 26
            }else{
                initialShift + 26
            }
        }
    }
    @Test
    fun test() {
        println(replaceCharacters("azAZ", 105))
        println(replaceCharacters("azAZ", -2))
    }
}