package edu.fje.dam2.projecte_simon;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Final extends AppCompatActivity {

    private int pts = 0;
    private String usr = "a";
    private TextView user,total;
    AnimationDrawable a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        usr = intent.getStringExtra("usr");
        pts = intent.getIntExtra("pts",0);

        setContentView(R.layout.finptd);

        user = (TextView) findViewById(R.id.lbUsr);
        user.setText(usr);

        total = (TextView) findViewById(R.id.lbPts);
        total.setText(String.valueOf(pts));


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
        startActivity(inici);
    }

}
