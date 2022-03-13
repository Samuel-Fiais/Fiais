package com.samuelfiais.fiais.second_function
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sqrt

class SecondFunctionCalculate(
    private val AnumberEDT: EditText,
    private val BnumberEDT: EditText,
    private val CnumberEDT: EditText,
    val functionTXT: TextView,
    val resultTXT: TextView,
    val calculateBTN: Button
) {
    fun main() {
        button()
    }
    fun calculate(a: Double, b: Double, c: Double): List<Double> {
        val delta = (b * b) - (4 * a * c)
        if (delta < 0) {
            return listOf(delta, 0.0)
        } else if (delta == 0.0) {
            val x = (-b) / (2 * a)
            return listOf(delta, x)
        } else {
            val x1 = (-b + sqrt(delta)) / (2 * a)
            val x2 = (-b - sqrt(delta)) / (2 * a)
            return listOf(delta, x1, x2)
        }
    }
    fun function(a: Double, b: Double, c: Double) {
        val aTXT = if (a >= 0) "$a" else "$a"
        val bTXT = if (b >= 0) "+$b" else "$b "
        val cTXT = if (c >= 0) "+$c" else "$c "
        functionTXT.text = "${aTXT}x²${bTXT}x$cTXT"
    }
    fun button() {
        calculateBTN.setOnClickListener {
            val a = AnumberEDT.text.toString().toDouble()
            val b = BnumberEDT.text.toString().toDouble()
            val c = CnumberEDT.text.toString().toDouble()
            val result = calculate(a, b, c)
            function(a, b, c)
            if (result[0] < 0) {
                resultTXT.text = "Não existe raízes reais"
            } else if (result[0] == 0.0) {
                resultTXT.text = "x = ${result[1]}"
            } else {
                resultTXT.text = "x₁ = ${result[1]}\nx₂ = ${result[2]}"
            }
        }
    }
}
