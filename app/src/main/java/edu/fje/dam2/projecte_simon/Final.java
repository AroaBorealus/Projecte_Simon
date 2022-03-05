package edu.fje.dam2.projecte_simon;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Final extends AppCompatActivity {

    private int ptsIntent = 0;
    private String usr = "a", pts;
    private TextView user,total;
    AnimationDrawable a;


    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intent = getIntent();
        usr = intent.getStringExtra("usr");
        ptsIntent = intent.getIntExtra("pts",0);

        pts = String.valueOf(ptsIntent);

        setContentView(R.layout.finptd);


        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("puntuacions");

        id = myRef.push().getKey();

        Jugador jugador = new Jugador(id, usr, pts);
        myRef.child(id).setValue(jugador);

        user = (TextView) findViewById(R.id.lbUsr);
        user.setText(usr);

        total = (TextView) findViewById(R.id.lbPts);
        total.setText(pts);


        ImageView imatge = (ImageView) findViewById(R.id.sprite);
        imatge.setBackgroundResource(R.drawable.simon);

        Animation simon = AnimationUtils.loadAnimation(this, R.anim.wheel_anim);
        imatge.startAnimation(simon);

        new android.os.Handler(Looper.getMainLooper()).postDelayed(
                new Runnable() {
                    public void run() {
                        ImageView sprite = (ImageView) findViewById(R.id.sprite);
                        sprite.setBackgroundResource(R.drawable.sprite);
                        a = (AnimationDrawable) sprite.getBackground();
                        a.start();
                    }
                },
                4000);

    }

    public void onClickTornar(View view){
        Intent inici = new Intent(this, Benvinguda.class);
        /*inici.putExtra("id",id);
        inici.putExtra("usr",usr);
        inici.putExtra("pts",pts);*/
        startActivity(inici);
    }

}
