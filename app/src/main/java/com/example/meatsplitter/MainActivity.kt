package com.example.meatsplitter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var totalBill: EditText
    private lateinit var meatPortion: EditText
    private lateinit var totalPeople: EditText
    private lateinit var meatEaters: EditText
    private lateinit var calculateButton: Button
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI elements to variables
        totalBill = findViewById(R.id.totalBill)
        meatPortion = findViewById(R.id.meatPortion)
        totalPeople = findViewById(R.id.totalPeople)
        meatEaters = findViewById(R.id.meatEaters)
        calculateButton = findViewById(R.id.calculateButton)
        result = findViewById(R.id.result)

        // Set onClickListener for the calculate button
        calculateButton.setOnClickListener {
            calculateBill()
        }
    }

    private fun calculateBill() {
        // Get values from input fields
        val totalBillAmount = totalBill.text.toString().toDoubleOrNull()
        val meatPortionAmount = meatPortion.text.toString().toDoubleOrNull()
        val numberOfPeople = totalPeople.text.toString().toIntOrNull()
        val numberOfMeatEaters = meatEaters.text.toString().toIntOrNull()

        // Check if inputs are valid
        if (totalBillAmount == null || meatPortionAmount == null || numberOfPeople == null || numberOfMeatEaters == null) {
            result.text = "Please enter valid values"
            return
        }

        // Perform the bill-splitting calculation
        val nonMeatPortion = totalBillAmount - meatPortionAmount
        val shareNonMeat = nonMeatPortion / numberOfPeople
        val shareMeat = meatPortionAmount / numberOfMeatEaters
        val amountForMeatEaters = shareNonMeat + shareMeat
        val amountForNonMeatEaters = shareNonMeat

        // Display the result
        val resultText = """
            Non-meat eaters owe: $${String.format("%.2f", amountForNonMeatEaters)}
            Meat eaters owe: $${String.format("%.2f", amountForMeatEaters)}
        """.trimIndent()
        result.text = resultText
    }
}
