package sk.sandeep.demoapidagger.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sk.sandeep.demoapidagger.data.model.RecyclerList

interface DemoApi {

    @GET("repositories")
    fun getDataFromAPI(
        @Query("q") query: String
    ): Call<RecyclerList>
}