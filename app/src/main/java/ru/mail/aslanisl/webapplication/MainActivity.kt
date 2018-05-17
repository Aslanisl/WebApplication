package ru.mail.aslanisl.webapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var callback: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { moveTaskToBack(true) }

        webview.webViewClient = WebViewClient()
        webview.webChromeClient = WebChromeClient()

        callback = {
            Log.d("WebApplication", "Loaded url = $it")
            webview.loadUrl(it)
        }

        Webservice.doWork(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        callback = null
    }
}
