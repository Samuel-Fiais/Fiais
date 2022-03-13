package com.samuelfiais.fiais.molecular_mass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuelfiais.molecular_mass.R
import kotlinx.android.synthetic.main.activity_molecular_mass.*

class MolecularMass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_molecular_mass)

        val moleculeMain = MoleculeMassCalculate(molecularEDT, calculateBTN, resultTXT, number_moleculeTXT)
        moleculeMain.main()
    }
}
