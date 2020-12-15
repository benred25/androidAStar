package com.example.a_star;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.draw:
                draw();
                break;
            case R.id.erase:
                erase();
                break;
            case R.id.start:
                start();
                break;
            case R.id.clear:
                clear();
                break;
        }
        return true;
    }

    public void draw() {

    }

    public void erase() {

    }

    public void start() {

    }

    public void clear() {

    }
}