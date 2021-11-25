package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    RadioButton dog;
    RadioButton cat;
    RadioGroup dog_cat;
    ImageView mImageViwe;
    Button pic;
    Button send;
    Button save;
    EditText area;
    EditText animal_kind;
    EditText password;
    TextView password_notice;
    public static String result ="aaa";
    private  static final int IMAGE_PICK_CODE = 1000;
    private  static final int PERMISSION_CODE = 1001;

    private Socket client;
    private Socket client_kind;
    private Socket socket;
    JSONObject jsonObject = new JSONObject();
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private static String SERVER_IP = "3.34.141.69";
    private static String CONNECT_MSG = "connect";
    private static String STOP_MSG = "stop";
    private String img_path;
    String imageName;
    int kind = -1;
    String strBase64 = "";
    ProgressDialog loading;

    private DataOutputStream dataOutput_kind;
    private DataInputStream dataInput_kind;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);



        dog_cat = (RadioGroup)findViewById(R.id.radio_group);
        mImageViwe =findViewById(R.id.imageView);
        pic=(Button)findViewById(R.id.button);
        send=(Button)findViewById(R.id.button3);
        animal_kind=findViewById(R.id.read_text);
        save = findViewById(R.id.button5);
        area = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPassword);
        animal_kind = findViewById(R.id.read_text);
        mImageViwe = findViewById(R.id.imageView);
        password_notice = findViewById(R.id.textView);




        loading = new ProgressDialog(this);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.setCancelable(false);


        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                save.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                animal_kind.setVisibility(View.VISIBLE);
                area.setVisibility(View.VISIBLE);
                password_notice.setVisibility(View.VISIBLE);
                loading.show();





                Connect connect = new Connect();
                connect.execute(CONNECT_MSG);




            }
        });


        dog_cat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                pic.setVisibility(View.VISIBLE);

                if(checkedId == R.id.radioButton){
                    result = "강아지";
                    kind = 1;
                    Toast myToast = Toast.makeText(InsertActivity.this,"강아지" , Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    result = "고양이";
                    kind = 0;
                    System.out.println(result);
                    Toast myToast = Toast.makeText(InsertActivity.this,"고양이" , Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });

        pic.setOnClickListener(new View.OnClickListener(){

            public  void onClick(View view){
                send.setVisibility(View.VISIBLE);
                //check runtime permission
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){

                        String[]  permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions, PERMISSION_CODE);


                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();

                    }
                }


                else{
                    //system os is less then marshmallow
                    pickImageFromGallery();

                }
            }



        });



        //json test 중




        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int kind_temp = kind;
                String area_text = area.getText().toString();
                String password_text = password.getText().toString();
                String an_kind = animal_kind.getText().toString();
                Toast myToast = Toast.makeText(InsertActivity.this,area_text , Toast.LENGTH_SHORT);


                if (TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(area.getText())){
                    myToast = Toast.makeText(InsertActivity.this,"저장준비실패" , Toast.LENGTH_SHORT);
                    myToast.show();

                }
                else {
                    myToast = Toast.makeText(InsertActivity.this,area_text , Toast.LENGTH_SHORT);
                    myToast.show();
                }


                try {





                    jsonObject.put("kind",kind_temp);
                    jsonObject.put("area",area_text);
                    jsonObject.put("password",password_text);
                    jsonObject.put("animal_kind",an_kind);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Connect_send connect_send = new Connect_send();
                connect_send.execute(CONNECT_MSG);
                Intent open = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(open);

                

            }
        });

        Button back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(open);
            }
        });

    }
    private class Connect_send extends AsyncTask< String , String,Void > {
        String se = SERVER_IP;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                byte[] jsonByteArray = jsonObject.toString().getBytes("UTF-8");
                String jsonString = jsonObject.toString();
                byte[] buf = new byte[1024];
                int readByte;
                System.out.println(jsonString);

                socket = new Socket(se, 7008);
                socket.getOutputStream();
                dataOutput = new DataOutputStream(socket.getOutputStream());


                //dataOutput.write(jsonByteArray);
                dataInput = new DataInputStream(new ByteArrayInputStream(jsonByteArray));
                //dataInput = new DataInputStream(new getInputStream(jsonString));

                while ((readByte=dataInput.read(buf))!= -1){
                    dataOutput.write(buf,0,readByte);
                    System.out.println("과연");


                }


                dataOutput.close();
                socket.close();


            } catch (UnknownHostException e) {
                String str = e.getMessage().toString();
                Log.w("discnt", str + " 1");
            } catch (IOException e) {
                String str = e.getMessage().toString();
                Log.w("discnt", str + " 2");
            }



            return null;
        }


    }





    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);


    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();

                } else {
                    Toast.makeText(this, "Permission denide!", Toast.LENGTH_SHORT).show();


                }


            }
        }

    }



    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            mImageViwe.setImageURI(data.getData());
        }

        System.out.println(getImagePathToUri(data.getData()));


        //이미지를 비트맵형식으로 반환
        img_path = getImagePathToUri(data.getData()); //이미지의 URI를 얻어 경로값으로 반환.
        Toast.makeText(getBaseContext(), "img_path : " + img_path, Toast.LENGTH_SHORT).show();


    }

    public String getImagePathToUri(Uri data) {
        //사용자가 선택한 이미지의 정보를 받아옴
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.d("test", imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
        Toast.makeText(InsertActivity.this, "이미지 이름 : " + imgName, Toast.LENGTH_SHORT).show();
        this.imageName = imgName;


        img_path = imgPath;

        return imgPath;
    }



    private class Connect extends AsyncTask< String , String,Void > {

        private String output_message;
        private String input_message;
        private FileOutputStream fop;
        private FileInputStream fip;
        DataInputStream dis1;
        String path = img_path;
        String se = SERVER_IP;




        protected Void doInBackground(String... strings) {


            try {

                client = new Socket(se, 8079);
                File file1 =new File(path);
                DataInputStream dis = new DataInputStream(new FileInputStream(file1));
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                DataOutputStream dos2 = new DataOutputStream(client.getOutputStream());

                byte[] buf1 = new byte[1024];
                byte[] buf2 = new byte[1024];
                long totalReadBytes = 0;
                int readBytes;
                FileInputStream fis = null;
                byte[] bt = new byte[(int) file1.length()];
                fis = new FileInputStream(file1);
                fis.read(bt);
                strBase64 = new String(Base64.encodeToString(bt,Base64.NO_WRAP));






                buf2 = result.getBytes();
                dos.write(buf2,0, buf2.length);


                while ((readBytes = dis.read(buf1)) != -1){

                    dos.write(buf1,0,readBytes);
                    totalReadBytes += readBytes;
                    System.out.println(totalReadBytes);

                }

                System.out.println("데이터 전송 완료");


                dos.close();
                client.close();


            } catch (IOException e) {

                e.printStackTrace();
            }


            //while(true) {
                try {
                    client = new Socket(SERVER_IP, 8079);
                    dataOutput = new DataOutputStream(client.getOutputStream());
                    dataInput = new DataInputStream(client.getInputStream());


                    byte[] buf = new byte[100];
                    int read_Byte = dataInput.read(buf);
                    input_message = new String(buf, 0, read_Byte);
                    if (!input_message.equals(STOP_MSG)) {
                        publishProgress(input_message);
                    } else {
                        System.out.println("종료!");


                    }
                    dataOutput.close();
                    dataInput.close();
                    client.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            //}

            return null;
        }



        protected void onProgressUpdate(String... params){
            animal_kind.setText(""); // Clear the chat box
            if(params[0].equals("fail")){
                Toast myToast = Toast.makeText(InsertActivity.this,"파이썬 서버 에러" , Toast.LENGTH_SHORT);
                myToast.show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);

                loading.dismiss();
            }
            else {
                animal_kind.append(params[0]);
                loading.dismiss();

            }

        }

    }
//이미지 Base64로 변환

    public class ProgressDialog extends Dialog
    {
        public ProgressDialog(Context context)
        {
            super(context);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.loading);

        }
    }







}