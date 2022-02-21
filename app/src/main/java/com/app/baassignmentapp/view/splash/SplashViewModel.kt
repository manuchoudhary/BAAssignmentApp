package com.app.baassignmentapp.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.baassignmentapp.boot.*
import com.app.baassignmentapp.datasource.MainInteractor
import com.app.baassignmentapp.utils.Constants.BOOT_COMPLETE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mainInteractor: MainInteractor

    var progressValue = MutableLiveData(true)
    val isShowProgress: LiveData<Boolean>
        get() = progressValue

    var bootComplete = MutableLiveData(false)

    fun initiateBootCall(bootSection: String) {
        progressValue.value = true
        when(bootSection) {
            BOOT_ARTS -> {
                viewModelScope.launch(Dispatchers.IO) {
                    mainInteractor.callArticle()
                }
                initiateBootCall(BOOT_COMPLETE)
            }
            BOOT_COMPLETE -> {
                progressValue.value = false
                bootComplete.postValue(true)
            }
        }
    }
}