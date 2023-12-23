package com.example.variant2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText editTextLogin, editTextPassword;
    Button button;
    DataBaseHelper mDatabaseHelper; // Добавляем экземпляр класса DataBaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем экземпляр DataBaseHelper
        mDatabaseHelper = new DataBaseHelper(this);

        // Находим элементы управления
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();

                // Проверяем, существует ли введенный логин в базе данных и верен ли пароль
                if (mDatabaseHelper.checkUser(login, password)) {
                    // Если логин и пароль совпадают, запускаем следующую активность
                    Intent intent = new Intent(com.example.variant2.login.this, personal_area.class);
                    startActivity(intent);
                } else {
                    // Если логин и/или пароль неверны, выполняем какие-то действия, например, выводим сообщение об ошибке
                    Toast.makeText(com.example.variant2.login.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}