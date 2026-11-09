package com.naiane.privaci.calculator

import java.math.BigDecimal
import java.math.RoundingMode

/** Motor da calculadora: + - × ÷ % com ordem correta das operações. */
object CalculatorEngine {

    /** Devolve o resultado em texto, ou null se houver erro (ex.: divisão por zero). */
    fun evaluate(input: String): String? = try {
        val parser = Parser(input)
        val value = parser.parseExpression()
        if (!parser.atEnd() || value.isNaN() || value.isInfinite()) null else format(value)
    } catch (e: Exception) {
        null
    }

    private fun format(value: Double): String =
        BigDecimal(value).setScale(8, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString()

    private class Parser(private val s: String) {
        private var i = 0

        fun atEnd() = i >= s.length

        fun parseExpression(): Double {
            var v = parseTerm()
            while (i < s.length && (s[i] == '+' || s[i] == '-')) {
                val op = s[i++]
                val r = parseTerm()
                v = if (op == '+') v + r else v - r
            }
            return v
        }

        private fun parseTerm(): Double {
            var v = parseFactor()
            while (i < s.length && (s[i] == '×' || s[i] == '÷')) {
                val op = s[i++]
                val r = parseFactor()
                if (op == '÷') {
                    if (r == 0.0) throw ArithmeticException("divisao por zero")
                    v /= r
                } else {
                    v *= r
                }
            }
            return v
        }

        private fun parseFactor(): Double {
            if (i < s.length && s[i] == '-') {
                i++
                return -parseFactor()
            }
            val start = i
            while (i < s.length && (s[i].isDigit() || s[i] == '.')) i++
            if (start == i) throw IllegalArgumentException("numero esperado")
            var v = s.substring(start, i).toDouble()
            while (i < s.length && s[i] == '%') {
                i++
                v /= 100
            }
            return v
        }
    }
}
