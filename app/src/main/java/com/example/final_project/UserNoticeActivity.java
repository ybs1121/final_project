package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class UserNoticeActivity extends AppCompatActivity {
    private static final String TAG = "err";
    private ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> contactList;
    private String num, upload_date, kind;

    private ListView noticeListView;
    private NoticeListAdapter Adapter;
    private List<Notice> noticeList;
    private String Sever_ip = "3.34.141.69";
    Dialog search_Dialog;
    EditText search_text;
    String temp_search_text;
    Button logout_btn;
    private Context mContext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notice);



        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        mContext = this;


        System.out.println("디테일 실행");
        System.out.println(kind);




        contactList =new ArrayList<>();

        new BackgrounTask().execute();



        final LinearLayout notice = (LinearLayout)findViewById(R.id.notice);

        noticeListView = (ListView)findViewById(R.id.noticeList);
        noticeList = new ArrayList<Notice>();
        Adapter = new NoticeListAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(Adapter);




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
                open.putExtra("kind",kind);
                open.putExtra("activity","1");
                open.putExtra("search","2");



                startActivity(open);
            }
        });

        try{
            String str_msg = null;
            Intent msg= getIntent();
            if((str_msg=msg.getStringExtra("message")) != null){
                Toast myToast = Toast.makeText(UserNoticeActivity.this,str_msg , Toast.LENGTH_SHORT);
                myToast.show();


            }

        }catch(Exception e){
            e.printStackTrace();

        }

        Button back_btn = findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(open);
            }
        });


        logout_btn = findViewById(R.id.logout);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.clear(mContext);
                Intent open = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(open);

            }
        });








    }


    class BackgrounTask extends AsyncTask<Void,Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://"+Sever_ip+"/user_detail_notice.php";


        }



        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestMethod("GET");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();


                String send = "kind=" + kind;
                System.out.println("aa입력값: " + send);


                outputStream.write(send.getBytes("UTF-8"));

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");

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