package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;


public class UserDetailNoticeActivity extends AppCompatActivity {
    Button return_main;
    Button remove_btn;
    TextView kind_text;
    TextView upload_text;
    TextView area_text;
    ImageView pic;
    String Sever_ip = "3.34.141.69";
    String num;
    String temp_num;
    String passwd;
    Dialog del_Dialog;
    String search;
    String kind;
    String activity_kind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notice);
        Intent intent = getIntent();
        num = intent.getStringExtra("num");
        kind= intent.getStringExtra("kind");
        activity_kind = intent.getStringExtra("activity");

        if (activity_kind ==null){
            activity_kind = "2";
        }



        kind_text = findViewById(R.id.textView2);
        upload_text = findViewById(R.id.textView3);
        area_text = findViewById(R.id.textView4);
        pic = findViewById(R.id.imageView2);
        remove_btn = findViewById(R.id.button2);





        return_main = findViewById(R.id.button4);

        return_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                try{
                    search = intent.getStringExtra("search");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(search==null){
                    search ="3";
                }

                if (search.equals("1")==true ){

                    Intent open = new Intent(getApplicationContext(), activity_search.class);
                    System.out.println("카인드 activity_search");

                    open.putExtra("kind",kind);

                    startActivity(open);

                }

                else if (search.equals("2")==true ){

                    Intent open = new Intent(getApplicationContext(), UserNoticeActivity.class);
                    System.out.println("카인드 UserNoticeActivity");

                    open.putExtra("kind",kind);

                    startActivity(open);




                }


                else{
                    System.out.println("카인드 UserMainActivity");

                    Intent open = new Intent(getApplicationContext(), UserMainActivity.class);
                    startActivity(open);





                }

            }
        });


        del_Dialog = new Dialog(UserDetailNoticeActivity.this);
        del_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        del_Dialog.setContentView(R.layout.del_dialog);


        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del_Dialog.show();


                Button send = del_Dialog.findViewById(R.id.sendBtn);
                Button back = del_Dialog.findViewById(R.id.backBtn);
                final EditText passwd_text = del_Dialog.findViewById(R.id.passwd_text);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String target ;
                        String temp_passwd =passwd_text.getText().toString();
                        //passwd.equals(temp_passwd)
                        if (passwd.equals(temp_passwd)==true){

                            try {
                                new del_BackgrounTask().execute();
                                del_Dialog.dismiss();


                                Intent open = new Intent(getApplicationContext(),UserMainActivity.class);
                                startActivity(open);

                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        else{
                            Toast myToast = Toast.makeText(UserDetailNoticeActivity.this,"비밀번호 불일치" , Toast.LENGTH_SHORT);
                            myToast.show();
                            System.out.println("디비" + passwd);
                            System.out.println("입력" + temp_passwd);
                        }

                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        del_Dialog.dismiss();
                    }
                });


            }
        });

        new BackgrounTask().execute();

    }

    class BackgrounTask extends AsyncTask<Void, Void, String> {
        String target;


        @Override
        protected void onPreExecute() {

            if(activity_kind.equals("1")==true){
                System.out.println(activity_kind);
                target = "http://" + Sever_ip + "/get_no.php";
            }
            else{
                System.out.println(activity_kind);
                target = "http://" + Sever_ip + "/user_get_no.php";
            }



        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                System.out.println("입력값: " + num);
                String send = "num= " + num;
                outputStream.write(send.getBytes("UTF-8"));


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
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
                System.out.println(stringBuilder.toString().trim());
                String rev = stringBuilder.toString();


                return stringBuilder.toString().trim();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();

        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String upload_date, kind, area, image;

                System.out.println(jsonArray.length());

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);

                    kind = object.getString("kind");
                    upload_date = object.getString("upload_date");
                    area = object.getString("area");
                    image = object.getString("image");
                    passwd = object.getString("passwd");
                    temp_num= object.getString("num");
                    kind_text.setText(kind);
                    upload_text.setText(upload_date);
                    area_text.setText(area);


                    Bitmap bitmap = StringToBitmap(image);
                    pic.setImageBitmap(bitmap);


                    count++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {

            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            //return bitmap;
            return Bitmap.createScaledBitmap(bitmap,512,512,true);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }


    }


    class del_BackgrounTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://" + Sever_ip + "/user_del_test.php";

        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                System.out.println("입력값: " + num);
                String send = "num= " + num;
                outputStream.write(send.getBytes("UTF-8"));


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
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
                System.out.println(stringBuilder.toString().trim());
                String rev = stringBuilder.toString();


                return stringBuilder.toString().trim();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();

        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String sc;
                String i_sc = "1";

                System.out.println(jsonArray.length());

                while (count != 1) {



                    JSONObject object = jsonArray.getJSONObject(count);
                                       sc = object.getString("sc");

                    System.out.println(sc);
                    System.out.println(object.getString("sc"));

                    if(i_sc.equals(sc)){
                        String msg = "삭제완료";
                        count = 1;
                        Intent open = new Intent(getApplicationContext(),UserMainActivity.class);
                        open.putExtra("message",msg);




                    }

                    else{
                        count = 1;
                        Toast myToast = Toast.makeText(UserDetailNoticeActivity.this,"삭제실패" , Toast.LENGTH_SHORT);
                        myToast.show();



                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}