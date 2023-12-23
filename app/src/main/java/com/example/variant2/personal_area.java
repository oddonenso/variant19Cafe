package com.example.variant2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class personal_area extends AppCompatActivity {
    private DataBaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        mDatabaseHelper = new DataBaseHelper(this);

        String username = getUsernameFromDatabase();

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Доброе утро, " + username);

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personal_area.this, search.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("Range")
    private String getUsernameFromDatabase() {
        String username = "";
        Cursor data = mDatabaseHelper.getData();
        if (data != null && data.moveToFirst()) {
            username = data.getString(data.getColumnIndex("LOGIN"));
        }
        if (data != null) {
            data.close();
        }
        return username;
    }
}