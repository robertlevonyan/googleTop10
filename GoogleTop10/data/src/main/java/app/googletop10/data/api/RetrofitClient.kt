package app.googletop10.data.api

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {
    companion object {
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit = retrofit ?: Retrofit.Builder().run {
            baseUrl("https://www.googleapis.com/")
            addConverterFactory(GsonConverterFactory.create())
            client(getOkHttpClient())
            build().also { retrofit = it }
        }

        private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().run {
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
            connectionSpecs(
                listOf(
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS,
                    ConnectionSpec.RESTRICTED_TLS,
                    ConnectionSpec.CLEARTEXT
                )
            )
            build()
        }
    }
}
