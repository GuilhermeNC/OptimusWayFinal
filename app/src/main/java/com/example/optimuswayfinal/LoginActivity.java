package com.example.optimuswayfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;

    Integer verificador = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.username);
        senha = findViewById(R.id.senha);

        String login = email.getText().toString();
        String pass = senha.getText().toString();

    }

    public void telaHome(View view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }

    public void telaRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
