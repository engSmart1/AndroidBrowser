package com.example.hytham.androidbrowser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button searchButtonHome;
    private EditText inputUrl;
    private Button facebook_btn;
    private Button youtube_btn;
    private Button instagram_btn;
    private Button snapchat_btn;
    private Button google_btn;
    private Button twitter_btn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        loadingBar = new ProgressDialog(this);

        searchButtonHome = findViewById(R.id.search_btn_home);
        inputUrl = findViewById(R.id.search_url_home);
        facebook_btn = findViewById(R.id.facebook);
        youtube_btn = findViewById(R.id.youtube);
        instagram_btn= findViewById(R.id.instagram);
        snapchat_btn = findViewById(R.id.snapchat);
        google_btn = findViewById(R.id.google);
        twitter_btn = findViewById(R.id.twitter);


        searchButtonHome.setOnClickListener(this);
        facebook_btn.setOnClickListener(this);
        youtube_btn.setOnClickListener(this);
        instagram_btn.setOnClickListener(this);
        snapchat_btn.setOnClickListener(this);
        google_btn.setOnClickListener(this);
        twitter_btn.setOnClickListener(this);


        loadingBar.setTitle("WELCOME");
        loadingBar.setMessage("Welcome to the best web browser ever ...");
        loadingBar.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                loadingBar.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 4000);



    }

    @Override
    public void onClick(View view)
    {
        if (view == searchButtonHome)
        {
            OpenWebSite();
        }
        if (view == facebook_btn)
        {
            Intent open_facebook = new Intent(HomeActivity.this , UrlSearch.class);
            open_facebook.putExtra("url_address" , "http://www.facebook.com");
            startActivity(open_facebook);
        }

        if (view == youtube_btn)
        {
            Intent open_youtube = new Intent(HomeActivity.this , UrlSearch.class);
            open_youtube.putExtra("url_address" , "http://www.youtube.com");
            startActivity(open_youtube);
        }
        if (view == instagram_btn)
        {
            Intent open_instagram = new Intent(HomeActivity.this , UrlSearch.class);
            open_instagram.putExtra("url_address" , "http://www.instagram.com");
            startActivity(open_instagram);
        }
        if (view == snapchat_btn)
        {
            Intent open_snapchat = new Intent(HomeActivity.this , UrlSearch.class);
            open_snapchat.putExtra("url_address" , "http://www.snapchat.com");
            startActivity(open_snapchat);
        }
        if (view == google_btn)
        {
            Intent open_google = new Intent(HomeActivity.this , UrlSearch.class);
            open_google.putExtra("url_address" , "http://www.google.com");
            startActivity(open_google);
        }
        if (view == twitter_btn)
        {
            Intent open_twitter = new Intent(HomeActivity.this , UrlSearch.class);
            open_twitter.putExtra("url_address" , "http://www.twitter.com");
            startActivity(open_twitter);
        }

    }

    private void OpenWebSite()
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



        String Url_address = inputUrl.getText().toString();
        if (TextUtils.isEmpty(Url_address))
        {
            Toast.makeText(HomeActivity.this, "Please Enter Url or WebSite !!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String url_without_url = Url_address.replaceAll("https://www." , "");
            String url_without_com = Url_address.replaceAll(".com" , "");
            String https =  "https://";
            String www =  "www.";
            String com = ".com";

            Intent search = new Intent(HomeActivity.this , UrlSearch.class);
            search.putExtra("url_address" , https+www+url_without_url);
            search.putExtra("url_address" , https+www+url_without_com+com);
            startActivity(search);

            inputUrl.setText("");
            inputUrl.requestFocus();


        }


    }
}
