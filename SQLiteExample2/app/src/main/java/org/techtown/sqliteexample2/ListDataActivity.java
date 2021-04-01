package org.techtown.sqliteexample2;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    private ArrayList<Data> Data;
    private DataAdapter dataAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    String message;

    String sData;
    DatabaseHelper mDatabaseHelper;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Data = new ArrayList<>();
        mDatabaseHelper = new DatabaseHelper(this);


        dataAdapter = new DataAdapter(Data);
        recyclerView.setAdapter(dataAdapter);

        dataAdapter.Clearmlist();

        dataAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, TextView sData) {
                String row_data = sData.getText().toString();
                ////////////// 리사이클러뷰를 통해 삭제하는 기능 추가중...
                Log.d(TAG, "onItemClick: You Clicked on " + sData.getText());
                //////////////
                AlertDialog.Builder ad = new AlertDialog.Builder(ListDataActivity.this);
                ad.setIcon(R.mipmap.ic_launcher_round);
                ad.setTitle("제목");
                ad.setMessage("삭제하시겠습니까?");

                final EditText et = new EditText(ListDataActivity.this);
                ad.setView(et);

                ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabaseHelper.deleteName(row_data);
                        dialog.dismiss();
                        dataAdapter.notifyDataSetChanged();
                    }
                });

                ad.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ad.show();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                populateListView();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dataAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the View");

        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            sData = data.getString(1);
            Data data1 = new Data(sData);
            Data.add(data1);
        }
    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
