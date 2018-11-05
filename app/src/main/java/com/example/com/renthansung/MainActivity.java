package com.example.com.renthansung;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Document doc = null;
    Elements contents;
    final String url = "https://info.hansung.ac.kr/";
    TextView textView;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        textView = (TextView) findViewById(R.id.test1);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new testM().execute();
            }
        });
    }
    private class testM extends AsyncTask<Void, Void, Void> {
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String Accpet="text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
                String AcceptE="gzip, deflate, br";
                String AccpetL="ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7";
                String Upgrade="1";
                String UserAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36";
                // Connect to the web site
                Connection.Response res1=Jsoup.connect(url)
                        .timeout(3000)
                        .header("Accept",Accpet)
                        .header("Accept-Encoding",AcceptE)
                        .header("Accept-Language",AccpetL)
                        .header("Upgrade-Insecure-Requests",Upgrade)
                        .header("User-Agent",UserAgent)
                        .method(Connection.Method.GET)
                        .execute();
                Map<String,String> cookies=res1.cookies();

                Connection.Response res2=Jsoup.connect("https://info.hansung.ac.kr/h_dae/dae_main.html")
                        .timeout(3000)
                        .data("id","1494017")
                        .data("passwd","!qweasd9577")
                        .header("Accept",Accpet)
                        .header("Accept-Encoding",AcceptE)
                        .header("Accept-Language",AccpetL)
                        .header("Upgrade-Insecure-Requests",Upgrade)
                        .header("User-Agent",UserAgent)
                        .cookies(cookies)
                        .method(Connection.Method.POST)
                        .execute();
                Map<String,String> logincookies=res2.cookies();
                Connection.Response res3=Jsoup.connect("https://info.hansung.ac.kr/tonicsoft/jik/register/collage_register_1_rwd.jsp")
                        .timeout(3000)

                        .header("Accept",Accpet)
                        .header("Accept-Encoding",AcceptE)
                        .header("Accept-Language",AccpetL)
                        .header("Upgrade-Insecure-Requests",Upgrade)
                        .header("User-Agent",UserAgent)
                        .cookies(logincookies)
                        .method(Connection.Method.POST)
                        .execute();
                Map<String,String> logincookies1=res3.cookies();

                Document document = Jsoup.connect("https://info.hansung.ac.kr/tonicsoft/jik/register/collage_register_1_rwd.jsp")

                        .header("Accept",Accpet)
                        .header("Accept-Encoding",AcceptE)
                        .header("Accept-Language",AccpetL)
                        .header("Upgrade-Insecure-Requests",Upgrade)
                        .header("User-Agent",UserAgent)
                        .method(Connection.Method.GET)
                        .cookies(logincookies1)
                        .get();
                // Get the html document title
                title = document.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            TextView txttitle = (TextView) findViewById(R.id.test1);
            txttitle.setText(title);
            mProgressDialog.dismiss();
        }
    }

}

