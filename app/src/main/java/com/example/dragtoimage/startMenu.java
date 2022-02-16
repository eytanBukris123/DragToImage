package com.example.dragtoimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startMenu extends AppCompatActivity implements View.OnClickListener {

    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        btnStartGame = findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnStartGame)
        {
            Intent intent = new Intent(startMenu.this , MainActivity.class);
            startActivity(intent);

        }

    }
}
