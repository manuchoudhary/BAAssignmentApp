package com.app.baassignmentapp.view.main

import androidx.lifecycle.*
import com.app.baassignmentapp.datasource.MainInteractor
import com.app.baassignmentapp.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mainInteractor: MainInteractor
    var articleList: MutableLiveData<ArrayList<Article>>  = MutableLiveData()

    fun getArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            if (::mainInteractor.isInitialized) {
                val list = mainInteractor.callArticle()
                withContext(Dispatchers.Main) {
                    articleList.postValue(list)
                }
            }
        }
    }
}