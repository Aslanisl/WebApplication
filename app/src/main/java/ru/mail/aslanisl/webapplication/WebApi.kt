package ru.mail.aslanisl.webapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface WebApi {

    @GET
    fun getHtml(@Url url: String): Call<String>
}