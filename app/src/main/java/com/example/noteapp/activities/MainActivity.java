package com.example.noteapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.noteapp.R;
import com.example.noteapp.activities.CreateNoteActivity;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(v ->
                startActivityForResult(new Intent(getApplicationContext(), CreateNoteActivity.class)
                        , REQUEST_CODE_ADD_NOTE));
    }
}