package com.example.hytham.androidbrowser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UrlSearch extends AppCompatActivity implements View.OnClickListener {
    private Button searchUrlButton, homeButton;
    private EditText UrlInput;
    private WebView searchWebAddress;
    String url;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_url_search);
        searchUrlButton = findViewById(R.id.search_url_button);
        UrlInput = findViewById(R.id.input_search_url);
        homeButton = findViewById(R.id.home_button);
        searchWebAddress = findViewById(R.id.searchWebSite);

        url = getIntent().getExtras().get("url_address").toString();

        UrlInput.setText(url);

        WebSettings webSettings = searchWebAddress.getSettings();
        webSettings.setJavaScriptEnabled(true);
        searchWebAddress.loadUrl(url);
        searchWebAddress.setWebViewClient(new WebViewClient());


        searchUrlButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        loadingBar = new ProgressDialog(this);

        loadingBar.setTitle("Loading......");
        loadingBar.setMessage("Please wait while we are opening your requested web address");
        loadingBar.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                loadingBar.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);



    }

    @Override
    public void onBackPressed() {
        if (searchWebAddress.canGoBack()) {
            searchWebAddress.goBack();
        } else
            super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if (view == homeButton) {
            finish();
            startActivity(new Intent(UrlSearch.this, HomeActivity.class));
        }
        if (view == searchUrlButton) {
            SearchWebAddress();
        }

    }

    private void SearchWebAddress()
    {
        loadingBar.setTitle("Loading......");
        loadingBar.setMessage("Please wait while we are opening your requested web address");
        loadingBar.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                loadingBar.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);


        String https = "https://";
        String www = "www.";
        String com = ".com";
        String Url_address = UrlInput.getText().toString();
        String url_without_url  = Url_address.replaceAll("https://www.", "");;
        String url_without_com = null;

        if (TextUtils.isEmpty(Url_address))
        {
           // Toast.makeText(UrlSearch.this, "Please enter Url or Website", Toast.LENGTH_SHORT).show();

            url_without_com = Url_address.replaceAll(".com", "");

            Intent search1 = new Intent(UrlSearch.this, UrlSearch.class);
            search1.putExtra("url_address", https + www + url_without_com + com );
            startActivity(search1);
        }
        else
            {
                Intent search = new Intent(UrlSearch.this, UrlSearch.class);
                search.putExtra("url_address", https + www + url_without_url );
                startActivity(search);

                UrlInput.setText("");
                UrlInput.getText().clear();
                UrlInput.requestFocus();

            }




    }
}