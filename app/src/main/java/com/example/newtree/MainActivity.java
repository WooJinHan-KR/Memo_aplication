package com.example.newtree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    Button button2;
    Button button3;
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_NewTree);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("on","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);





    }
    @Override
    protected  void onStart(){
        super.onStart();
        Log.i("on","onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button: Intent intent = new Intent(MainActivity.this, MainActivity2.class); startActivity(intent); break;
            case R.id.button2: Intent intent2 = new Intent(MainActivity.this, MainActivity3.class); startActivity(intent2); break;
            case R.id.button3: Intent intent3 = new Intent(MainActivity.this, MainActivity4.class); startActivity(intent3); break;
            case R.id.button4: Intent intent4 = new Intent(MainActivity.this, MainActivity5.class); startActivity(intent4); break;
        }

    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("on","onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();

    }


}