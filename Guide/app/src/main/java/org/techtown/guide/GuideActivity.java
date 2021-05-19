package org.techtown.guide;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {

    ViewFlipper flipper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.guide);

        flipper = (ViewFlipper) findViewById(R.id.flipper);

        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(R.drawable.image01 + i);
            flipper.addView(img);
        }

        Animation showIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(showIn);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }


    public void mOnClick(View v) {
        switch (v.getId()) {

            case R.id.btn_previous:

                flipper.showPrevious();
                break;

            case R.id.btn_next:

                flipper.showNext();

                break;
            case R.id.btn_fin:
                finish();
        }
    }


}
