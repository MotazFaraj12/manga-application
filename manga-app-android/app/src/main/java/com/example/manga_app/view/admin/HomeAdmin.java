package com.example.manga_app.view.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.manga_app.view.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manga_app.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;

public class HomeAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    LinearLayout profile;
    private String email;

    private static final int REQUEST_CODE = 1;

    private static final long UPDATE_INTERVAL = 24 * 60 * 60 * 1000; // 24 hours

    LinearLayout allCourses;
    LinearLayout pendingRegistrationApplications;
    LinearLayout onGoingCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        NavigationView navigationView = findViewById(R.id.nav_view2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        profile = header.findViewById(R.id.profile);
        profile.setOnClickListener(this);
        TextView profileEmail = profile.findViewById(R.id.profilemail);
        ImageView profileImg = profile.findViewById(R.id.profileimage);
        TextView profileName = profile.findViewById(R.id.profilename);

        allCourses = findViewById(R.id.all_courses);
        pendingRegistrationApplications = findViewById(R.id.pending_registration_applications);
        onGoingCourses = findViewById(R.id.ongoing_courses_currently);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profile) {
            Intent intent = new Intent(getApplicationContext(), com.example.manga_app.view.admin.MyProfile.class);
            intent.putExtra("email",email);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.nav_AddManga) {
//            Intent intent = new Intent(getApplicationContext(), AddManga.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_edManga) {
//            Intent intent = new Intent(getApplicationContext(), EditDeleteManga.class);
//            startActivity(intent);
//        } else if (id == R.id.nav_AddChapter) {
//            Intent intent = new Intent(getApplicationContext(), AddChapter.class);
//            intent.putExtra("email",email);
//            startActivity(intent);
//        }else if (id == R.id.nav_MangeUsers) {
//            Intent intent = new Intent(getApplicationContext(), MangeUsers.class);
//            startActivity(intent);
//        }else if (id == R.id.nav_logout) {
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}