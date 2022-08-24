package sk.sandeep.demoapidagger.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sk.sandeep.demoapidagger.data.model.RecyclerData
import sk.sandeep.demoapidagger.data.model.RecyclerList
import sk.sandeep.demoapidagger.data.remote.DemoApi
import javax.inject.Inject

class DemoRepository @Inject constructor(
    private val api: DemoApi
) {

    fun makeApiCall(query: String, liveData: MutableLiveData<List<RecyclerData>>) {
        val call: Call<RecyclerList> = api.getDataFromAPI(query)
        call.enqueue(object : Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                liveData.postValue(response.body()?.items!!)
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

}