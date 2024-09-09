package com.gabriel.authentication.login

sealed class Screen(val route: String) {

    object Chat : Screen("chat")
}