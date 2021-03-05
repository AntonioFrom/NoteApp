package com.example.noteapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.noteapp.R;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> onBackPressed());
    }
}