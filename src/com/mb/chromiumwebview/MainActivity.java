package com.mb.chromiumwebview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    public static final String TAG = "test";
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                Log.d(TAG, "on create window called, blocking popup");
                return false;
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "loading url " + url);
                webView.loadUrl(url);
                return true;
            }
        });
        //webView.loadUrl("http://www.google.com");

        String customHtml = "<html><body><h1>Hello, Test</h1>" +
                "<button type=\"button\" onclick=\"window.open('http://www.example.com');\">" +
                "Clicking here should NOT open example.com</button>" +
                "</body></html>";
        webView.loadData(customHtml, "text/html", "UTF-8");

        setContentView(webView);

    }

}