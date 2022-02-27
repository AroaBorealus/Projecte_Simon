package edu.fje.dam2.projecte_simon;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Benvinguda extends AppCompatActivity {

    private Button btJugar;
    private TextView joc;
    private EditText usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benvinguda);

        btJugar = (Button) findViewById(R.id.btInici);
        joc = (TextView) findViewById(R.id.textView);
        usr = (EditText) findViewById(R.id.tiUser);

        AnimatorSet setText = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.up_anim);
        setText.setTarget(joc);
        setText.start();

        AnimatorSet setBtn = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.down_anim);
        setBtn.setTarget(btJugar);
        setBtn.start();


    }



    public void onClickIniciar(View view){
        if(usr.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Introdueix un nom d'usuari", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, Simon.class);
            intent.putExtra("usr", usr.getText().toString());
            startActivity(intent);
        }
    }
}
