package com.github.elwyncrestha.darazreplica.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.elwyncrestha.darazreplica.R;
import com.github.elwyncrestha.darazreplica.util.StrictMode;
import com.github.elwyncrestha.darazreplica.util.UserUtils;

public class AppLoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etMobileNumberOrEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);

        this.etMobileNumberOrEmail = findViewById(R.id.etMobileNumberOrEmail);
        this.etPassword = findViewById(R.id.etPassword);
        this.btnLogin = findViewById(R.id.btnLogin);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etMobileNumberOrEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                StrictMode.StrictMode();
                if (UserUtils.checkUser(email, password)) {
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Either username or password is incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
