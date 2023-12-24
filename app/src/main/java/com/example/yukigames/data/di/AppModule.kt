package com.example.yukigames.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.yukigames.data.remote.GamesAPI
import com.example.yukigames.data.repository.GameRepositoryImpl
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGamesAPI() : GamesAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GamesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideGameRepository(api : GamesAPI) : GameRepository{
        return GameRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context : Context) =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSessionmanager(preferences : SharedPreferences) = SessionManager(preferences)

}