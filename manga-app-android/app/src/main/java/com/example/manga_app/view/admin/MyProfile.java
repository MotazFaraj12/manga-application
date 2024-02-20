package com.example.manga_app.view.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.manga_app.model.User;
import com.example.manga_app.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyProfile extends AppCompatActivity {
    private String email;
    private final int GALLERY_REQ_CODE = 1000;
    private ImageView personalPhoto;
    Uri selectedImageUri;
    private User admin;
    private byte[] userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        personalPhoto = findViewById(R.id.personalPhoto);
        TextView emailBox = findViewById(R.id.emailBox);
        TextView emailView = findViewById(R.id.email);
        EditText UserName = findViewById(R.id.UserName);
        TextView name = findViewById(R.id.name);

        admin = new User();

        emailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyProfile.this, "Email uneditable", Toast.LENGTH_SHORT).show();
            }
        });

        personalPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
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


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}