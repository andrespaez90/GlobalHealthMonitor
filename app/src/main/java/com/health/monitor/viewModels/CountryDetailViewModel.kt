package com.health.monitor.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.health.monitor.network.api.ApiMonitor
import com.health.monitor.network.models.CountryHistoryResponse
import javax.inject.Inject

class CountryDetailViewModel @Inject constructor(private val monitorApi: ApiMonitor) : AndroidViewModel() {

    private val countryInformation = MutableLiveData<CountryHistoryResponse>()

    fun updateInformation(countryName: String) {
        disposables.add(monitorApi.getHistoryByCountry(countryName)
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({ countryInformation.postValue(it) }, ::showServiceError)
        )
    }


    /**
     * LiveData
     */

    fun onDataChange(): LiveData<CountryHistoryResponse> = countryInformation

}