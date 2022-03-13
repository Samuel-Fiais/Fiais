package com.samuelfiais.fiais.molecular_mass

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged


class MoleculeMassCalculate (
    private val molecularEDT: EditText,
    private val calculateBTN: Button,
    private val resultTXT: TextView,
    private val number_moleculeTXT: TextView
    ) {
    fun main() {
        moleculeMass()
    }

    private fun moleculeMass() {
        molecularEDT.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) {
                for (ind in text.indices) {
                    if (text[ind] in '1'..'9') {
                        val char = text[ind].toString()
                        val txt = text.toString().replace(char, subscribedNumbers[char.toInt() - 1])
                        molecularEDT.setText(txt)
                        molecularEDT.setSelection(txt.length)
                    }
                }
                if (text[text.length - 1] == '0') {
                    val txt = text.toString().replace("0", "")
                    molecularEDT.setText(txt)
                    molecularEDT.setSelection(txt.length)
                }
            }
        }

        calculateBTN.setOnClickListener {
            if (molecularEDT.text.isNotEmpty()) {
                molecules.clear()
                if (molecularEDT.text.isNullOrBlank() || molecularEDT.text.isNullOrEmpty()) {
                    Toast.makeText(molecularEDT.context, "Digite uma molécula!", Toast.LENGTH_SHORT).show()
                } else {
                    val result = calculateMolecularMass(molecularEDT.text.toString())
                    if (!moleculeFalse) {
                        resultTXT.text = "${molecularEDT.text} = $result u"
                    } else {
                        Toast.makeText(molecularEDT.context, "Molécula Inválida!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            moleculeFalse = false
        }
    }

    private fun addMolecule(molecule: String) {
        if (molecule !in molecules && elements.contains(molecule)) {
            molecules.add(molecule)
        } else if (!elements.contains(molecule)) {
            moleculeFalse = true
        }
    }

    private val molecules = mutableListOf<String>()
    private var moleculeFalse = false

    private fun calculateMolecularMass(molecule: String): Int {
        var molecularMass = 0
        var moleculeCurrent = ""
        var num = 1
        val txtMolecule = "$molecule   "
        var haveParentheses = false
        var moleculeParentheses = ""

        if (txtMolecule.length == 4) {
            moleculeCurrent += txtMolecule[0]
            addMolecule(moleculeCurrent)
            molecularMass += if (elements.contains(moleculeCurrent)) elements[moleculeCurrent]!! * num else 0
        } else if (txtMolecule.length > 4) {
            for (ind in molecule.indices) {
                val char = txtMolecule[ind]
                if (molecule[ind] == '(') {
                    haveParentheses = true
                }
                if (haveParentheses) {
                    if (char == ')') {
                        if (txtMolecule[ind + 1] in '₀'..'₉') {
                            num = subscribedNumbers.indexOf(txtMolecule[ind + 1].toString()) + 1
                            if (txtMolecule[ind + 2] in '₀'..'₉') {
                                num = (subscribedNumbers.indexOf(txtMolecule[ind + 1].toString()) + 1) * 10 + subscribedNumbers.indexOf(txtMolecule[ind + 2].toString()) + 1
                            }
                        }
                        haveParentheses = false
                        molecularMass += if (elements.contains(moleculeParentheses)) {
                            calculateMolecularMass(moleculeParentheses) * num
                        } else {
                            moleculeFalse = true
                            0
                        }
                        moleculeParentheses = ""
                        num = 1
                    } else {
                        moleculeParentheses += molecule[ind + 1]
                    }
                } else if (char in 'A'..'Z' ) {
                    if (txtMolecule[ind + 1] in 'a'..'z') {
                        moleculeCurrent += char
                        moleculeCurrent += txtMolecule[ind + 1]
                        addMolecule(moleculeCurrent)
                        if (txtMolecule[ind + 2] in '₀'..'₉') {
                            num = subscribedNumbers.indexOf(txtMolecule[ind + 2].toString()) + 1
                            if (txtMolecule[ind + 3] in '₀'..'₉') {
                                num = (subscribedNumbers.indexOf(txtMolecule[ind + 2].toString()) + 1) * 10 + subscribedNumbers.indexOf(txtMolecule[ind + 3].toString()) + 1
                            }
                        }
                        molecularMass += if (elements.contains(moleculeCurrent)) {
                            elements[moleculeCurrent]!! * num
                        } else {
                            moleculeFalse = true
                            0
                        }
                        moleculeCurrent = ""
                        num = 1
                    } else {
                        moleculeCurrent += char
                        addMolecule(moleculeCurrent)
                        if (txtMolecule[ind + 1] in '₀'..'₉') {
                            num = subscribedNumbers.indexOf(txtMolecule[ind + 1].toString()) + 1
                            if (txtMolecule[ind + 2] in '₀'..'₉') {
                                num = (subscribedNumbers.indexOf(txtMolecule[ind + 1].toString()) + 1) * 10 + subscribedNumbers.indexOf(txtMolecule[ind + 2].toString()) + 1
                            }
                        }
                        molecularMass += if (elements.contains(moleculeCurrent)) {
                            elements[moleculeCurrent]!! * num
                        } else {
                            moleculeFalse = true
                            0
                        }
                        moleculeCurrent = ""
                        num = 1
                    }
                }
            }
        }
        return if (molecules.size >= 1) {
            var txt = ""
            for (mol in molecules) {
                txt += "$mol = ${elements[mol]} u\n"
            }
            if (!moleculeFalse) {
                number_moleculeTXT.text = txt
            } else {
                number_moleculeTXT.text = ""
            }
            molecularMass
        } else {
            0
        }
    }

    private val subscribedNumbers = listOf("₁", "₂", "₃", "₄", "₅", "₆", "₇", "₈", "₉")

    private val elements = mapOf(
        "H" to 1,
        "He" to 4,
        "Li" to 7,
        "Be" to 9,
        "B" to 11,
        "C" to 12,
        "N" to 14,
        "O" to 16,
        "F" to 19,
        "Ne" to 20,
        "Na" to 23,
        "Mg" to 24,
        "Al" to 27,
        "Si" to 28,
        "P" to 31,
        "S" to 32,
        "Cl" to 35,
        "Ar" to 40,
        "K" to 39,
        "Ca" to 40,
        "Sc" to 45,
        "Ti" to 48,
        "V" to 51,
        "Cr" to 52,
        "Mn" to 55,
        "Fe" to 56,
        "Co" to 59,
        "Ni" to 59,
        "Cu" to 64,
        "Zn" to 65,
        "Ga" to 70,
        "Ge" to 73,
        "As" to 75,
        "Se" to 79,
        "Br" to 80,
        "Kr" to 84,
        "Rb" to 85,
        "Sr" to 88,
        "Y" to 89,
        "Zr" to 91,
        "Nb" to 93,
        "Mo" to 96,
        "Tc" to 98,
        "Ru" to 101,
        "Rh" to 103,
        "Pd" to 106,
        "Ag" to 108,
        "Cd" to 112,
        "In" to 115,
        "Sn" to 119,
        "Sb" to 122,
        "Te" to 128,
        "I" to 127,
        "Xe" to 131,
        "Cs" to 133,
        "Ba" to 137,
        "La" to 139,
        "Ce" to 140,
        "Pr" to 141,
        "Nd" to 144,
        "Pm" to 145,
        "Sm" to 150,
        "Eu" to 152,
        "Gd" to 157,
        "Tb" to 159,
        "Dy" to 162,
        "Ho" to 165,
        "Er" to 167,
        "Tm" to 169,
        "Yb" to 173,
        "Lu" to 175,
        "Hf" to 179,
        "Ta" to 181,
        "W" to 184,
        "Re" to 186,
        "Os" to 191,
        "Ir" to 192,
        "Pt" to 195,
        "Au" to 197,
        "Hg" to 201,
        "Tl" to 204,
        "Pb" to 207,
        "Bi" to 209,
        "Po" to 210,
        "At" to 210,
        "Rn" to 220,
        "Fr" to 223,
        "Ra" to 226,
        "Ac" to 227,
        "Th" to 232,
        "Pa" to 231,
        "U" to 238,
        "Np" to 237,
        "Pu" to 244,
        "Am" to 243,
        "Cm" to 247,
        "Bk" to 247,
        "Cf" to 251,
        "Es" to 252,
        "Fm" to 257,
        "Md" to 258,
        "No" to 259,
        "Lr" to 262,
        "Rf" to 261,
        "Db" to 262,
        "Sg" to 266,
        "Bh" to 264,
        "Hs" to 277,
        "Mt" to 268,
        "Ds" to 271,
        "Rg" to 272,
        "Cn" to 277,
        "Nh" to 286,
        "Fl" to 289,
        "Mc" to 288,
        "Lv" to 293,
        "Ts" to 294,
        "Og" to 294
    )
}