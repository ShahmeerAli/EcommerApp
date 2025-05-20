package com.example.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
  var totalPrice=0;
  
    @Composable
    fun displayCartItems(){

        val context= LocalContext.current.applicationContext
        val dbHelper= DBHelperClass.getInstance(context)
        val repo= RepositoryRoom(dbHelper)
        val viewModel:MyViewModel = viewModel(factory = RepoFactory(null,repo))

        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = Color.White)){
            Row(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
                    .offset(0.dp,30.dp)
                    .background(color = Color(0x0FB62929))
                ,
                horizontalArrangement = Arrangement.Start

            ){
                Text("My Cart", style = TextStyle(fontSize= 25.sp))



            }

            Spacer(modifier = Modifier.height(20.dp).background(color = Color(0XFF800080)))

            val cartItemsState = viewModel.cartItems?.observeAsState(emptyList())
            val totalItems= cartItemsState?.value ?: emptyList()

            if(totalItems.isEmpty()){
                Column (modifier = Modifier.padding(10.dp).fillMaxHeight().fillMaxWidth().background(color = Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Image(painter = painterResource(R.drawable.emptycart), contentDescription = "cart empty", modifier = Modifier.width(200.dp).height(200.dp))
                }
            }else{
                LazyColumn (modifier = Modifier.fillMaxWidth().weight(1f).padding(8.dp).navigationBarsPadding(), verticalArrangement = Arrangement.spacedBy(5.dp)){
                    items(totalItems){
                            item->
                        DisplayCard(item,viewModel)

                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                Button(modifier = Modifier.wrapContentHeight().wrapContentWidth(), colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B2482)
                ), onClick = {

                })
                {
                    Text("Check Out", color = Color.White)
                }

                Text("Total  : ${totalPrice}", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(10.dp))


            }




        }


        }



    @Composable
    fun DisplayCard(item: EcommTable,viewModel: MyViewModel){
        var quantity by remember {
            mutableStateOf(item.quantity)
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp,5.dp)
                .padding(8.dp)
                .background(color = Color.White), verticalArrangement = Arrangement.spacedBy(5.dp)



        ){
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(2.dp,Color.White),
                colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
                shape = RoundedCornerShape(10.dp),

            ){
                Row(modifier = Modifier.fillMaxWidth()){
                    AsyncImage(
                        model = item.image,
                        contentDescription = null,
                        modifier = Modifier.height(100.dp).width(120.dp).padding(5.dp)
                    )



                    Column (modifier = Modifier
                        .padding(8.dp)){
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.offset(180.dp)){
                            Icon(painter = painterResource(R.drawable.cancel_svgrepo_com),"cancelBt", tint = Color.Red, modifier = Modifier.clickable{
                                viewModel.deleteItemCart(item.id)

                            })
                        }

                        Text(item.categoyr)
                        Text("quantity : ${quantity}")





                        Spacer(modifier = Modifier.height(5.dp))
                        Row(modifier = Modifier.fillMaxWidth()){
                            Icon(painter = painterResource(R.drawable.add),"add bt", tint = Color.Green, modifier = Modifier.clickable{
                                quantity++

                                    viewModel.updateQuantity(quantity,item.id)


                            })
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(painter = painterResource(R.drawable.subtract_circle_minus_remove_svgrepo_com),"subtractBt", tint = Color.Red, modifier = Modifier.clickable{
                                if(quantity>1){
                                    quantity--
                                        viewModel.updateQuantity(quantity,item.id)


                                }

                            })
                        }

                    }





                }

            }

        }

    }
}