package org.d3if4003.galerihewan2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4003.galerihewan2.hewan.Hewan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface HewanApiService {
    companion object {
        private const val BASE_URL = "https://dif.indraazimi.com/"
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        interface HewanApiService {
            @GET("listhewan.json")
            //suspend fun getHewan(): String
            suspend fun getHewan(): List<Hewan>
        }

        object HewanApi {
            val service: HewanApiService by lazy {
                retrofit.create(HewanApiService::class.java)

            }
            enum class ApiStatus { LOADING, SUCCESS, FAILED }

            fun getHewanUrl(nama: String): String {
                return BASE_URL + "hewan/$nama.jpg"
            }
        }
    }
}