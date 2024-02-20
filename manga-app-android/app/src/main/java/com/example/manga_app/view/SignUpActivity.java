package com.example.manga_app.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;

import com.example.manga_app.Service.RetrofitService;
import com.example.manga_app.model.User;
import com.example.manga_app.model.role;
import com.example.manga_app.Service.UserApiService;
import com.example.manga_app.DTO.RegisterRequest;
import com.example.manga_app.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private EditText signupEmail, signupPassword, signupPasswordConfirm, userName;
    private Button signupButton;
    private TextView loginRedirectText;

    private ImageView personalPhoto;
    private final int GALLERY_REQ_CODE = 1000;
    private byte[] userImage;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        personalPhoto = findViewById(R.id.personalPhoto);
        signupPasswordConfirm = findViewById(R.id.signup_password_confirm);
        userName = findViewById(R.id.userName);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        RetrofitService service = new RetrofitService();
        UserApiService userApiService = service.getRetrofit().create(UserApiService.class);

        personalPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String pass_conf = signupPasswordConfirm.getText().toString().trim();
                String userName_str = userName.getText().toString().trim();
                if (email.isEmpty()) {
                    reInitializeEditText();
                    signupEmail.setError("Email cannot be empty");
                    signupEmail.setBackgroundResource(R.drawable.edittext_error);
                    Toast.makeText(getApplicationContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (pass.isEmpty()) {
                    reInitializeEditText();
                    signupPassword.setError("Password cannot be empty");
                    signupPassword.setBackgroundResource(R.drawable.edittext_error);
                    Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (pass_conf.isEmpty()) {
                    reInitializeEditText();
                    signupPasswordConfirm.setError("You must confirm your Password");
                    signupPasswordConfirm.setBackgroundResource(R.drawable.edittext_error);
                    Toast.makeText(getApplicationContext(), "You must confirm your Password", Toast.LENGTH_SHORT).show();
                }
                else if (!pass_conf.equals(pass)) {
                    reInitializeEditText();
                    signupPassword.setError("Passwords must match");
                    signupPassword.setBackgroundResource(R.drawable.edittext_error);
                    Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_SHORT).show();
                }
                else if (!validatePassword(pass)) {
                    reInitializeEditText();
                    signupPassword.setError("Invalid Password\nMinimum 8 characters and maximum 15 characters\n" +
                            "It must contain at least one number, one lowercase letter, and one uppercase letter.");
                    signupPassword.setBackgroundResource(R.drawable.edittext_error);
                    Toast.makeText(getApplicationContext(), "Invalid Password\nMinimum 8 characters and maximum 15 characters\nIt must " +
                            "contain at least one number, one lowercase letter, and one uppercase letter.", Toast.LENGTH_SHORT).show();
                }
                else if (!RegisterRequest.isValidName(userName_str)) {
                    reInitializeEditText();
                    userName.setError("Invalid UserName\nIt must be between 3 and 20 characters");
                    userName.setBackgroundResource(R.drawable.edittext_error);
                    Toast.makeText(getApplicationContext(), "Invalid Name\nThe name must be 3 and 20 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    RegisterRequest registerRequest = new RegisterRequest(userName_str,email , pass , role.admin , userImage);
                    userApiService.saveUser(registerRequest).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            Log.d("Response", "Data sent successfully" +"   "+ response.body().getUsername());
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("Error", t.toString());
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_REQ_CODE){
                selectedImageUri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[9999999];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                    }
                    userImage = byteArrayOutputStream.toByteArray();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void reInitializeEditText(){
        signupEmail.setBackgroundResource(R.drawable.custom_edittext);
        signupPassword.setBackgroundResource(R.drawable.custom_edittext);
        signupPasswordConfirm.setBackgroundResource(R.drawable.custom_edittext);
        userName.setBackgroundResource(R.drawable.custom_edittext);
    }

    public static boolean validatePassword(String pass) {
        // Minimum 8 characters and maximum 15 characters
        if (pass.length() < 8 || pass.length() > 15) {
            return false;
        }

        // At least one number, one lowercase letter, and one uppercase letter
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}