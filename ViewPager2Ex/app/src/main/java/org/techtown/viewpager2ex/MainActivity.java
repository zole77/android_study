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
        //유저리스트에 들어갈 요소, icon, name, age 저장
        userList.add(new User(R.drawable.ic_baseline, "Kim", "22"));
        userList.add(new User(R.drawable.ic_android, "Lee", "23"));
        userList.add(new User(R.drawable.ic_bedtime, "Park", "26"));

        //유저어댑터에 Context와 유저리스트 전달
        userAdapter = new UserAdapter(this, userList);
        //뷰페이저2에 어댑터 연결
        viewPager2.setAdapter(userAdapter);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다음버튼을 누를때마다 뷰페이저 아이템 카운트가 하나씩 증가.
                // 이 값이 어댑터가 전달받은 리스트의 사이즈보다 작으면
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