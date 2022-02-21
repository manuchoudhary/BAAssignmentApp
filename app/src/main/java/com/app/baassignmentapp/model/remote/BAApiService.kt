package com.app.baassignmentapp.model.remote

import com.app.baassignmentapp.utils.Constants.END_POINT
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface BAApiService {
    @GET(END_POINT)
    fun callArticles() : Call<JsonObject>
}