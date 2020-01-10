package com.github.elwyncrestha.darazreplica.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.elwyncrestha.darazreplica.R;

public class LoginActivity extends AppCompatActivity {

    Button btnCreate, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btnCreate = findViewById(R.id.btnCreate);
        this.btnLogin = findViewById(R.id.btnLogin);

        this.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AppLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
