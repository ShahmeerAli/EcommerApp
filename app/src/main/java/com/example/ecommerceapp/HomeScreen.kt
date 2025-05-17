package com.example.ecommerceapp

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreen {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

    fun Home(navController: NavController){
        val context= LocalContext.current.applicationContext
        val repoFirebase= RepositoryClass()
        val viewmodel: MyViewModel = viewModel(factory = RepoFactory(repoFirebase))

        Scaffold (
            topBar = { TopAppBar(
                title = {Text("")}, colors = TopAppBarDefaults.mediumTopAppBarColors(
                    titleContentColor = Color(0xFF800080)
                ),

                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.height(30.dp),
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Menu Clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(painterResource(R.drawable.menu_svgrepo_com), "Menu")
                    }
                }, actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(painterResource(R.drawable.heart_svgrepo_com__1_),"cart")
                    }
                }
            )
            }
        )
        {
            innerpadding->
            Column(modifier = Modifier.fillMaxWidth().background(color = Color.White)
                .padding(
              innerpadding
            )
            ) {
                
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp).border(0.dp, color = Color.Transparent, shape = RoundedCornerShape(3.dp)), horizontalArrangement = Arrangement.Center){
                    Image(painterResource(R.drawable.bannerimage),"banner", modifier = Modifier.fillMaxWidth())

                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    Text("Shop by Category", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(5.dp))
                    Text("See All", style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(7.dp).clickable(onClick = {

                    }))
                }

                Spacer(modifier= Modifier.height(12.dp))
                ShopCategory(navController)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Trending Now", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(5.dp))
                Spacer(modifier = Modifier.height(20.dp))
                TrendingItems(viewmodel)
            }







        }

    }



    @Composable
    fun ShopCategory(navController: NavController){
        var category=arrayOf(
            "Men",
             "Women",
            "Kids",
            "Accessory",
            "All"
        )
        
        var images=arrayOf(
            R.drawable.men_in_their_20s_and_30s_face_svgrepo_com,
            R.drawable.women_svgrepo_com,
            R.drawable.kids_couple_svgrepo_com,
            R.drawable.clothes_hanger_svgrepo_com,
            R.drawable.cart_large_minimalistic_svgrepo_com
        )


        LazyRow(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
               var itemsCount=images.size

               items (itemsCount){
                   RowItems(
                          category,
                          images,
                          itemIndex = it,
                          navController
                   )
               }
        }
    }

    @Composable
    fun RowItems(category: Array<String>,images: Array<Int>,itemIndex:Int,navController: NavController){
          Column(modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(10.dp).clickable{
              if(category[itemIndex]=="Men"){
                  navController.navigate(Screens.MenClothing.screen)
              } else {

              }
          }

          , verticalArrangement = Arrangement.Center) {
                 Image(painterResource(images[itemIndex]),"Images", modifier = Modifier.size(70.dp).clip(
                     CircleShape
                 ).padding(5.dp).border(0.dp, color = Color.Transparent, shape = RoundedCornerShape(50.dp)))
                 Spacer(modifier = Modifier.height(5.dp))
                  Text(category[itemIndex], style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(5.dp).offset(3.dp,1.dp))


          }
    }



    @Composable
    fun TrendingItems(
        viewModel: MyViewModel
    ) {
        LaunchedEffect(Unit) {
            Log.d("Launch Activity", "Fetching data for category: ")
            viewModel.getDataHome("Home")
        }

        val totalItems by viewModel.data.observeAsState(initial = emptyList())

        Log.d("Items Fetched", "Total items: ${totalItems.size}")

        if (totalItems.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Nothing to Display", style = TextStyle(fontSize = 16.sp))

            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(totalItems) { index, item ->
                    TrendingItemsCard(item,viewModel)
                }
            }
        }
    }

    @Composable
    fun TrendingItemsCard(item: DataHome,viewModel: MyViewModel){
        val context=LocalContext.current.applicationContext
        Log.d("Images","Fetching")

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.padding(10.dp).width(300.dp).wrapContentHeight()
                    .border(2.dp, color = Color.Transparent).shadow(elevation = 10.dp), shape = RoundedCornerShape(10.dp)
            ){
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = item.category.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )



                    Icon(painterResource(R.drawable.cart_1_svgrepo_com), contentDescription = "cart", modifier = Modifier.padding(10.dp).clickable{
                        addedtoCart(item.imageUrl, item.category.toString(),viewModel)
                        Toast.makeText(context,"Item Added to Cart", Toast.LENGTH_SHORT).show()
                    })
                    Icon(painter = painterResource(R.drawable.heart_svgrepo_com__1_), contentDescription = "favourites", modifier = Modifier.padding(10.dp).clickable{

                    })

                }
                }








    }


    @SuppressLint("CoroutineCreationDuringComposition")
    fun addedtoCart(item: String, category: String, viewModel: MyViewModel){

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insert(ecommDao = EcommTable(category,item,0,false))

        }
    }




}