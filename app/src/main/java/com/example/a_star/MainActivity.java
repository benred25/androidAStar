package com.example.a_star;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    SketchController controller = new SketchController();
    SketchModel model = new SketchModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SketchView sketchView = new SketchView(this);

        LinearLayout root = findViewById(R.id.root);
        root.addView(sketchView);

        sketchView.setModel(model);
        sketchView.setController(controller);
        controller.setModel(model);
        model.addSubscriber(sketchView);
        model.spotHeight = sketchView.rowGap;
        model.spotWidth = sketchView.colGap;
        model.createGrid();




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