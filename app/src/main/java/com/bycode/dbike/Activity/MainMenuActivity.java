package com.bycode.dbike.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bycode.dbike.Config.ConfigFirebase;
import com.bycode.dbike.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity {

    private FirebaseAuth autentification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Dbike");
        setSupportActionBar(toolbar);

        autentification = ConfigFirebase.getFirebaseAutentification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings :
                deslogarUsuario();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
            case R.id.action_maps:
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario(){
        try {
            autentification.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
