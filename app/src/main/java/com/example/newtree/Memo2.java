package com.example.newtree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Memo2 extends AppCompatActivity {


    private Button btn_Save;
    private Button btn_Load;
    private Button btn_Delete;

    private EditText editText_TextArea2;
    private String fileName = "MyMemo2.txt";
    private long backPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo2);


        btn_Save = findViewById(R.id.button_save);
        btn_Load = findViewById(R.id.button_load);
        btn_Delete = findViewById(R.id.button_delete);
        editText_TextArea2 = findViewById(R.id.editText3);

        btn_Save.setOnClickListener(btnSaveListener);
        btn_Load.setOnClickListener(btnLoadListener);
        btn_Delete.setOnClickListener(btnDeleteListener);
    }


    View.OnClickListener btnLoadListener = new View.OnClickListener()
    {

        public void onClick(View v)
        {

            FileInputStream inputStream = null;

            try
            {
                inputStream = openFileInput(fileName);

                byte[] data = new byte[inputStream.available()];
                while(inputStream.read(data) != -1) {}


                editText_TextArea2.setText(new String(data));

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
                    //파일 읽기 성공 여부 상관없이 반드시 스트림 닫기
                    if(inputStream != null)
                        inputStream.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    };


    View.OnClickListener btnSaveListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {

            FileOutputStream outputStream = null;

            try
            {
                outputStream = openFileOutput(fileName, MODE_PRIVATE);

                outputStream.write(editText_TextArea2.getText().toString().getBytes());
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
