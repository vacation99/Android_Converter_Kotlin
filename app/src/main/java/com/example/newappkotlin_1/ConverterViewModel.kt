package com.example.newappkotlin_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappkotlin_1.db.Currency
import com.example.newappkotlin_1.repository.MainRepository
import com.example.newappkotlin_1.util.Event
import com.example.newappkotlin_1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val hashMap: HashMap<String, Double> = HashMap(34)

    private val _liveDataResult = MutableLiveData<String>()
    private val _liveDataErrorMessage = MutableLiveData<Event<String>>()

    val getLiveDataResult: LiveData<String>
        get() = _liveDataResult
    val getLiveDataErrorMessage: LiveData<Event<String>>
        get() = _liveDataErrorMessage

    init {
        viewModelScope.launch {
            when (val response = mainRepository.getResponseFromRetrofit()) {
                is Resource.Error -> _liveDataErrorMessage.value = Event(response.message.toString())
                is Resource.Success -> {
                    if (response.data != null) {
                        val rates = response.data.rates

                        hashMap["RUB"] = 1.0
                        hashMap["AUD"] = rates.AUD
                        hashMap["AZN"] = rates.AZN
                        hashMap["GBP"] = rates.GBP
                        hashMap["AMD"] = rates.AMD
                        hashMap["BYN"] = rates.BYN
                        hashMap["BGN"] = rates.BGN
                        hashMap["BRL"] = rates.BRL
                        hashMap["HUF"] = rates.HUF
                        hashMap["HKD"] = rates.HKD
                        hashMap["DKK"] = rates.DKK
                        hashMap["USD"] = rates.USD
                        hashMap["EUR"] = rates.EUR
                        hashMap["INR"] = rates.INR
                        hashMap["KZT"] = rates.KZT
                        hashMap["CAD"] = rates.CAD
                        hashMap["KGS"] = rates.KGS
                        hashMap["CNY"] = rates.CNY
                        hashMap["MDL"] = rates.MDL
                        hashMap["NOK"] = rates.NOK
                        hashMap["PLN"] = rates.PLN
                        hashMap["RON"] = rates.RON
                        hashMap["XDR"] = rates.XDR
                        hashMap["SGD"] = rates.SGD
                        hashMap["TJS"] = rates.TJS
                        hashMap["TRY"] = rates.TRY
                        hashMap["TMT"] = rates.TMT
                        hashMap["UZS"] = rates.UZS
                        hashMap["UAH"] = rates.UAH
                        hashMap["CZK"] = rates.CZK
                        hashMap["SEK"] = rates.SEK
                        hashMap["CHF"] = rates.CHF
                        hashMap["ZAR"] = rates.ZAR
                        hashMap["KRW"] = rates.KRW
                        hashMap["JPY"] = rates.JPY
                    }
                }
            }
        }
    }

    fun convert(et_from: String, et_to: String, et_count: String) {
        if (hashMap.isEmpty()) {
            _liveDataErrorMessage.value = Event("No Internet")
        } else {
            val count = et_count.toDouble()
            val from = hashMap[et_from]
            val to = hashMap[et_to]

            if (from != null && to != null) {
                val result = (to / from) * count

                _liveDataResult.value = String.format("%.4f", result)
            }
        }
    }

    fun insertInDb(result: String) {
        viewModelScope.launch {
            mainRepository.insertData(Currency(result))
        }
    }
}