package edu.fje.dam2.projecte_simon;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import edu.fje.dam2.projecte_simon.R;

/**
 * Classe que demostra el funcionament de Canvas.
 * Dibuixa directament sobre una View
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 */

class M06_VistaPropia extends View
{
    private float x;
    private float y;
    private String cadena;
    private String clicks;

    private int val;
    private int primerBucle = 0;

    private int color;
    public M06_VistaPropia(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.M06_VistaPropia,
                0, 0);

        try {
            x = a.getFloat(R.styleable.M06_VistaPropia_x, 0);
            cadena = a.getString(R.styleable.M06_VistaPropia_cadena);

        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ////
        Log.i("colors", "Entra a onDraw");
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        Log.i("VISTA2", getColor() + " - " + getX() + "," + getY());
        canvas.drawRect(getX() + x, getY() + y, 550  , 550, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int xAct = 0;
        int yAct = 0;
        int color = 0;
        int nColor = 0;
        val = 0;

        if(x < 550 && y < 550){
            xAct = 0;
            yAct = 0;
            color = Color.parseColor("#246C14");
            nColor = Color.GREEN;
            val = 1;
            Log.i("colors","COLOR VERD");
        }

        else if(x > 550 && y < 550){
            xAct = 550;
            yAct = 0;
            color = Color.parseColor("#940000");
            nColor = Color.RED;
            val = 2;
            Log.i("colors","COLOR VERMELL");
        }

        else if(x < 550 && y > 550){
            xAct = 0;
            yAct = 550;
            color = Color.parseColor("#F4B904");
            nColor = Color.YELLOW;
            val = 3;
            Log.i("colors","COLOR GROC");
        }

        else if(x > 550 && y > 550){
            xAct = 550;
            yAct = 550;
            color = Color.BLUE;
            nColor = Color.CYAN;
            val = 4;
            Log.i("colors","COLOR BLAU");
        }

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                this.x = xAct;
                this.y = yAct;
                this.color= nColor;
                this.invalidate();
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                this.x = xAct;
                this.y = yAct;
                this.color= color;
                this.cadena = cadena + "," + val;

                Log.i("CLICKS", cadena);
                this.invalidate();
                invalidate();
                break;
        }

        return true;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.primerBucle = 0;
        this.cadena = cadena;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }


    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getClicks() {
        return clicks;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void pintaPatro(int torn){
        int xAct = 0;
        int yAct = 0;
        int nColor = 0;

        Log.i("VAL", String.valueOf(torn));

        if(torn == 1){
            xAct = 0;
            yAct = 0;
            nColor = Color.GREEN;
            val = 1;
        }

        else if(torn == 2){
            xAct = 550;
            yAct = 0;
            nColor = Color.RED;
            val = 2;
        }

        else if(torn == 3){
            xAct = 0;
            yAct = 550;
            nColor = Color.YELLOW;
            val = 3;
        }

        else if(torn == 4){
            xAct = 550;
            yAct = 550;
            nColor = Color.CYAN;
            val = 4;
        }

        this.x = xAct;
        this.y = yAct;
        this.color= nColor;
        this.invalidate();

    }

    public void resetSimon(int torn){
        int xAct = 0;
        int yAct = 0;
        int nColor = 0;

        Log.i("VAL", String.valueOf(torn));

        if(torn == 1){
            xAct = 0;
            yAct = 0;
            nColor = Color.parseColor("#246C14");
            val = 1;
        }

        else if(torn == 2){
            xAct = 550;
            yAct = 0;
            nColor = Color.parseColor("#940000");
            val = 2;
        }

        else if(torn == 3){
            xAct = 0;
            yAct = 550;
            nColor = Color.parseColor("#F4B904");
            val = 3;
        }

        else if(torn == 4){
            xAct = 550;
            yAct = 550;
            nColor = Color.BLUE;
            val = 4;
        }

        this.x = xAct;
        this.y = yAct;
        this.color= nColor;
        this.invalidate();

    }

}