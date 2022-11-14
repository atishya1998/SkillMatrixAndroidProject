package com.example.skillmatrix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.skillmatrix.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Notify HR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_notify_hr, R.id.nav_scheduler)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*  @Override
      public boolean onCreateOptionsMenu (Menu m){
          super.onCreateOptionsMenu(m);
          getMenuInflater().inflate(R.menu.main, m );
          return true;
      }

     */
    public boolean onOptionsItemSelected(MenuItem mi) {
        super.onOptionsItemSelected(mi);

        // setContentView(R.layout.activity_test_toolbar);
        switch (mi.getItemId()) {
            case R.id.action_settings:
                //Start an activity…
                Log.d("Toolbar", "Version 2.4 by Atishya Reddy");
                Toast.makeText(getApplicationContext(), "Version 2.4 by Atishya Reddy", Toast.LENGTH_SHORT).show();

                break;
            case R.id.action_logout:
                //Start an activity…

                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                break;
                case R.id.action_edit_profile:
                Intent i2 = new Intent(getApplicationContext(),AddandEditMyProfile.class);
                startActivity(i2);
                break;
            default:
                break;
        }

        return false;
    }
}