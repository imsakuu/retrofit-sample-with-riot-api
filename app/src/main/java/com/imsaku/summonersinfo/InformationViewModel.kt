package com.imsaku.summonersinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InformationViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    fun onSearch(summonerName: String) {
        getSummoner(summonerName)
    }

    private fun getSummoner(summonerName: String) {
        coroutineScope.launch {
            val getSummonerDeferred = Api.retrofitService.getSummoner(summonerName)

            try {
                val summoner = getSummonerDeferred.await()
                _response.value = "Summoner Name : ${summoner.name} \n" +
                        "Summoner Level : ${summoner.summonerLevel}"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InformationViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return InformationViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}