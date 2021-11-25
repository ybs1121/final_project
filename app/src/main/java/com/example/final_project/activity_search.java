package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.LauncherActivity;
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

public class activity_search extends AppCompatActivity {
    private static final String TAG = "err";
    private ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> contactList;
    private String  kind;

    private ListView noticeListView_1;
    private NoticeListAdapter Adapter_1;
    private List<Notice> noticeList_1;
    private String Sever_ip = "3.34.141.69";
    Dialog search_Dialog;
    EditText search_text;
    String temp_search_text;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        noticeListView_1 = (ListView)findViewById(R.id.noticeList_search);
        noticeList_1 = new ArrayList<Notice>();
        Adapter_1 = new NoticeListAdapter(getApplicationContext(),noticeList_1);
        noticeListView_1.setAdapter(Adapter_1);

        new search_BackgrounTask().execute();


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(open);
            }
        });

        noticeListView_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String search_code = "1";

                //int index = noticeListView.getCheckedItemPosition();
                Object index_Object = (Object) adapterView.getAdapter().getItem(i);
                //String index_string = String.valueOf(index_Object);
                long a = adapterView.getItemIdAtPosition(i);
                String index_string = noticeList_1.get(i).getUpload_date();




                System.out.println(index_string);
                Intent open = new Intent(getApplicationContext(),DetailNoticeActivity.class);

                open.putExtra("search",search_code);
                open.putExtra("num",index_string);
                open.putExtra("kind",kind);



                startActivity(open);
            }
        });

    }


    class search_BackgrounTask extends AsyncTask<Void,Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://"+Sever_ip+"/search_test.php?";

        }



        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestMethod("GET");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();


               // String send = "num= " + num;

                String send = "kind="+kind;
                System.out.println(send);
                outputStream.write(send.getBytes("UTF-8"));


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                    System.out.println("위치2");
                    //System.out.println(stringBuilder.toString().trim());

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

                    noticeList_1.add(new Notice(num, upload_date, kind));
                    noticeListView_1.setAdapter(Adapter_1);
                    count++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}