package com.example.jetpcakcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpcakcompose.ui.theme.JetpcakComposeTheme
import java.security.SecureRandom

val TAG = "Lifecycle"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContent {
            MyNavigation()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}

var password = ""
var errorMessage = "Password sdf length cannot be 0"
var userOptions = mutableMapOf<String,Boolean>(
    "Capital letters" to false,
    "Small letters" to false,
    "Numbers" to false,
    "Characters (£%&?@#)" to false
)



public fun generatePassword(length : Int, ) : Unit {
    Log.d("generatePassword", "$length ")

    val letters : String = "abcdefghijklmnopqrstuvwxyz"
    val uppercaseLetters : String = letters.uppercase()
    val numbers : String = "0123456789"
    val characters : String = "!£%&?@#=+/ęäøæ*"
    var result : String = ""
    var iterator : Int = 0


    if ( userOptions.get("Capital letters") == true) result += uppercaseLetters
    if ( userOptions.get("Small letters") == true) result += letters
    if ( userOptions.get("Numbers") == true) result += numbers
    if ( userOptions.get("Characters (£%&?@#)") == true) result += characters
    val rnd = SecureRandom.getInstance("SHA1PRNG")
    val sb = StringBuilder(length)

    if (length > 5 && result.isNotBlank()) {

        while (iterator < length) {
            val randomInt : Int = rnd.nextInt(result.length)
            sb.append(result[randomInt])
            Log.d("TAG", "generatePassword: "+result[randomInt]+ " randomInt= "+randomInt)
            iterator++
        }
        Log.d("TAG", "generatePassword: result = "+ result)
        Log.d("TAG", "generatePassword: rnd = "+ rnd)
        password = sb.toString()
        Log.d("generatePassword", password)
    } else Log.d("generatePassword", " length = $length")


}
var container_modifier = Modifier
    .fillMaxSize()
    .background(Color.White)

@Preview(
    name = "First Jetpack Compose project",
    showBackground = true)
@Composable
fun DefaultPreview() {
    JetpcakComposeTheme {
        MyNavigation()
    }
}