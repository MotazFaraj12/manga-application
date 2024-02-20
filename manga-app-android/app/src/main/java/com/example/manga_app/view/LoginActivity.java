package com.example.manga_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manga_app.DTO.AuthenticationRequest;
import com.example.manga_app.R;
import com.example.manga_app.Service.RetrofitService;
import com.example.manga_app.Service.UserApiService;
import com.example.manga_app.model.AuthUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private MyLoadingButton loginButton;
    private CheckBox rememberMe;
    TextView forgotPassword;
    TextView name;
    final int[] i = {0};
    String[] role = {""};
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String KEY = "myKey";

    public static void saveData(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, text);
        editor.apply();
    }

    public static String loadData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(KEY, "NULL");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.forgot_password);
        rememberMe = findViewById(R.id.rememberMe);
        name = findViewById(R.id.name);
        setLoadingButtonStyle();
        loginButton.setMyButtonClickListener(this::setLoadingButtonStyle);
        RetrofitService service = new RetrofitService();
        UserApiService userApiService = service.getRetrofit().create(UserApiService.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.showLoadingButton();
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        AuthenticationRequest request = new AuthenticationRequest(email,pass);
                        userApiService.authenticate(request).enqueue(new Callback<AuthUser>() {
                            @Override
                            public void onResponse(Call<AuthUser> call, @NonNull Response<AuthUser> response) {
                                if (response.body() != null) {
                                    Log.d("Response", "Data sent successfully");
                                    if (response.body().getRole().toString().matches("admin")) {
                                        Intent intent = new Intent(LoginActivity.this, com.example.manga_app.view.admin.HomeAdmin.class);
                                        intent.putExtra("email", email);
                                        Toast.makeText(LoginActivity.this, "Logged in successfully admin", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    } else if (response.body().getRole().toString().matches("user")) {
                                        Intent intent = new Intent(LoginActivity.this, com.example.manga_app.view.user.HomeUser.class);
                                        intent.putExtra("email", email);
                                        Toast.makeText(LoginActivity.this, "Logged in successfully user", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Couldn't fetch the role", Toast.LENGTH_SHORT).show();
                                    }
                                    finish();
                                } else {
                                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                    Toast.makeText(LoginActivity.this, "Either the email or password is wrong", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                            @Override
                            public void onFailure(Call<AuthUser> call, Throwable t) {
                                Log.e("Error", t.toString());
                            }
                        });

                    } else {
                        loginPassword.setError("Empty fields are not allowed");
                        loginButton.showErrorButton();
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                    loginButton.showErrorButton();
                } else {
                    loginEmail.setError("Please enter correct email");
                    loginButton.showErrorButton();
                }
                if (rememberMe.isChecked()) {
                    saveData(getApplicationContext(), loginEmail.getText().toString());
                } else if (!rememberMe.isChecked()) {
                    saveData(getApplicationContext(), "false");
                }
            }
        });
    }

    private void setLoadingButtonStyle() {

        loginButton.setAnimationDuration(1000)
                .setButtonColor(R.color.lavender)
                .setButtonLabel("Login")
                .setButtonLabelSize(20)
                .setProgressLoaderColor(Color.WHITE)
                .setButtonLabelColor(R.color.white);

    }
}