package ru.mail.aslanisl.webapplication

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val URL = "http://paperwork.press/"

private const val BUTTON_NAME = "but1"

object Webservice {

    private val webApi by lazy {
        Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(URL)
                .build()
                .create(WebApi::class.java)
    }

    fun doWork(callback: ((String) -> Unit)?) {
        webApi.getHtml("http://paperwork.press/test.html").enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                callback?.invoke(getButtonLink(response?.body(), BUTTON_NAME))
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                //Do nothing
            }
        })
    }

    /**
     * @param buttonName name of button that you want to click for
     * it must contains href link
     */
    private fun getButtonLink(html: String?, buttonName: String): String {
        if (html == null) return ""
        val buttonString = "a class=\"$buttonName\" href=\""
        val startIndex = html.indexOf(buttonString) + buttonString.length
        return html.substring(startIndex, html.indexOf("\"", startIndex, true))
    }
}