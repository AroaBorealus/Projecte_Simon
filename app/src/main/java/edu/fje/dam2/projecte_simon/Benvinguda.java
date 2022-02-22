package edu.fje.dam2.projecte_simon;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Benvinguda extends AppCompatActivity {

    private Button btJugar;
    private TextView joc;
    AnimationDrawable a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benvinguda);

        btJugar = (Button) findViewById(R.id.btInici);
        joc = (TextView) findViewById(R.id.textView);

        ImageView imatge = (ImageView) findViewById(R.id.sprite);
        imatge.setBackgroundResource(R.drawable.simon);

        Animation simon = AnimationUtils.loadAnimation(this, R.anim.wheel_anim);
        imatge.startAnimation(simon);

        AnimatorSet setText = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.up_anim);
        setText.setTarget(joc);
        setText.start();

        AnimatorSet setBtn = (AnimatorSet)
                AnimatorInflater.loadAnimator(this,R.animator.down_anim);
        setBtn.setTarget(btJugar);
        setBtn.start();

        setBtn.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                ImageView sprite = (ImageView) findViewById(R.id.sprite);
                sprite.setBackgroundResource(R.drawable.sprite);
                a = (AnimationDrawable) sprite.getBackground();
                a.start();
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });


    }



    public void onClickIniciar(View view){
        Intent intent = new Intent(this, Simon.class);

        startActivity(intent);
    }
}
