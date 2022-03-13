package com.samuelfiais.fiais.second_function

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samuelfiais.molecular_mass.R
import kotlinx.android.synthetic.main.activity_second_function.*

class SecondFunction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_function)

        val secondFunction = SecondFunctionCalculate(AnumberEDT, BnumberEDT, CnumberEDT, functionTXT, resultTXT, calculateBTN)
        secondFunction.main()
    }
}