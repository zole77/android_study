package org.techtown.inflater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuAcitivity extends AppCompatActivity {

    LinearLayout container;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_acitivity);

        container = findViewById(R.id.container);


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout();
            }
        });


    }

    public void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(getBaseContext().LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub1, container, true);

        Toast.makeText(this, "부분 화면이 추가되었습니다", Toast.LENGTH_LONG).show();
    }

}