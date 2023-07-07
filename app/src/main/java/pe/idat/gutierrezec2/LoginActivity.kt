package pe.idat.gutierrezec2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import pe.idat.gutierrezec2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tilEmail.editText?.addTextChangedListener { text ->
            val email = text.toString()
            val password = binding.tilPassword.editText?.text.toString().toIntOrNull() ?: 0
            binding.btnIngresar.isEnabled = validateEmailPassword(email, password)
        }

        binding.tilPassword.editText?.addTextChangedListener { text ->
            val email = binding.tilEmail.editText?.text.toString()
            val password = text.toString().toIntOrNull() ?: 0
            binding.btnIngresar.isEnabled = validateEmailPassword(email, password)
        }

        binding.btnIngresar.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateEmailPassword(email:String, password: Int): Boolean{
        val validateEmail = email == "ejemplo@idat.edu.pe"
        val validatePassword = password == 123456
        val validateNotEmptyEmail = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val validateNotEmptyPassword = password.toString().length == 6
        return validateEmail && validatePassword && validateNotEmptyEmail && validateNotEmptyPassword
    }
}