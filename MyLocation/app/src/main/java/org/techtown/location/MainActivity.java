package org.techtown.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    public void startLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        long minTime = 10000;   // 10초마다 한번씩 위치 알려달라
        float minDistance = 0;  // ~ 거리만큼 이동했을 때 알려달라
        try{
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            textView.setText("내 위치: " + location.getLatitude() + ", " + location.getLongitude());
                            // getLatitude 위도 정보
                            // getLongtitude 경도 정보
                        }
                    });
        }catch(SecurityException e){
            e.printStackTrace();
        }

        showToast("위치가 요청되었습니다.");
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}