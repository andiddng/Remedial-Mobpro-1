package org.d3if4003.galerihewan2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if4003.galerihewan2.R
import org.d3if4003.galerihewan2.hewan.Hewan
import org.d3if4003.galerihewan2.network.HewanApiService

class MainViewModel : ViewModel() {
    private val data = MutableLiveData<List<Hewan>>()
    private val status = MutableLiveData<HewanApiService.Companion.HewanApi.ApiStatus>()

    init {
        retrieveData()

    }
    private fun retrieveData() {
        viewModelScope.launch {
            status.value = HewanApiService.Companion.HewanApi.ApiStatus.LOADING
            try {
                data.value = HewanApiService.Companion.HewanApi.service.getHewan()
                status.value = HewanApiService.Companion.HewanApi.ApiStatus.SUCCESS

            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = HewanApiService.Companion.HewanApi.ApiStatus.FAILED
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data
    fun getStatus(): LiveData<HewanApiService.Companion.HewanApi.ApiStatus> = status

}