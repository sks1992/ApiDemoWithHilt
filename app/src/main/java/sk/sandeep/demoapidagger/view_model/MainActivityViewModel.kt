package sk.sandeep.demoapidagger.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sk.sandeep.demoapidagger.data.model.RecyclerData
import sk.sandeep.demoapidagger.repository.DemoRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: DemoRepository
) : ViewModel() {
    private var liveData: MutableLiveData<List<RecyclerData>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<RecyclerData>> {
        return liveData
    }

    fun loadListOfData() {
        repository.makeApiCall("ny", liveData)
    }
}