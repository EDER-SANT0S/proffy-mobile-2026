package com.rafael.proffy.ui.register

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityRegisterStepOneBinding

class RegisterStepOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepOneBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonGoBack = binding.buttonGoBack
        val textInputFistName = binding.textInputEditFirstName
        val textInputLastName = binding.textInputEditLastName
        val buttonNext = binding.buttonNext

        val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        buttonGoBack.setOnClickListener {
            goBack()
        }

        buttonNext.setOnClickListener {
            handleNextStep()
        }

        textInputFistName.addTextChangedListener {
            val fistName = textInputFistName.text?.toString()?.trim() ?: ""
            val lastName = textInputLastName.text?.toString()?.trim() ?: ""
            val validInputs = fistName.isNotEmpty() && lastName.isNotEmpty()
            setButtonState(validInputs, enabledButtonColor, disabledButtonColor)
        }

        textInputLastName.addTextChangedListener {
            val fistName = textInputFistName.text?.toString()?.trim() ?: ""
            val lastName = textInputLastName.text?.toString()?.trim() ?: ""
            val validInputs = fistName.isNotEmpty() && lastName.isNotEmpty()
            setButtonState(validInputs, enabledButtonColor, disabledButtonColor)
        }
    }

    private fun goBack() {
        finish()
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonNext.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor

        binding.buttonNext.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = if (enabled)
            ContextCompat.getColor(this, R.color.shape_01_white)
        else
            ContextCompat.getColor(this, R.color.text_complement)
        binding.buttonNext.setTextColor(textColor)
    }

    private fun handleNextStep() {
        val firstName = binding.textInputEditFirstName.text.toString()
        val lastName = binding.textInputEditLastName.text.toString()

        if (firstName.length > 2 && lastName.length > 4) {
            val intent = Intent(this, RegisterStepTwoActivity::class.java)
            intent.putExtra("firstName",firstName)
            intent.putExtra("latName",lastName)
            startActivity(intent)
        } else {
            binding.textInputEditFirstName.error = "Deve conter pelo menos 3 caracteres."
            binding.textInputEditLastName.error = "Deve conter pelo menos 5 caracteres."
        }

    }
}