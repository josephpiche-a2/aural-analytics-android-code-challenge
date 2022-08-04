package com.auralanalytics.android.challenge

import com.auralanalytics.android.challenge.api.RedditService
import com.auralanalytics.android.challenge.ui.ListViewModel
import com.auralanalytics.android.challenge.ui.PostViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

@OptIn(ExperimentalSerializationApi::class)
val appModule = module {

    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    single {
        val contentType = "application/json".toMediaType()
        val json = Json {
            encodeDefaults = false
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    single<RedditService> {
        get<Retrofit>().create()
    }

    viewModel {
        ListViewModel(
            redditApi = get(),
            subreddit = BuildConfig.SUBREDDIT
        )
    }
    viewModel { PostViewModel() }
}