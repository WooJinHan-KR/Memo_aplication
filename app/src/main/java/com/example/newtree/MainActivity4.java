package com.example.newtree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity4 extends AppCompatActivity {

    private Button btn_Save;
    private Button btn_Load;
    private Button btn_Delete;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn_ba;

    private EditText editText_TextArea;
    private String fileName = "MyMemo.txt";
    private long backPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        btn_Save = findViewById(R.id.button_save);
        btn_Load = findViewById(R.id.button_load);
        btn_Delete = findViewById(R.id.button_delete);
        editText_TextArea = findViewById(R.id.editText3);
        btn_ba = findViewById(R.id.button);
        btn1 =findViewById(R.id.button_load2);
        btn2 =findViewById(R.id.button_load3);
        btn3 =findViewById(R.id.button_load4);
        btn4 =findViewById(R.id.button_load5);
        btn5 =findViewById(R.id.button_load6);

        btn_Save.setOnClickListener(btnSaveListener);
        btn_Load.setOnClickListener(btnLoadListener);
        btn_Delete.setOnClickListener(btnDeleteListener);
        btn1.setOnClickListener(btnLoadListener);
        btn2.setOnClickListener(btnLoadListener);
        btn3.setOnClickListener(btnLoadListener);
        btn4.setOnClickListener(btnLoadListener);
        btn5.setOnClickListener(btnLoadListener);
        btn_ba.setOnClickListener(btnLoadListener);
    }





    View.OnClickListener btnLoadListener = new View.OnClickListener()
    {

        public void onClick(View v)
        {    switch (v.getId()){
            case R.id.button_load:

                FileInputStream inputStream = null;

                try
                {
                    inputStream = openFileInput(fileName);

                    byte[] data = new byte[inputStream.available()];
                    while(inputStream.read(data) != -1) {}

                    editText_TextArea.setText(new String(data));

                    Toast.makeText(getApplicationContext(), "로드 성공", Toast.LENGTH_LONG).show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {

                        if(inputStream != null)
                            inputStream.close();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button_load2:
                Intent intenta = new Intent(getBaseContext(), Memo1.class);
                startActivity(intenta);
                break;
            case R.id.button_load3:
                Intent intents = new Intent(getBaseContext(), Memo2.class);
                startActivity(intents);
                break;
            case R.id.button_load4:
                Intent intentd = new Intent(getBaseContext(), Memo3.class);
                startActivity(intentd);
                break;
            case  R.id.button_load5:
                Intent intentf = new Intent(getBaseContext(), Memo4.class);
                startActivity(intentf);
                break;
            case R.id.button_load6:
                Intent intentg = new Intent(getBaseContext(), Memo5.class);
                startActivity(intentg);
                break;
            case R.id.button:
                Intent intenth = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intenth);
                break;
        }}

    };


    View.OnClickListener btnSaveListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            FileOutputStream outputStream = null;

            try
            {
                outputStream = openFileOutput(fileName, MODE_PRIVATE);
                outputStream.write(editText_TextArea.getText().toString().getBytes());
                outputStream.close();

                Toast.makeText(getApplicationContext(), "저장 성공", Toast.LENGTH_LONG).show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    };


    View.OnClickListener btnDeleteListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            boolean bDel = deleteFile(fileName);
            if(bDel)
                Toast.makeText(getApplicationContext(), "메모 삭제 완료", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "메모 삭제 실패", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onBackPressed()
    {
        if(System.currentTimeMillis() - backPressTime >= 2000)
        {
            backPressTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "백(Back) 버튼을 한 번 더 누르면 종료", Toast.LENGTH_LONG).show();
        }
        else if(System.currentTimeMillis() - backPressTime < 2000)
            finish();
    }




}