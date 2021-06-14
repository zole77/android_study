package org.techtown.lifecycleex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 액티비티 또는 프래그먼트가 생성이 되었을 때 내부구문 실행
        Log.e("onCreate", "ENTER");

        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, subActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart", "ENTER");
    }

    @Override
    protected void onResume() {
        // 중지되어있던 액티비타가 다시 실행됨
        super.onResume();
        Log.e("onResume", "ENTER");
    }

    @Override
    protected void onPause() {
        // 중지 상태일 때 내부구문 실행
        // ex. 홈 버튼을 눌러 바깥으로 잠깐 빠져나갔을 때
        // ex. 다른 액티비티가 활성화 되어있을 때
        super.onPause();
        Log.e("onPause", "ENTER");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop", "ENTER");
    }

    @Override
    protected void onDestroy() {
        // 액티비티 소멸했을 때 내부구문을 실행
        super.onDestroy();
        Log.e("onDestroy", "ENTER");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart", "ENTER");
    }
}