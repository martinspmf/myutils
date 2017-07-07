package com.mobinteg.myutils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobinteg.utilslib.MyUtils;
import com.mobinteg.utilslib.util.ConvertUtils;
import com.mobinteg.utilslib.util.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private WebView webview;
    private String content;
    private String url = "http://autarquicas.psd.pt/index.php/site/candidato?id=199";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.init(this);

        ConvertUtils.dp2px(100);

        mContext = this;

        webview = (WebView)findViewById(R.id.webview);
/*
        Async async = new Async();
        async.execute();
*/

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                webview.loadUrl("javascript:(function() {document.getElementsByClassName(\"header\")[0].style.display='none';})()");
                webview.loadUrl("javascript:(function() {document.getElementsByClassName(\"footer-above\")[0].style.display='none';})()");
                webview.loadUrl("javascript:(function() {document.getElementsByClassName(\"footer\")[0].style.display='none';})()");
            }
        });
        webview.loadUrl(url);

    }

    class Async extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadDataWithBaseURL("", url, "text/html", "UTF-8", "");
            //webview.loadUrl(content);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Document document = null;
            try {
                document = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            content = document.select("div.candidato").outerHtml();

            return null;
        }
    }

}
