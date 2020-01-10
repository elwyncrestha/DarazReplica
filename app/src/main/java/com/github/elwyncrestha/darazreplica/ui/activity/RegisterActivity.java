package com.github.elwyncrestha.darazreplica.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.elwyncrestha.darazreplica.R;
import com.github.elwyncrestha.darazreplica.endpoint.DarazService;
import com.github.elwyncrestha.darazreplica.endpoint.RetrofitUtils;
import com.github.elwyncrestha.darazreplica.model.User;
import com.github.elwyncrestha.darazreplica.util.StrictMode;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.etFullName = findViewById(R.id.etFullName);
        this.etEmail = findViewById(R.id.etEmail);
        this.etPassword = findViewById(R.id.etPassword);
        this.btnRegister = findViewById(R.id.btnRegister);

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                StrictMode.StrictMode();
                DarazService darazService = RetrofitUtils.getRetrofit().create(DarazService.class);
                Call<User> userCall = darazService.register(new User(name, email, password));
                try {
                    Response<User> loginResponse = userCall.execute();
                    if (loginResponse.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registered!!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), AppLoginActivity.class));
                        finish();
                    }
                } catch (IOException e) {
                    Log.e("Register Error", e.getLocalizedMessage(), e);
                    Toast.makeText(RegisterActivity.this, "Failed to register!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
