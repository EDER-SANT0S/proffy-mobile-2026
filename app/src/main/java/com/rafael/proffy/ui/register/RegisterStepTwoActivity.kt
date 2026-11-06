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
import com.rafael.proffy.databinding.ActivityRegisterStepTwoBinding
import com.rafael.proffy.ui.finallyComponent.FinallyActivity
import com.rafael.proffy.utils.ValidationUtils
import org.intellij.lang.annotations.Pattern

class RegisterStepTwoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterStepTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepTwoBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonGoBackStepOne = binding.buttonGoBack
        val textInputEmail = binding.textInputEditEmail
        val textInputPassword = binding.textInputEditPassword
        val buttonFinish = binding.buttonFinish

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")

        println("Primeiro nome: $firstName, Sobrenome: $lastName")

        buttonGoBackStepOne.setOnClickListener {
            goBack()
        }

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        textInputEmail.addTextChangedListener {
            val fistName = textInputEmail.text?.toString()?.trim() ?: ""
            val lastName = textInputPassword.text?.toString()?.trim() ?: ""
            val validInputs = fistName.isNotEmpty() && lastName.isNotEmpty()
            setButtonState(validInputs, enabledButtonColor, disabledButtonColor)
        }

        textInputPassword.addTextChangedListener {
            val fistName = textInputEmail.text?.toString()?.trim() ?: ""
            val lastName = textInputPassword.text?.toString()?.trim() ?: ""
            val validInputs = fistName.isNotEmpty() && lastName.isNotEmpty()
            setButtonState(validInputs, enabledButtonColor, disabledButtonColor)
        }
    }

    private fun goBack() {
        finish()
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonFinish.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor

        binding.buttonFinish.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = if (enabled)
            ContextCompat.getColor(this, R.color.shape_01_white)
        else
            ContextCompat.getColor(this, R.color.text_complement)
        binding.buttonFinish.setTextColor(textColor)
    }

    private fun handleNextStep() {
        val email = binding.textInputEditEmail.text.toString()
        val password = binding.textInputEditPassword.text.toString()

        if (ValidationUtils.isValidEmail(email) && password.length > 4) {
            //val intent = Intent(this, RegisterStepTwoActivity::class.java)
            //startActivity(intent)
        } else {
            binding.textInputEditEmail.error = "Deve conter pelo menos 3 caracteres."
            binding.textInputEditPassword.error = "Deve conter pelo menos 5 caracteres."
        }

    }

    private fun sendDoneRegister() {
        val intent = Intent(this, FinallyActivity::class.java)
        intent.putExtra("textViewTitleFinally", "Cadastro\nconcluído!")
        intent.putExtra("textViewSubtitleFinally", "Agora você faz parte da\nplataforma da Proffy")
        intent.putExtra("titleButton", "Fazer login")

        startActivity(intent)
        finish()
    }
    }
