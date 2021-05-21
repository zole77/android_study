package org.techtown.viewpager2guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    List<Guide> guideList;
    GuideAdapter guideAdapter;

    MaterialButton materialButton;
    TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager2 = findViewById(R.id.viewPager2);
        materialButton = findViewById(R.id.button);
        text_view = findViewById(R.id.text_view);

        guideList = new ArrayList<>();

        guideList.add(new Guide(R.drawable.guide1));
        guideList.add(new Guide(R.drawable.guide2));
        guideList.add(new Guide(R.drawable.guide3));

        guideAdapter = new GuideAdapter(this, guideList);
        viewPager2.setAdapter(guideAdapter);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewPager2.getCurrentItem() + 1 < guideAdapter.getItemCount()){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if(position == guideAdapter.getItemCount() - 1){
                    materialButton.setText("시작하기");
                }else{
                    materialButton.setText("다음");
                }

                text_view.setText((position + 1) + " / " + guideAdapter.getItemCount());
            }
        });
    }
}
