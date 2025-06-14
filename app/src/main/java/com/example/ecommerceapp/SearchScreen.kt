package com.example.ecommerceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SearchScreen {

    @Composable
    fun searchScreen(){
        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = Color.White)){
                  searchBar()
        }

    }

    @Composable
    fun searchBar(){
        val search= remember{
            mutableStateOf(" ")
        }

        Column() {
            Column (modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value=it
                    }, label = {Text("Search Bar", modifier = Modifier.padding(3.dp))},
                    placeholder = { Text("Search Here") },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.Blue,
                        focusedTextColor = Color.White,
                        focusedContainerColor =  Color(0xFF9F81D0),
                        unfocusedContainerColor = Color(0xFFD4C1F3),
                        focusedPlaceholderColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        focusedLabelColor = Color.White

                    ),
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(modifier = Modifier.wrapContentHeight().wrapContentHeight().padding(5.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8860F6)
                ), onClick = {

                }) {
                    Text("Search", style = TextStyle(fontSize = 16.sp), color = Color.White)
                }

            }

            Text("Related Results", modifier = Modifier.padding(8.dp), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }


    }
}