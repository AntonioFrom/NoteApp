package com.example.noteapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapp.R;
import com.example.noteapp.database.NotesDatabase;
import com.example.noteapp.entities.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteSubtitle, inputNoteText;
    private TextView textDateTime;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView imageBack = findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> onBackPressed());
        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteText = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.inputTextDateTime);

        textDateTime.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH;mm a", Locale.getDefault())
                .format(new Date()));
        ImageView imageSave =findViewById(R.id.image_save);
        imageSave.setOnClickListener(v -> saveNote());

    }

    private void saveNote() {
        if (inputNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note title can't be empty!", Toast.LENGTH_LONG).show();
            return;
        } else if (inputNoteSubtitle.getText().toString().trim().isEmpty()
                && inputNoteText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note can't be empty!", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            final Note note = new Note();
            note.setTitle(inputNoteTitle.getText().toString());
            note.setSubtitile(inputNoteSubtitle.getText().toString());
            note.setNoteText(inputNoteText.getText().toString());
            note.setDateTime(textDateTime.getText().toString());

            @SuppressLint("StaticFieldLeak")
            class SaveNoteTask extends AsyncTask<Void,Void,Void>{

                @Override
                protected Void doInBackground(Void... voids) {
                    NotesDatabase.getNotesDatabase(getApplicationContext()).noteDao().insertNote(note);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Intent intent = new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
            new SaveNoteTask().execute();
        }
    }
}