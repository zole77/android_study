
package org.techtown.disatermsg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Row> rowData;
    private DisaterAdapter disaterAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public Row row;

    static RequestQueue requestQueue;
    private static final String TAG = "disaster_msg";



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    String key = "pPaSpIZ%2BXFweoQb0rmHH5gguuqHRO00DHw7CgOuW9wZ2c5HDm%2BwqWpv%2B29V9NIHAcggmnJz3ztzM8206Hkkw7A%3D%3D";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        rowData = new ArrayList<>();

        disaterAdapter = new DisaterAdapter(rowData);
        recyclerView.setAdapter(disaterAdapter);

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());

            sendRequest();
        }

//        myRef.child("disasterMsg/0").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                Log.d(TAG, dataSnapshot.toString());
//                HashMap value = dataSnapshot.getValue(HashMap.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }




    public void sendRequest() {

        String url = "https://apixml-5d25d-default-rtdb.firebaseio.com/Msg.json";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        processResponse(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void processResponse(String response) {
//        XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
//        Log.d("xmlToJson: ", xmlToJson.toJson().toString());
        Log.d("firebasejson", response);
        Gson gson = new Gson();
        Digest digestList = gson.fromJson(response, Digest.class);
        for(int i=0;i< digestList.DisasterMsg.row.size(); i++){
            row = digestList.DisasterMsg.row.get(i);
            String str = row.getMsg();
            if(row.getLocation_name().equals("경기도 평택시") || row.getLocation_name().equals("부산광역시 전체")){
                Pattern pattern = Pattern.compile("[(](.*?)[)]");
                Matcher matcher = pattern.matcher(str);

                while (matcher.find()) {  // 일치하는 게 있다면
                    //재난문자 ( ) 안 내용들 모두 들어옴

                    if(matcher.group(1).length() > 2){
                        //요일제외 2글자 이상인 재난문자 선별
                        Pattern pattern2 = Pattern.compile(".*?(길\\b|동\\b|대로\\b|로\\b).*");
                        Matcher matcher2 = pattern2.matcher(matcher.group(1));
                        while(matcher2.find()){
                            // ~길, ~동, ~대로 ~로 글자 전후로 가져옴
                            String filter = matcher2.group();

                            int target_index;
                            if(filter.contains("소독완료")){
                                target_index = filter.indexOf("소독완료");
                                filter = filter.replace(filter.substring(target_index), "");

                            }else if(filter.contains("방역완료")){
                                target_index = filter.indexOf("방역완료");
                                filter = filter.replace(filter.substring(target_index), "");
                            }
                            filter = filter.replaceAll(".*감염경로.*","");
                            Log.d(TAG, filter);

                            //                        if(filter.contains("소독완료") || filter.contains("방역완료") || filter.contains("감염경로")){
                            //                            filter = filter.replaceAll(".*소독완료.*", "");
                            //                        }
                            //filter = filter.replace("소독완료", "");
                        }
                    }
                    if(matcher.group(1) ==  null)
                        break;
                }
                disaterAdapter.addItem(row);
            }
//            if(row.location_name.equals("부산광역시 사하구") || row.location_name.equals("부산광역시 전체")){
//
//                disaterAdapter.addItem(row);
//            }
        }
        disaterAdapter.notifyDataSetChanged();
    }



    public void println(Object data, TextView textView) {
        textView.setText(data.toString());
    }

}