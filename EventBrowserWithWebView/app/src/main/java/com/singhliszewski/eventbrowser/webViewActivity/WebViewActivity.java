package com.singhliszewski.eventbrowser.webViewActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.singhliszewski.eventbrowser.R;

/**
 * Created by Ryanliszewski on 12/23/16.
 */

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        String url = super.getIntent().getExtras().getString("urlString");
        Log.i("Test:", url);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
