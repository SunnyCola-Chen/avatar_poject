package com.example.myapplication.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.login.repository.RetrofitClient
import com.example.myapplication.login.repository.UserRepository
import com.example.myapplication.login.vm.LoginViewModel
import com.example.myapplication.login.vm.UiState

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val apiService = RetrofitClient.apiService
        val repository = UserRepository(apiService)
        viewModel = LoginViewModel(repository)

        val usernameInput = findViewById<EditText>(R.id.etUsername)
        val passwordInput = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val statusText = findViewById<TextView>(R.id.tvStatus)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            viewModel.login(username, password)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is UiState.Loading -> statusText.text = "登录中..."
                    is UiState.Success -> statusText.text = "欢迎：${state.data.username}"
                    is UiState.Error -> statusText.text = "错误：${state.message}"
                }
            }
        }
    }
}
