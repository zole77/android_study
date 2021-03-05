package org.techtown.xtoj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    JSONObject jsonObject = null;

    String xml = "<?xml version=\"1.0\"?>\n" +
            "   <book id=\"bk101\">\n" +
            "      <author>Gambardella, Matthew</author>\n" +
            "      <title>XML Developer's Guide</title>\n" +
            "      <genre>Computer</genre>\n" +
            "      <price>44.95</price>\n" +
            "      <publish_date>2000-10-01</publish_date>\n" +
            "      <description>An in-depth look at creating applications \n" +
            "      with XML.</description>\n" +
            "   </book>\n" +
            "   <book id=\"bk102\">\n" +
            "      <author>Ralls, Kim</author>\n" +
            "      <title>Midnight Rain</title>\n" +
            "      <genre>Fantasy</genre>\n" +
            "      <price>5.95</price>\n" +
            "      <publish_date>2000-12-16</publish_date>\n" +
            "      <description>A former architect battles corporate zombies, \n" +
            "      an evil sorceress, and her own childhood to become queen \n" +
            "      of the world.</description>\n" +
            "   </book>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textman);
        button = findViewById(R.id.Buttonman);

        textView.setText(xml);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    jsonObject = XML.toJSONObject(xml);

                    textView.setText(jsonObject.toString(3));
                } catch(JSONException e){
                    e.printStackTrace();
                }

            }
        });


    }
}