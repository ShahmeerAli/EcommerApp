package com.example.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

class CartScreen {
      val counter=0;
      val totalPrice=0;

    @Composable
    fun displayCartItems(){
        val context= LocalContext.current.applicationContext
        val dbHelper= DBHelperClass.getInstance(context)
        val repo= RepositoryRoom(dbHelper)
        val viewModel:MyViewModel = viewModel(factory = RepoFactory(null,repo))

        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text("My Cart", style = TextStyle(fontSize= 25.sp))
            Text("Total Price : ", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(5.dp))


        }

         Spacer(modifier = Modifier.height(20.dp))

            val cartItemsState = viewModel.cartItems?.observeAsState(emptyList())
            val totalItems= cartItemsState?.value ?: emptyList()

            if(totalItems.isEmpty()){
                Column (modifier = Modifier.padding(10.dp).fillMaxHeight().fillMaxWidth().background(color = Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Image(painter = painterResource(R.drawable.emptycart), contentDescription = "cart empty", modifier = Modifier.width(200.dp).height(200.dp))
                }
            }else{

                LazyColumn (modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(10.dp)){
                    items(totalItems){
                            item->
                        DisplayCard(item)

                    }
                }
            }




        }



    @Composable
    fun DisplayCard(item: EcommTable){


        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(0.dp,50.dp)
                .padding(5.dp)


        ){
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(2.dp,Color.LightGray),
                colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ){
                Row(modifier = Modifier.fillMaxWidth()){
                    AsyncImage(
                        model = item.image,
                        contentDescription = null,
                        modifier = Modifier.height(120.dp).width(140.dp).padding(5.dp)
                    )

                    Column (modifier = Modifier
                        .padding(10.dp)){
                        Text(item.categoyr)
                    }





                }

            }

        }

    }
}