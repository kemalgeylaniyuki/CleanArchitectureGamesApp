package com.example.yukigames.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.yukigames.data.local.Converters
import com.example.yukigames.data.local.GameDatabase
import com.example.yukigames.data.local.GamesDao
import com.example.yukigames.data.remote.GamesAPI
import com.example.yukigames.data.repository_impl.GameRepositoryImpl
import com.example.yukigames.domain.repository.GameRepository
import com.example.yukigames.util.Constants
import com.example.yukigames.util.Constants.BASE_URL
import com.example.yukigames.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGamesAPI() : GamesAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GamesAPI::class.java)
    }

    @Provides
    fun provideGameRepository(api : GamesAPI, database: GameDatabase) : GameRepository{
        return GameRepositoryImpl(api,database)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context : Context) =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    fun provideSessionmanager(preferences : SharedPreferences) = SessionManager(preferences)

    @Provides
    fun provideDatabase(context: Application) : GameDatabase{
        return GameDatabase.invoke(context)
    }

    @Provides
    fun provideDao(database : GameDatabase) : GamesDao{
        return database.gamesDao()
    }

    @Provides
    fun provideConverters(): Converters {
        return Converters()
    }


}