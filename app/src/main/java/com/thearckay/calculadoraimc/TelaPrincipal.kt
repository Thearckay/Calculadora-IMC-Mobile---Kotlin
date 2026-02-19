package com.thearckay.calculadoraimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaPrincipal : AppCompatActivity() {
    private lateinit var ageInput: EditText
    private lateinit var weigthInput: EditText
    private lateinit var heigthInput: EditText
    private lateinit var imcResult: TextView
    private lateinit var detailsResult: TextView
    private lateinit var groupButtons: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ageInput = findViewById(R.id.ageInput)
        weigthInput = findViewById(R.id.weigthInput)
        heigthInput = findViewById(R.id.heigthInput)
        imcResult = findViewById(R.id.imcResult)
        detailsResult = findViewById(R.id.detailsResult)
        groupButtons = findViewById(R.id.radioGroup)
    }

    @SuppressLint("DefaultLocale")
    fun calc(view: View) {
        if (!verifyInputs()) return

        val selectedButtonId = groupButtons.checkedRadioButtonId
        val radioButtonSelected = findViewById<RadioButton>(selectedButtonId)

        val imc = calcIMC() ?: return
        val age = ageInput.text.toString().toIntOrNull() ?: 0
        val gender = radioButtonSelected.text.toString()

        val result = verifyIMCByAge(imc, age, gender)

        imcResult.text = String.format("Seu IMC é: %.2f.", imc)
        detailsResult.text = result
    }

    private fun calcIMC(): Double? {
        val peso = weigthInput.text.toString().toDoubleOrNull()
        val altura = heigthInput.text.toString().toDoubleOrNull()

        if (peso == null || altura == null || altura == 0.0) return null

        return peso / (altura * altura)
    }

    @SuppressLint("SetTextI18n")
    private fun verifyInputs(): Boolean {
        val selectedButtonId = groupButtons.checkedRadioButtonId

        if (selectedButtonId == -1) {
            imcResult.text = "Falta informar seu sexo"
            return false
        }
        if (ageInput.text.isBlank()) {
            imcResult.text = "Falta informar sua idade"
            return false
        }
        if (weigthInput.text.isBlank()) {
            imcResult.text = "Falta informar seu peso"
            return false
        }
        if (heigthInput.text.isBlank()) {
            imcResult.text = "Falta informar sua altura"
            return false
        }

        return true
    }

    @SuppressLint("SetTextI18n")
    private fun verifyIMCByAge(imc: Double, age: Int, gender: String): String {
        if (age < 6) return "A idade mínima para o calculo completo é apartir de 6 anos"
        if (age > 110) return "A idade máxima para o calculo completo é até 110 anos"

        return if (gender == "Masculino") {
            when (age) {
                6 -> calc(imc, 14.5, 16.7, 18.0)
                7 -> calc(imc, 15.0, 17.4, 19.1)
                8 -> calc(imc, 15.6, 16.8, 20.3)
                9 -> calc(imc, 16.1, 18.9, 21.4)
                10 -> calc(imc, 16.7, 19.7, 22.5)
                11 -> calc(imc, 17.2, 20.4, 23.7)
                12 -> calc(imc, 17.8, 21.2, 24.8)
                13 -> calc(imc, 18.5, 22.0, 25.9)
                14 -> calc(imc, 19.2, 22.8, 26.9)
                15 -> calc(imc, 19.9, 23.7, 27.7)
                16 -> calc(imc, 20.5, 24.4, 28.8)
                17 -> calc(imc, 21.1, 25.2, 29.4)
                in 18..110 -> calc(imc, 21.7, 25.7, 30.0)
                else -> "Idade inválida"
            }
        } else {
            when (age) {
                6 -> calc(imc, 14.3, 16.2, 17.4)
                7 -> calc(imc, 14.9, 17.2, 18.9)
                8 -> calc(imc, 15.6, 18.2, 20.3)
                9 -> calc(imc, 16.3, 19.2, 21.7)
                10 -> calc(imc, 17.0, 20.2, 23.2)
                11 -> calc(imc, 17.6, 21.2, 24.5)
                12 -> calc(imc, 18.3, 22.2, 25.9)
                13 -> calc(imc, 18.9, 23.1, 27.7)
                14 -> calc(imc, 19.3, 23.9, 27.9)
                15 -> calc(imc, 19.6, 24.3, 28.8)
                16 -> calc(imc, 19.9, 25.0, 29.6)
                17 -> calc(imc, 20.2, 25.5, 30.1)
                in 18..110 -> calc(imc, 20.4, 25.8, 30.3)
                else -> "Idade inválida"
            }
        }
    }

    private fun calc(imc: Double, thresholdBelow: Double, thresholdNormal: Double, thresholdOverweight: Double): String {
        return when {
            imc < thresholdBelow -> "Abaixo do peso"
            imc < thresholdNormal -> "Normal"
            imc <= thresholdOverweight -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}
