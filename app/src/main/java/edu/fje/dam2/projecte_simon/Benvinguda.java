package edu.fje.dam2.projecte_simon;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Benvinguda extends AppCompatActivity {

    public static final String NOM = "dam2.fje.edu.nom";
    public static final String ID = "dam2.fje.edu.id";

    private Button btJugar;
    private TextView joc;
    private EditText usr;
    List<Jugador> jugadors;

    ListView llistaJugadors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benvinguda);
        jugadors = new ArrayList<>();


        btJugar = (Button) findViewById(R.id.btInici);
        joc = (TextView) findViewById(R.id.textView);
        usr = (EditText) findViewById(R.id.tiUser);
        llistaJugadors =  findViewById(R.id.llistaJugadors);


        AnimatorSet setText = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.up_anim);
        setText.setTarget(joc);
        setText.start();

        AnimatorSet setBtn = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.down_anim);
        setBtn.setTarget(btJugar);
        setBtn.start();

        AnimatorSet setUsr = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.fadein_anim);
        setUsr.setTarget(usr);
        setUsr.start();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("puntuacions");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jugadors.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Jugador jugador = postSnapshot.getValue(Jugador.class);

                    jugadors.add(jugador);
                    Log.i("jugadors",jugador.getNom()+" - "+jugador.getPunts());
                }

                jugadors.sort((o1, o2) -> o2.getPunts().compareTo(o1.getPunts())); //Ordena la colecci?? per puntuaci??

                LlistaJugadors jugadorAdapter = new LlistaJugadors(Benvinguda.this, jugadors);
                llistaJugadors.setAdapter(jugadorAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }



    public void onClickIniciar(View view){
        if(usr.getText().length() == 0){
            Snackbar.make(view, "Introdueix un nom d'usuari", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }else {
            Intent intent = new Intent(this, Simon.class);
            intent.putExtra("usr", usr.getText().toString());
            startActivity(intent);
        }
    }
}
