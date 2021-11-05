package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 =findViewById(R.id.button2);
         Button abu=findViewById(R.id.button);
        Button btn2 =findViewById(R.id.button3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmain();
            }
        });
        abu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openabout();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainv();
            }
        });

    }
    public void openmain(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void openabout(){
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
    public void openmainv(){
        Intent intent = new Intent(this,MainActivity4.class);
        startActivity(intent);
    }

}