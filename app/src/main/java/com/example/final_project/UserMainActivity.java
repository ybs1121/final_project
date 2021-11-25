package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.ProgressDialog;
import android.os.AsyncTask;

//import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class UserMainActivity extends AppCompatActivity {
    private static final String TAG = "err";
    private ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> contactList;

    private ListView noticeListView;
    private NoticeListAdapter Adapter;
    private List<Notice> noticeList;
    private String Sever_ip = "3.34.141.69";
    Dialog search_Dialog;
    EditText search_text;
    TextView title;
    String temp_search_text;
    private Context mContext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.text_notice);
        title.setText("실종게시판");
        contactList =new ArrayList<>();
        mContext = this;
        final Button login = (Button) findViewById(R.id.login_btn);
        final Button move_user_notice_btn =findViewById(R.id.button7);

        String text_id = PreferenceManager.getString(mContext,"id");

        if(text_id.equals("")){
            System.out.println("데이터 없음");


        }
        else{
            Toast myToast = Toast.makeText(UserMainActivity.this,"자동 로그인" , Toast.LENGTH_SHORT);
            myToast.show();
            login.setText("로그아웃");

            move_user_notice_btn.setVisibility(View.VISIBLE);

        }

        move_user_notice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_id = PreferenceManager.getString(mContext,"id");
                String temp_kind = PreferenceManager.getString(mContext,"kind");
                System.out.println(temp_kind);

                Intent open = new Intent(getApplicationContext(),UserNoticeActivity.class);
                open.putExtra("kind",temp_kind);
                open.putExtra("id",temp_id);
                startActivity(open);
            }
        });

        new BackgrounTask().execute();




        Button upload = (Button) findViewById(R.id.upload);


        noticeListView = (ListView)findViewById(R.id.noticeList);

        noticeList = new ArrayList<Notice>();
        Adapter = new NoticeListAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(Adapter);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_id = PreferenceManager.getString(mContext,"id");
                if(text_id.equals("")){
                    Intent open = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(open);

                }
                else{
                    PreferenceManager.clear(mContext);
                    System.out.println("clear1");
                    login.setText("로그인");
                    move_user_notice_btn.setVisibility(View.INVISIBLE);

                }

            }
        });

        Button lost_btn = (Button)findViewById(R.id.lost_btn);

        lost_btn.setText("목격");

        lost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(open);
            }
        });









        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),UserInsertActivity.class);
                startActivity(open);

            }
        });

        TextView text_notice = findViewById(R.id.text_notice);
        text_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });


        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object index_Object = (Object) adapterView.getAdapter().getItem(i);
                long a = adapterView.getItemIdAtPosition(i);
                String index_string = noticeList.get(i).getUpload_date();





                System.out.println(index_string);

                Intent open = new Intent(getApplicationContext(),UserDetailNoticeActivity.class);
                open.putExtra("num",index_string);

                startActivity(open);
            }
        });

        try{
            String str_msg = null;
            Intent msg= getIntent();
            if((str_msg=msg.getStringExtra("message")) != null){
                Toast myToast = Toast.makeText(UserMainActivity.this,str_msg , Toast.LENGTH_SHORT);
                myToast.show();


            }

        }catch(Exception e){
            e.printStackTrace();

        }

        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Button search = findViewById(R.id.search);
                search_Dialog = new Dialog(UserMainActivity.this);
                search_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                search_Dialog.setContentView(R.layout.search);


                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        search_Dialog.show();
                        Button send = search_Dialog.findViewById(R.id.sendBtn2);
                        Button back = search_Dialog.findViewById(R.id.backBtn2);
                        search_text =search_Dialog.findViewById(R.id.seach_text);

                        send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                temp_search_text = search_text.getText().toString();
                                Intent open = new Intent(getApplicationContext(),UserSearchActivity.class);
                                open.putExtra("kind",temp_search_text);
                                open.putExtra("search",1);

                                startActivity(open);
                                search_Dialog.dismiss();


                            }
                        });

                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                search_Dialog.dismiss();
                            }
                        });

                    }
                });
            }
        });







    }


    class BackgrounTask extends AsyncTask<Void,Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://"+Sever_ip+"/user_main.php";


        }



        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");

                }



                if (bufferedReader != null){
                    try {

                        bufferedReader.close();

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }




                inputStream.close();
                httpURLConnection.disconnect();
                System.out.println(stringBuilder.toString().trim());

                return stringBuilder.toString().trim();




            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();

        }

        @Override
        public void onPostExecute(String result){
            try{
                System.out.println("Test");
                System.out.println(result);
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray =jsonObject.getJSONArray("response");
                int count = 0;
                String num,upload_date,kind;

                System.out.println(jsonArray.length());

                while(count < jsonArray.length()){
                    JSONObject object =jsonArray.getJSONObject(count);
                    System.out.println(object);


                    kind = object.getString("kind");
                    upload_date = object.getString("upload_date");
                    num = object.getString("num");

                    noticeList.add(new Notice(num, upload_date, kind));
                    noticeListView.setAdapter(Adapter);

                    count++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }




}