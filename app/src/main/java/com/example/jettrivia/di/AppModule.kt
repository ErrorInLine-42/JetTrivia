package com.example.jettrivia.di

import android.content.Context
import com.example.jettrivia.network.QuestionAPI
import com.example.jettrivia.repository.QuestionRepository
import com.example.jettrivia.util.Constants
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

    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionAPI): QuestionRepository = QuestionRepository(api)

    @Singleton
    @Provides
    fun provideQuestionAPI(): QuestionAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context
}