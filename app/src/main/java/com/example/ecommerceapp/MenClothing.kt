package com.example.ecommerceapp

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class MenClothing {


    @Composable
    fun DisplayClothingMen(){
        val repoClass= RepositoryClass()
        val viewModel: MyViewModel= viewModel(factory = RepoFactory(repoClass))
        MenCard(viewModel)


    }


    @Composable
    fun MenCard(viewModel: MyViewModel){

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
                DisplayCard(item)

            }


        }

    }


    @Composable
    fun DisplayCard(totalItems: DataHome){
        Card(
            modifier = Modifier.padding(10.dp).width(300.dp).height(300.dp).border(width = 2.dp, color = Color.Transparent).background(color = Color.White).shadow(elevation = 10.dp), shape = RoundedCornerShape(2.dp)
        ){
            AsyncImage(
                model=totalItems.imageUrl,
                contentDescription = totalItems.category.toString(),
                contentScale= ContentScale.Crop,
                placeholder = painterResource(R.drawable.men_in_their_20s_and_30s_face_svgrepo_com),
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )
        }
    }

}