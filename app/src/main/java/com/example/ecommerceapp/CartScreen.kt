package com.example.ecommerceapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

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

        LazyColumn(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.SpaceEvenly) {
//            items() {
//
//            }


        }
    }
}