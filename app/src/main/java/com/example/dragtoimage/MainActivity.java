package com.example.dragtoimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

//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

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ImageView Toilet, Poop;
    float xDown = 0;
    float yDown = 0;

    Rect rc1, rc2;

    int Points = 0;
    TextView pointsTv;

    DisplayMetrics displaymetrics;
    int yAbs;

    int fallSpeed = 5000;

    boolean stopThread;

    float bottomOfScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toilet = findViewById(R.id.poop);
        Poop = findViewById(R.id.dote);
        pointsTv = findViewById(R.id.pointsTv);
        pointsTv.setAlpha((float) 0.5);

        Toilet.setOnTouchListener(this);

        yAbs = (int) Poop.getY();
        displaymetrics = new DisplayMetrics();

        bottomOfScreen = getResources().getDisplayMetrics()
                .heightPixels - (Poop.getHeight() * 4);

        Poop.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(fallSpeed);

        startThread();


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        touchAndRestart();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                yDown = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float movedX , movedY;
                movedX = event.getX();
                movedY = event.getY();

                float distanceX = movedX -xDown;
                float distanceY = movedY -yDown;

                Toilet.setX(Toilet.getX() + distanceX);

                break;

            case MotionEvent.ACTION_UP:
        }



        return true;
    }


    public void startThread() {
        stopThread = false;
        TouchThread runnable = new TouchThread(this);
        new Thread(runnable).start();

    }

    public void stopThread() {
        stopThread = true;
        restart();
    }

    public void restart(){
        int random = (int)(Math.random()*1000);
        fallSpeed -= 250;
        if(fallSpeed <= 250){
            fallSpeed = 500;
        }
        Poop.setX(random);
        Poop.setY(yAbs);
        Poop.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(fallSpeed);
        Points++;
        pointsTv.setText("Points: " + Points);
    }

    public boolean touch(){
        rc1 = new Rect();
        Toilet.getHitRect(rc1);
        rc2 = new Rect();
        Poop.getHitRect(rc2);

        if (Rect.intersects(rc1, rc2)){

            return true;

        }
        return false;
    }

    public void touchAndRestart(){
        rc1 = new Rect();
        Toilet.getHitRect(rc1);
        rc2 = new Rect();
        Poop.getHitRect(rc2);

        if (touch()){

            restart();

        }
    }
}

    class TouchThread extends Thread {

    boolean always = false;

    Context context;

    TouchThread(Context context){
        this.context = context;
    }

        @Override
        public void run() {

            while(!always) {
                if(((MainActivity)context).touch()){
                    ((MainActivity)context).stopThread();
//                    ((MainActivity)context).restart();
                }
            }
        }
    }

