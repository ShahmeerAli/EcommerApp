package com.example.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

class CartScreen {


    @Composable
    fun displayCartItems(){
        val context= LocalContext.current.applicationContext
        val dbHelper= DBHelperClass.getInstance(context)
        val repo= RepositoryRoom(dbHelper)
        val viewModel:MyViewModel = viewModel(factory = RepoFactory(null,repo))

        fetchCartItems(viewModel)

    }


    @Composable
    fun fetchCartItems(viewModel: MyViewModel){
        val cartItemsState = viewModel.cartItems?.observeAsState(emptyList())
        val totalItems = cartItemsState?.value ?: emptyList()

        if(totalItems.isEmpty()){
            Column (modifier = Modifier.padding(10.dp).fillMaxHeight().fillMaxWidth().background(color = Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Image(painter = painterResource(R.drawable.emptycart), contentDescription = "cart empty", modifier = Modifier.width(200.dp).height(200.dp))
            }
        }else{
            LazyColumn (modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight()){
                items(totalItems){
                    item->
                    DisplayCard(item,viewModel)

                }
            }
        }


    }

    @Composable
    fun DisplayCard(item: EcommTable,viewModel: MyViewModel){
        Row(modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight()){
            Card(modifier = Modifier.fillMaxWidth().height(100.dp)){
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier.height(40.dp).width(40.dp)
                )

                Column (modifier = Modifier.padding(10.dp)){
                    Text(item.categoyr)
                }

            }
        }
    }
}