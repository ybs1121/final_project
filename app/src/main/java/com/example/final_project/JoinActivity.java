package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

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

public class JoinActivity extends AppCompatActivity {

    String Sever_ip = "3.34.141.69";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button user_cancel_btn = (Button) findViewById(R.id.user_cancel);

        user_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(open);
            }
        });

        Button user_join_btn = (Button) findViewById(R.id.user_save);

        user_join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    public  void run(){
                        user_save_db();

                    }
                }.start();





            }
        });








    }//onCreate End




    private void user_save_db() {

        EditText id_text = (EditText) findViewById(R.id.user_id);
        EditText passwd_text = (EditText) findViewById(R.id.user_passwd);
        EditText kind_text = (EditText) findViewById(R.id.user_kind);
        String id, passwd, kind;


        String target = "http://" + Sever_ip + "/save_user.php";

        id = id_text.getText().toString();
        passwd = passwd_text.getText().toString();
        kind = kind_text.getText().toString();

        StringBuilder stringBuilder = new StringBuilder();
        String success = "fail";

        if (id.equals("") == true) {
            JoinActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(JoinActivity.this, "아이디를 확인해주십시오", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (passwd.equals("") == true) {
            JoinActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(JoinActivity.this, "비밀번호를 확인해주십시오", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (kind.equals("") == true) {
            JoinActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(JoinActivity.this, "종을 확인해주십시오", Toast.LENGTH_SHORT).show();
                }
            });
        } else {


            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                System.out.println("입력값: " + id);
                System.out.println("입력값: " + passwd);
                System.out.println("입력값: " + kind);
                String send = "id=" + id + "&passwd=" + passwd + "&kind=" + kind;
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


            //id 확인

            try {
                System.out.println("Test");
                JSONObject jsonObject = new JSONObject(stringBuilder.toString().trim());
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;


                System.out.println(jsonArray.length());

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    System.out.println(object);


                    success = object.getString("success");
                    count++;

                }

                if (success.equals("success") == true) {
                    Intent open = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(open);

                } else {
                    JoinActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(JoinActivity.this, "사용되고 있는 ID입니다.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }



}



