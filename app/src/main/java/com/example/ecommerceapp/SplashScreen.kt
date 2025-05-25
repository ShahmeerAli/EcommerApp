package com.example.ecommerceapp

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController
import kotlinx.coroutines.delay

class SplashScreen {


    @Composable
    fun showSplashScreenPreview(navController: NavController){
        LaunchedEffect(Unit) {
            delay(4000)
        }

        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = Color(
            0xFF6D50B1
        )
        )){

        }


    }
}