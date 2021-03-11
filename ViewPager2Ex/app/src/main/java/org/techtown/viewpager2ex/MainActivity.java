package org.techtown.viewpager2ex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    List<User> userList;
    UserAdapter userAdapter;

    MaterialButton materialButton;
    TextView text_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager2);
        materialButton = findViewById(R.id.button);
        text_view = findViewById(R.id.text_view);

        userList = new ArrayList<>();
        userList.add(new User(R.drawable.ic_baseline, "Kim", "22"));
        userList.add(new User(R.drawable.ic_android, "Lee", "23"));
        userList.add(new User(R.drawable.ic_bedtime, "Park", "26"));

        userAdapter = new UserAdapter(this, userList);
        viewPager2.setAdapter(userAdapter);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem() + 1 < userAdapter.getItemCount()){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if(position == userAdapter.getItemCount() - 1){
                    materialButton.setText("Start");
                }else{
                    materialButton.setText("Next");
                }

                text_view.setText((position + 1) + " / " + userAdapter.getItemCount());
            }
        });
    }


}