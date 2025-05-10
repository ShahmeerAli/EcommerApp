package com.example.ecommerceapp

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [EcommTable::class],
    exportSchema = false,
    version = 1
)
abstract class DBHelperClass: RoomDatabase() {

    abstract fun EcommmDao(): EcommDao

    companion object{
        val DB_NAME="ecomm_db"
        @Volatile
        private var INSTANCE: DBHelperClass?=null
        fun getInstance(context:Context) : DBHelperClass{
             return INSTANCE?:synchronized(this) {
                 val instance= Room.databaseBuilder(context, DBHelperClass::class.java,DB_NAME).fallbackToDestructiveMigration().build()
                 INSTANCE=instance
                 return instance
             }
        }
    }


}