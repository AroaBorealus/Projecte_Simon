package edu.fje.dam2.projecte_simon;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import edu.fje.dam2.projecte_simon.M06_VistaPropia;

public class Simon extends AppCompatActivity {

    private boolean isReproduint= true;
    private Intent intent;
    private String usr = "a";
    private int ronda = 0;
    private HashMap<Integer,Integer> list = new HashMap<Integer, Integer>();
    private M06_VistaPropia vistaPropia,vistaPropia4,vistaPropia2,vistaPropia3,vistaPropiaError;
    private TextView pts;
    private MediaPlayer mp1,mp2,mp3,mp4;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intentE = getIntent();
        usr = intentE.getStringExtra("usr");
        Log.i("usr",usr);

        mp1 = MediaPlayer.create(this,R.raw.sounds_1);
        mp2 = MediaPlayer.create(this,R.raw.sounds_2);
        mp3 = MediaPlayer.create(this,R.raw.sounds_3);
        mp4 = MediaPlayer.create(this,R.raw.sounds_4);

        intent = new Intent(this, bgMusicService.class);
        intent.putExtra("operacio", "inici");
        startService(intent);

        setContentView(R.layout.simon);

        pts = (TextView) findViewById(R.id.tlPts);

        fab = findViewById(R.id.fab);
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

        vistaPropia = findViewById(R.id.vista);
        vistaPropia2 = findViewById(R.id.vista2);
        vistaPropia3 = findViewById(R.id.vista3);
        vistaPropia4 = findViewById(R.id.vista4);
        vistaPropiaError = findViewById(R.id.vistaError);

        vistaPropia.setColor(Color.parseColor("#246C14"));

        vistaPropia2.setX(550);
        vistaPropia2.setColor(Color.parseColor("#940000"));

        vistaPropia3.setY(550);
        vistaPropia3.setColor(Color.parseColor("#F4B904"));

        vistaPropiaError.setY(550);
        vistaPropiaError.setX(550);
        vistaPropiaError.setColor(Color.BLUE);

        vistaPropia4.setY(550);
        vistaPropia4.setX(550);
        vistaPropia4.setColor(Color.BLUE);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("puntuacions");

        //myRef.setValue("Hello, World!");
        myRef.child("Adri").setValue("22");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("dbs", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("dbs", "Failed to read value.", error.toException());
            }
        });


    }
    

    public void onClickBtPatron(View v){
        int nouPat = (int) ((Math.random() * (4 - 1)) + 1);
        int delay = (list.size()+1) * 2000;

        list.put(delay,nouPat);

        fesPatro();
        resetSimon();
    }

    public void onClickBtEnviar(View v){
        String clicks = vistaPropia4.getCadena();

        Log.i("PATRO", "Jugador: "+clicks);



        vistaPropia4.setCadena("");

        String PatroMaquina = "";
        for(final Map.Entry<Integer, Integer> entry : list.entrySet()){
            PatroMaquina += "," + entry.getValue();
        }

        Log.i("PATRO","Màquina: "+PatroMaquina);

        String[] partsClicks = clicks.split(",");
        String[] parts = PatroMaquina.split(",");


        if(parts.length == partsClicks.length){
            for(int cnt = 0; cnt < parts.length; cnt++){
                if(!(parts[cnt].equals(partsClicks[cnt]))){
                    isReproduint = false;
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    intent.putExtra("operacio", "pausa");
                    startService(intent);

                    Intent intentFnl = new Intent(this,Final.class);
                    intentFnl.putExtra("usr", usr);
                    intentFnl.putExtra("pts", ronda);
                    startActivity(intentFnl);
                }else{
                    Toast.makeText(getApplicationContext(), "CORRECTE", Toast.LENGTH_SHORT).show();
                    pts.setText(String.valueOf(cnt));
                    ronda = cnt;
                }
            }
        }else {
            isReproduint = false;
            fab.setImageResource(android.R.drawable.ic_media_play);
            intent.putExtra("operacio", "pausa");
            startService(intent);

            Intent intentFnl = new Intent(this,Final.class);
            intentFnl.putExtra("usr", usr);
            intentFnl.putExtra("pts", ronda);
            startActivity(intentFnl);
        };
    }

    public void fesPatro(){
        for(final Map.Entry<Integer, Integer> entry : list.entrySet()){
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {

                        public void run() {
                            if (entry.getValue() == 1) {
                                vistaPropia.pintaPatro(1);
                                mp1.start();
                                vistaPropia2.resetSimon(2);
                                vistaPropia3.resetSimon(3);
                                vistaPropia4.resetSimon(4);

                            } else if (entry.getValue() == 2) {
                                vistaPropia2.pintaPatro(2);
                                mp2.start();
                                vistaPropia.resetSimon(1);
                                vistaPropia3.resetSimon(3);
                                vistaPropia4.resetSimon(4);

                            } else if (entry.getValue() == 3) {
                                vistaPropia3.pintaPatro(3);
                                mp3.start();
                                vistaPropia2.resetSimon(2);
                                vistaPropia.resetSimon(1);
                                vistaPropia4.resetSimon(4);

                            } else if (entry.getValue() == 4) {
                                vistaPropia4.pintaPatro(4);
                                mp4.start();
                                vistaPropia2.resetSimon(2);
                                vistaPropia.resetSimon(1);
                                vistaPropia3.resetSimon(3);
                            }

                        }
                    },
                    entry.getKey());
        }
    }

    public void resetSimon(){
        for(final int i: list.values()){
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        public void run() {
                            if (i == 1) {
                                vistaPropia.resetSimon(1);
                            } if (i == 2) {
                                vistaPropia2.resetSimon(2);
                            }if (i == 3) {
                                vistaPropia3.resetSimon(3);
                            }if (i == 4) {
                                vistaPropia4.resetSimon(4);
                            }
                        }
                    },
                    (list.size()+1) * 2000);
        }
    }



}