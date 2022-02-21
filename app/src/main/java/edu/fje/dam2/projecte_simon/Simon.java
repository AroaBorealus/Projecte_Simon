package edu.fje.dam2.projecte_simon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Simon extends AppCompatActivity {

    private boolean isReproduint= true;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(this, bgMusicService.class);
        intent.putExtra("operacio", "inici");
        startService(intent);


        setContentView(R.layout.simon);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(android.R.drawable.ic_media_pause);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;

                if (!isReproduint) {
                    isReproduint = true;
                    text = "Reproduint Audio";
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    intent.putExtra("operacio", "inici");
                    startService(intent);

                } else {
                    isReproduint = false;
                    text = "Pausant Audio";
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    intent.putExtra("operacio", "pausa");
                    startService(intent);
                }

                Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}