package com.pedromoura.chatfirebase.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pedromoura.chatfirebase.presentation.navigation.Screen
import com.pedromoura.chatfirebase.ui.theme.ChatFirebaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginScreenViewModel: LoginScreenViewModel = viewModel(
        factory = LoginViewModelFactory(
            LocalContext.current
        )
    ), navController: NavController
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tela de Login",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 50.dp)
        )
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = {
                Text(
                    text = "Usuário",
                    modifier = modifier,
                    textAlign = TextAlign.Start
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Senha",
                    modifier = modifier,
                    textAlign = TextAlign.Start
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 56.dp, bottom = 8.dp)
            .height(50.dp),
            onClick = {
                loginScreenViewModel.saveLoginCredentials(userName, password)
                Screen.Chat.route
            }) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    ChatFirebaseTheme {
        LoginScreen(
            navController = rememberNavController()
        )
    }
}