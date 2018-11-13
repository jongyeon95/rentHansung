package com.example.com.renthansung;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Document doc = null;
    Elements contents;
   
    TextView textView;
    EditText editText;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        textView = (TextView) findViewById(R.id.test1);
        textView = (TextView) findViewById(R.id.test2);
        editText =(EditText)findViewById(R.id.inputid);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new testM().execute();
            }
        });
    }
    private class testM extends AsyncTask<Void, Void, Void> {
        String title;
        String title1;

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
                Connection.Response res1=Jsoup.connect("https://info.hansung.ac.kr/")
                        .header("Accpet","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Host","info.hansung.ac.kr")
                        .header("Connection","no-cache")
                        .header("Upgrade-Insecure-Requests","1")
                        .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36")
                        .method(Connection.Method.GET)
                        .timeout(3000)
                        .execute();
                Map<String,String>cookies1=res1.cookies();
                //ssl
                Connection.Response res2=Jsoup.connect("https://info.hansung.ac.kr/servlet/s_gong.gong_login_ssl")
                        .header("Accpet","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Host","info.hansung.ac.kr")
                        .header("Connection","keep-alive")
                        .header("Cache-Control","no-cache")
                      //  .header("Content-Length","31")
                       // .header("Content-Type","application/x-www-form-urlencoded")
                        .cookies(cookies1)
                        .header("Upgrade-Insecure-Requests","1")
                        .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36")
                        .header("Origin","https://info.hansung.ac.kr")
                        .header("Pragma","no-cache")
                        .header("Referer","https://info.hansung.ac.kr/")
                        .data("id","1494017")
                        .data("passwd","!qweasd9577")
                        .method(Connection.Method.POST)
                        .timeout(3000)
                        .execute();

                Map<String,String>cookies2=res2.cookies();
                //main홈

                Connection.Response res3=Jsoup.connect("https://info.hansung.ac.kr/h_dae/dae_main.html")
                        .header("Accpet","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Host","info.hansung.ac.kr")
                        .header("Connection","keep-alive")
                        .header("Cache-Control","no-cache")
                        .cookies(cookies2)
                        .header("Upgrade-Insecure-Requests","1")
                        .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36")
                        .header("Pragma","no-cache")
                        .method(Connection.Method.GET)
                        .timeout(3000)
                        .execute();
                Map<String,String>cookies3=res3.cookies();
                //프레임열기
                Connection.Response res4=Jsoup.connect("https://info.hansung.ac.kr/jsp/haksa/infohaksamain.jsp")
                        .header("Accpet","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Host","info.hansung.ac.kr")
                        .header("Connection","keep-alive")
                        .header("Cache-Control","no-cache")
                        .cookies(cookies3)
                        .header("Upgrade-Insecure-Requests","1")
                        .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36")
                        .header("Pragma","no-cache")
                        .header("Referer","https://info.hansung.ac.kr/h_dae/dae_main.html")
                        .method(Connection.Method.GET)
                        .timeout(3000)
                        .execute();
                Map<String,String>cookies4=res4.cookies();
                //학적
                Connection.Response res5=Jsoup.connect("https://info.hansung.ac.kr/jsp/haksa/infohaksamain.jsp")
                        .header("Accpet","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                        .header("Host","info.hansung.ac.kr")
                        .header("Connection","keep-alive")
                        .header("Cache-Control","no-cache")
                        .cookies(cookies4)
                        .header("Upgrade-Insecure-Requests","1")
                        .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36")
                        .header("Pragma","no-cache")
                        .header("Referer","https://info.hansung.ac.kr/tonicsoft/jik/register/collage_register_1_rwd.jsp")
                        .method(Connection.Method.GET)
                        .timeout(3000)
                        .execute();
                Map<String,String>cookies5=res5.cookies();


                Document document = Jsoup.connect("https://info.hansung.ac.kr/jsp/haksa/infohaksamain.jsp")
                        .cookies(cookies4)
                        .get();
                Document document1 = Jsoup.connect("https://info.hansung.ac.kr/jsp/haksa/infohaksamain.jsp")
                        .cookies(cookies5)
                        .get();
                // Get the html document title
                title = document.html();
                title1= document1.html();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
         return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            TextView txttitle = (TextView) findViewById(R.id.test1);
            TextView txttitle1 = (TextView) findViewById(R.id.test2);
            txttitle.setText(title);
            txttitle1.setText(title1);
            mProgressDialog.dismiss();
        }
    }

}

