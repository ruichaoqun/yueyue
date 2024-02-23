package com.ruichaoqun.yueyue.ui.Web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.ruichaoqun.yueyue.R

class WebActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        intent.getStringExtra(INTENT_URL)?.apply {
            webView.loadUrl(this)
        }
    }

    companion object{
        const val INTENT_URL = "INTENT_URL"
        fun startWebActivity(context: Context, url:String) {
            context.startActivity(Intent(context,WebActivity::class.java).also {
                it.putExtra(INTENT_URL,url)
            })
        }
    }
}