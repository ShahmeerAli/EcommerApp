package com.example.ecommerceapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            EcommerceAppTheme {
                         MyBottomAppBar()
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomAppBar(){

    var navController=rememberNavController()
    var bottomSheet= rememberModalBottomSheetState()

      var selected= remember{
         mutableStateOf(R.drawable.home_1_svgrepo_com)
      }

    var sheetState by remember {
        mutableStateOf(false)
    }

   Scaffold (
       bottomBar = {
           BottomAppBar(
               containerColor = Color(0xFF442C9A)
           ){
               IconButton(onClick = {
                   selected.value=R.drawable.home_1_svgrepo_com
                   navController.navigate(Screens.HomeScreen.screen)
                   {
                       popUpTo(0)
                   }
               }, modifier = Modifier.weight(1f)) {
                   Icon(painterResource(R.drawable.home_1_svgrepo_com),"Home", tint = if(selected.value==R.drawable.home_1_svgrepo_com) Color(
                       0xFFF8F6F8
                   ) else Color.White)
               }
               IconButton(onClick = {
                   selected.value=R.drawable.search_svgrepo_com
                   navController.navigate(Screens.SearchScreen.screen)
                   {
                       popUpTo(0)
                   }
               }, modifier = Modifier.weight(1f)
               ) {
                   Icon(painterResource(R.drawable.search_svgrepo_com),"Search", tint = if(selected.value==R.drawable.search_svgrepo_com) Color(
                       0xFFF5EFF5
                   ) else Color.White)
               }
               IconButton(onClick = {
                   selected.value=R.drawable.cart_large_minimalistic_svgrepo_com
                   navController.navigate(Screens.CartScreen.screen)
                   {
                       popUpTo(0)
                   }
               }, modifier = Modifier.weight(1f)
               ) {
                   Icon(painterResource(R.drawable.cart_large_minimalistic_svgrepo_com),"cart",tint=if(selected.value==R.drawable.cart_large_minimalistic_svgrepo_com) Color(
                       0xFAEEE8EE
                   ) else Color.White)

               }
               IconButton(onClick = {
                   selected.value=R.drawable.settings_minimalistic_svgrepo_com
                   navController.navigate(Screens.SettingsScreen.screen)
                   {
                       popUpTo(0)
                   }
               }, modifier = Modifier.weight(1f)
               ) {
                   Icon(painterResource(R.drawable.settings_minimalistic_svgrepo_com),"settings", tint = if(selected.value==R.drawable.settings_minimalistic_svgrepo_com) Color(
                       0xFFEFEFEF
                   ) else Color.White)
               }
           }
       }


   ){innerPadding->
       NavHost(navController, startDestination = Screens.HomeScreen.screen, modifier = Modifier.padding(innerPadding), builder = {
           composable(Screens.HomeScreen.screen) {
               HomeScreen().Home(navController)
           }
           composable(Screens.SearchScreen.screen) {
               SearchScreen().searchBar()
           }
           composable(Screens.CartScreen.screen) {
               CartScreen().displayCartItems()
           }
           composable(Screens.SettingsScreen.screen) {
               SettingsScreen()
           }
           composable(Screens.MenClothing.screen) {
               MenClothing().DisplayClothingMen()
           }


       })
   }

}



