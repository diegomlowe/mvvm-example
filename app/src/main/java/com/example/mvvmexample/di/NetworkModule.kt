package com.example.mvvmexample.di

import com.example.mvvmexample.base.Constants
import com.example.mvvmexample.data.network.ApiClient
import com.example.mvvmexample.data.network.datasource.RemoteDataSource
import com.example.mvvmexample.data.network.datasource.RemoteDataSourceImpl
import com.example.mvvmexample.data.network.repoimpl.PostsRepoImpl
import com.example.mvvmexample.data.network.repoimpl.UsersRepoImpl
import com.example.mvvmexample.domain.repos.PostsRepo
import com.example.mvvmexample.domain.repos.UsersRepo
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideDataSource(dataSourceImpl: RemoteDataSourceImpl): RemoteDataSource {
        return dataSourceImpl
    }

    @Singleton
    @Provides
    fun provideUsersRepo(usersRepoImpl: UsersRepoImpl): UsersRepo {
        return usersRepoImpl
    }

    @Singleton
    @Provides
    fun providePostsRepo(postsRepoImpl: PostsRepoImpl): PostsRepo {
        return postsRepoImpl
    }
}