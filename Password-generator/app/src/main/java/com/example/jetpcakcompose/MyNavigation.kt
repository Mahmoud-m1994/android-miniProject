package com.example.jetpcakcompose

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    val startActivity = Activity.Main.activityName
    NavHost(navController = navController, startDestination = startActivity) {
        composable(
            route = startActivity
        ) {
            Main(navController = navController)
        }
    }
}

@Composable
fun Main(navController: NavController) {
    var passwordLenght = remember { mutableStateOf(TextFieldValue()) }
    var passwordText = remember{ mutableStateOf("") }
    var showError = remember { mutableStateOf(false) }

    Column(
        modifier= container_modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly, // SpaceAround ==> space for header and footer
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = 50.sp
                    )
                ){
                    append("P")
                }
                append("assword generator")
            },
            fontSize=40.sp,
            fontFamily = FontFamily.Cursive
        )
        val scrollState = rememberScrollState()
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scrollable(orientation = Orientation.Vertical, state = scrollState)
                .offset(0.dp, (-30).dp)

        ){
            Text("Password options",
                fontFamily = FontFamily.Monospace,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            userOptions.forEach { option ->
                Row (
                    modifier = Modifier.padding(bottom = 8.dp)
                ){
                    val isChecked = remember { mutableStateOf(false) }
                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = {
                            isChecked.value = it
                            userOptions.put(option.key,it)
                        }
                    )
                    Text(option.key)
                }
            }

            TextField(
                value = passwordLenght.value,
                onValueChange = {
                    passwordLenght.value = it
                    showError.value = false
                },
                trailingIcon = {
                    if (showError.value)
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colors.error)
                },
                singleLine = true,
                placeholder = { Text("Enter password length") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        }

        if (showError.value) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                textDecoration = TextDecoration.Underline
            )
        }

        Button(
            onClick = {
                if (passwordLenght.value.text.isNotBlank()) {
                    generatePassword(passwordLenght.value.text.toInt())
                    passwordText.value= password
                }
                else showError.value = true
            },
            shape = RoundedCornerShape(40.dp)
        ) {
            Text(text = "Generate the password")
        }

        SelectionContainer() {
            Text(
                "Your password is: \n"+passwordText.value,
                fontSize=20.sp,
            )
        }
    }
}