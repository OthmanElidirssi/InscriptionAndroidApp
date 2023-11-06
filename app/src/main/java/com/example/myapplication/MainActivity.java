package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication.filieres.FilieresListActivity;
import com.example.myapplication.roles.RolesViewActivity;
import com.example.myapplication.stdfiliere.StudentFiliere;
import com.example.myapplication.students.StudentsListActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.meny_open,R.string.meny_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Log.d("Test", "Entered");
            if (itemId == R.id.nav_roles) {
                intent = new Intent(MainActivity.this, RolesViewActivity.class);
            } else if (itemId == R.id.nav_filieres) {
                intent = new Intent(MainActivity.this, FilieresListActivity.class);
            } else if (itemId == R.id.nav_students) {
                intent = new Intent(MainActivity.this, StudentsListActivity.class);
            } else if (itemId==R.id.nav_students_per_filiere) {
                intent = new Intent(MainActivity.this, StudentFiliere.class);

            }
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected((item))){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openActivity(Context context,AppCompatActivity activity){
        intent=new Intent(context, activity.getClass());
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}