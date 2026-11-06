package com.rafael.proffy.ui.forgotPassword

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityForgotPasswordBinding
import com.rafael.proffy.ui.finallyComponent.FinallyActivity
import com.rafael.proffy.utils.ValidationUtils

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val keyboard = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(
                systemBars.left,
                0,
                systemBars.right,
                maxOf(systemBars.bottom, keyboard.bottom)
            )
            if (keyboard.bottom > 0) {
                binding.scrollViewForgotPassword.post {
                    binding.scrollViewForgotPassword.fullScroll(View.FOCUS_DOWN)
                }
            }
            insets
        }

        val buttonGoBack = binding.buttonGoBack
        val textInputEmail = binding.textInputEditEmail
        val buttonSend = binding.buttonSend

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        buttonGoBack.setOnClickListener {
            goBack()
        }

        buttonSend.setOnClickListener {
            handleSend()
        }

        textInputEmail.addTextChangedListener {
            val email = textInputEmail.text?.toString()?.trim() ?: ""
            val validEmail = ValidationUtils.isValidEmail(email)
            showEmailError(email, validEmail)
            setButtonState(validEmail, enabledButtonColor, disabledButtonColor)
        }
    }

    private fun goBack() {
        finish()
    }

    private fun showEmailError(email: String, validEmail: Boolean) {
        binding.textInputEditEmail.error = if (email.isNotEmpty() && !validEmail) {
            getString(R.string.forgot_password_error_invalid_email)
        } else {
            null
        }
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonSend.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor

        binding.buttonSend.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = if (enabled)
            ContextCompat.getColor(this, R.color.shape_01_white)
        else
            ContextCompat.getColor(this, R.color.text_complement)
        binding.buttonSend.setTextColor(textColor)
        binding.buttonSend.setText(
            if (enabled) R.string.forgot_password_button_send else R.string.forgot_password_button_default
        )
    }

    private fun handleSend() {
        val email = binding.textInputEditEmail.text.toString().trim()

        if (ValidationUtils.isValidEmail(email)) {
            sendDoneForgotPassword()
        } else {
            binding.textInputEditEmail.error = getString(R.string.forgot_password_error_invalid_email)
        }
    }

    private fun sendDoneForgotPassword() {
        val intent = Intent(this, FinallyActivity::class.java)
        intent.putExtra("textViewTitleFinally", getString(R.string.forgot_password_done_title))
        intent.putExtra("textViewSubtitle", getString(R.string.forgot_password_done_subtitle))
        intent.putExtra("titleButton", getString(R.string.forgot_password_done_button))

        startActivity(intent)
        finish()
    }
}
