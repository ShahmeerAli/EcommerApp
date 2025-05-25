package com.example.ecommerceapp

import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MenClothing {


    @Composable
    fun DisplayClothingMen(){
        val context=LocalContext.current.applicationContext
        val coroutineScope = rememberCoroutineScope()
        val repoClass= RepositoryClass()
        val dbHelper= DBHelperClass.getInstance(context)
        val repo= RepositoryRoom(dbHelper)
        val viewModel: MyViewModel= viewModel(factory = RepoFactory(repoClass,repo))
        Column (){
            Row (modifier = Modifier.offset(0.dp,10.dp).padding(5.dp)){
                Text("Men Section", modifier = Modifier.padding(5.dp), color = Color.Black, style = TextStyle(fontSize = 25.sp), fontWeight = FontWeight.Medium)

            }
            Spacer(modifier = Modifier.height(10.dp))
            MenCard(viewModel,coroutineScope)
        }



    }


    @Composable
    fun MenCard(viewModel: MyViewModel,coroutineScope: CoroutineScope){

        LaunchedEffect(Unit) {
            viewModel.getMenData("Home")
        }

        val totalItems = viewModel.dataMen.observeAsState(initial = emptyList()).value

        LazyVerticalGrid(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.SpaceEvenly,
            columns = GridCells.Fixed(2)

        ) {
            itemsIndexed(totalItems) {index,item->
                DisplayCard(item,viewModel, coroutineScope)

            }


        }

    }


    @Composable
    fun DisplayCard(totalItems: DataHome,viewModel: MyViewModel,coroutineScope: CoroutineScope){
        val context= LocalContext.current.applicationContext
        Card(
            modifier = Modifier.padding(10.dp).width(300.dp).height(280.dp).border(width = 2.dp, color = Color.Transparent).shadow(elevation = 10.dp), shape = RoundedCornerShape(2.dp), colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            AsyncImage(
                model=totalItems.imageUrl,
                contentDescription = totalItems.category.toString(),
                contentScale= ContentScale.Crop,
                placeholder = painterResource(R.drawable.men_in_their_20s_and_30s_face_svgrepo_com),
                modifier = Modifier.fillMaxWidth().height(230.dp)
            )
            Row(){
                Icon(painter = painterResource(R.drawable.cart_1_svgrepo_com),"cart", modifier = Modifier.padding(5.dp).clickable{
                    coroutineScope.launch{
                        viewModel.insert(EcommTable(totalItems.category.toString(),totalItems.imageUrl,0,1,false
                        ))
                    }
                    Toast.makeText(context,"Item Added to Cart", Toast.LENGTH_SHORT).show()

                })
                Spacer(modifier = Modifier.width(5.dp))
                Icon(painter = painterResource(R.drawable.heart_svgrepo_com__1_),"cart", modifier = Modifier.padding(5.dp))
            }


        }

    }

}