package org.techtown.viewbindingstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.techtown.viewbindingstudy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    /* 바인딩의 핵심:
    fineViewbyid를 사용하지 않고 레이아웃에 펼쳐진 버튼이나 텍스트뷰 등을 사용할 수 있음음
    */
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // Activity 바인딩 객체에 할당 및 뷰 설정
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.tvHello.setText("어플이 처음으로 실행되었음.");
        mainBinding.btnHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "안녕하세요 !", Toast.LENGTH_SHORT).show();
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, new TestFragment());
        ft.commit();
    }
}