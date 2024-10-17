package com.example.swiggyapplication.data

import com.example.swiggyapplication.domain.ICityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {


    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) : WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(repoImpl: CityRepoImpl) : ICityRepository {
        return repoImpl
    }


}