package com.example.testnitrix.di.modules

import androidx.room.Room
import com.example.testnitrix.data.api.BASE_URL
import com.example.testnitrix.data.api.VideoApi
import com.example.testnitrix.data.local.Dao
import com.example.testnitrix.data.local.Database
import com.example.testnitrix.data.repository.RepositoryImpl
import com.example.testnitrix.domain.repository.Repository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(VideoApi::class.java) }

    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
            name = "db"
        ).build()
    }

    single<Dao> { get<Database>().videoDao() }

    single<Repository> { RepositoryImpl(get(), get()) }

}