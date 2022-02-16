package com.example.dragtoimage;

import androidx.appcompat.app.AppCompatActivity;
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

    ImageView poop, dote;
    float xDown = 0;
    float yDown = 0;

    Rect rc1, rc2;

    int Points = 0;
    TextView pointsTv;

    DisplayMetrics displaymetrics;
    int yAbs;

    int fallSpeed = 5000;

    float bottomOfScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poop = findViewById(R.id.poop);
        dote = findViewById(R.id.dote);
        pointsTv = findViewById(R.id.pointsTv);
        pointsTv.setAlpha((float) 0.5);

        poop.setOnTouchListener(this);

        yAbs = (int)dote.getY();
        displaymetrics = new DisplayMetrics();

        bottomOfScreen = getResources().getDisplayMetrics()
                .heightPixels - (dote.getHeight() * 4);

        dote.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(fallSpeed);

    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {



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

                poop.setX(poop.getX() + distanceX);
                //poop.setY(poop.getY() + distanceY);


                break;

            case MotionEvent.ACTION_UP:
        }

        rc1 = new Rect();
        poop.getHitRect(rc1);
        rc2 = new Rect();
        dote.getHitRect(rc2);

        if (Rect.intersects(rc1, rc2)){
            int random = (int)(Math.random()*1000);
            fallSpeed -= 500;
            if(fallSpeed <= 0){
                fallSpeed = 500;
            }
            dote.setX(random);
            dote.setY(yAbs);
            dote.animate()
                    .translationY(bottomOfScreen)
                    .setInterpolator(new AccelerateInterpolator())
                    .setDuration(fallSpeed);
            Points++;
            pointsTv.setText("Points: " + Points);

        }

        return true;
    }
}

