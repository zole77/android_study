package org.techtown.kakaologinex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SubActivity extends AppCompatActivity {

    private String strNick, strProfileImg, strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");
        strEmail = intent.getStringExtra("email");

        TextView tv_nick = findViewById(R.id.tv_nickname);
        TextView tv_email = findViewById(R.id.tv_email);
        ImageView iv_profile = findViewById(R.id.iv_profile);

        // 닉네임 세팅
        tv_nick.setText(strNick);
        // 이메일 세팅
        tv_email.setText(strEmail);

        // 프로필 이미지 사진 세팅
        Glide.with(this).load(strProfileImg).into(iv_profile);

    }
}