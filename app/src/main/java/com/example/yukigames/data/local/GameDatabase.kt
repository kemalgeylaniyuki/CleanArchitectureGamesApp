package com.example.yukigames.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yukigames.domain.model.Game
import com.example.yukigames.domain.model.GameDetails


@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gamesDao() : GamesDao

    //Farklı thread'lerde, aynı anda, tek objeye, erişilmesi isteniyorsa singleton kullanılır.
    //Singleton

    //companion object, singleton desing pattern uygulamak için kullanılan yapıdır.
    companion object {

        //volatile : farklı thread'lerden, instance'ın güncel değerini senkronize etmek için kullanılır.
        @Volatile private var instance : GameDatabase? = null

        //thread'ler arasında güvenli instance oluşturmak için kullanılır.
        private val lock = Any()


        //invoke : singleton sınıfı çağrıldığında, çalışacak olan özel bir fonksiyondur.
        operator fun invoke(context: Context) = instance ?: kotlin.synchronized(lock){
            //instance null ise yeni database instance'ı oluşturulur.
            instance ?: database(context).also {
                instance = it
            }
        }

        private fun database(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            "database").build()

    }

}