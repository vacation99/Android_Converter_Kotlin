package com.example.newappkotlin_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappkotlin_1.db.Currency
import com.example.newappkotlin_1.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _liveDataDB = MutableLiveData<List<Currency>>()

    val getLiveDataDB: LiveData<List<Currency>>
        get() = _liveDataDB

    init {
        viewModelScope.launch {
            _liveDataDB.postValue(mainRepository.loadData())
        }
    }

    fun updateRecyclerView() {
        viewModelScope.launch {
            _liveDataDB.postValue(mainRepository.loadData())
        }
    }
}