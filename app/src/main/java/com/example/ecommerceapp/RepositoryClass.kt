package com.example.ecommerceapp

import android.R
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class RepositoryClass{
    val db= Firebase.firestore


    fun FetchDataHome(category: String,callback:(List<DataHome>)->Unit){
        Log.d("Repository", "Fetching data for category: $category")
          db.collection("ecomm").whereArrayContains("category",category).get()
              .addOnSuccessListener {
                  document->
                  Log.d("Repository", "Size of data: ${document.size()}")
                   val datahome=mutableListOf<DataHome>()

                  for(i in document){
                      val image=i.getString("ImageUrl")?:" "
                      Log.d("Fetched Image","${image}")
                      val categorylist=i.get("category") as List<String>?:emptyList()
                      Log.d("Fetched Catogory","${categorylist}")
                      datahome.add(DataHome(categorylist,image))

                  }


                  callback(datahome)

              }
              .addOnFailureListener {error->
                   Log.e("Fetch Error","${error.message}")

              }

    }

    fun getMenData(category:String,callback: (List<DataHome>) -> Unit){
        db.collection("ecomm").whereArrayContains("category",category).get().addOnSuccessListener {
            document->
            val datahome=mutableListOf<DataHome>()
            for(i in document){

                var image=i.getString("ImageUrl")?:" "
                var category=i.get("category") as List<String>?:emptyList()
                datahome.add(DataHome(category,image))



            }

            callback(datahome)
        }
            .addOnFailureListener {error->
                Log.e("Men Error","${error.message}")

            }
    }




}