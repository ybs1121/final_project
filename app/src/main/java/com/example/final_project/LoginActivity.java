package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    String Sever_ip = "3.34.141.69";
    String result;
    String id, passwd, kind, success;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button join_btn = (Button) findViewById(R.id.join_btn);
        Button home_btn = (Button) findViewById(R.id.home_btn);
        System.out.println("로그인 엑티비트 들어옴");
        mContext = this;
        String text_id = PreferenceManager.getString(mContext, "id");
        String text_kind = PreferenceManager.getString(mContext, "kind");
        if (text_id.equals("")) {
            System.out.println("로그인 엑티비트 자동로그인 실패");

        }
        else{
            System.out.println(text_id);
            System.out.println("로그인 엑티비트 자동로그인 들어옴");
            Toast myToast = Toast.makeText(LoginActivity.this, "자동로그인 성공", Toast.LENGTH_SHORT);
            myToast.show();

            Intent open2 = new Intent(getApplicationContext(), UserDetailNoticeActivity.class);
            open2.putExtra("kind",text_kind);

            Intent open = new Intent(getApplicationContext(), UserNoticeActivity.class);
            open.putExtra("kind",text_kind);
            startActivity(open);

        }


        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(open);
            }
        });


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(open);
            }
        });



        Button login_btn = (Button) findViewById(R.id.button6);

        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




                    new Thread() {
                        public void run() {
                            String check = "fail";

                            check = user_login_db();
                            System.out.println(check);


                        }
                    }.start();


            }
        });



    }//onCreate End


    private String user_login_db() {
        EditText id_text = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText passwd_text = (EditText) findViewById(R.id.editTextTextPersonName3);
        String check = "fail";



        String target = "http://" + Sever_ip + "/login_db.php";

        id = id_text.getText().toString();
        passwd = passwd_text.getText().toString();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(target);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();


            String send = "id=" + id +"&passwd=" + passwd;
            System.out.println("aa입력값: " + send);


            outputStream.write(send.getBytes("UTF-8"));

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;

            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp + "\n");

            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            inputStream.close();
            httpURLConnection.disconnect();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(stringBuilder.toString().trim());
        System.out.println("Test");


        try{

            JSONObject jsonObject = new JSONObject(stringBuilder.toString().trim());
            JSONArray jsonArray =jsonObject.getJSONArray("response");
            int count = 0;

            System.out.println(jsonArray.length());
            System.out.println("입력값: " + id);
            System.out.println("입력값: " + passwd);


            while(count < jsonArray.length()){
                JSONObject object =jsonArray.getJSONObject(count);
                System.out.println(object);


                id = object.getString("id");
                passwd = object.getString("passwd");
                kind = object.getString("kind");
                success = object.getString("success");
                System.out.println("Test 입력값: " + success);

                check = success;






                count++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        if (check.equals("success")==true) {



            System.out.println("전송한 값"+kind);

            check = "fail";


            mContext = this;

            String temp_id = PreferenceManager.getString(mContext,"id");
            String temp_kind = PreferenceManager.getString(mContext,"kind");

            if (temp_id.equals("")) {
                PreferenceManager.setString(mContext, "id",id);
                PreferenceManager.setString(mContext, "kind",kind);

            }




            LoginActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                }
            });

            Intent open2 = new Intent(getApplicationContext(), UserDetailNoticeActivity.class);
            open2.putExtra("kind",kind);


            Intent open = new Intent(getApplicationContext(), UserNoticeActivity.class);
            open.putExtra("kind",kind);
            startActivity(open);


        } else {
            System.out.println("2");



            LoginActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(LoginActivity.this, "아이디 비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            });


        }

        return success;
    }



}