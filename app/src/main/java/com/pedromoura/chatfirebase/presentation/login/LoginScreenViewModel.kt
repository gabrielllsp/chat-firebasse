package com.pedromoura.chatfirebase.presentation.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val context: Context) : ViewModel() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    var userId: String = ""
    var username: String = ""
    var password: String = ""

    fun saveLoginCredentials(username: String, password: String) {
        userId = if (username == "userone") {
            "1"
        } else {
            "2"
        }

        viewModelScope.launch {
            with(sharedPreferences.edit()) {
                putString("userId", userId)
                putString("username", username)
                putString("password", password)
                apply()
            }
        }
    }
}

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            return LoginScreenViewModel(context) as T
        }
        throw IllegalArgumentException("ViewModel")
    }
}