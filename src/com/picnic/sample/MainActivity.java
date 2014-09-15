package com.picnic.sample;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnApiListener{

    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // デフォルトのリストに表示する人を追加
        String[] members = { "mhidaka", "rongon_xp", "kacchi0516", "kobashinG",
                "seit", "kei_i_t", "furusin_oriver" };
         
        mLv = (ListView) findViewById(R.id.nearFriend);
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, members);
 
        mLv.setAdapter(adapter);
        
        searchAsyncTask task = new searchAsyncTask(this);
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /* (non-Javadoc)
     * @see com.picnic.sample.OnApiListener#onSuccess(java.lang.String)
     */
    @Override
    public void onSuccess(String result) {

//        Log.d("JSONampleActivity", result);
        
        // http://techbooster.jpn.org/andriod/application/1645/
        // Button button = (Button) findViewById(R.id.button1);
        // button.setText(result);
        
        List<String> list = new ArrayList<String>();

        try {
            JSONArray rootArray;
            rootArray = new JSONArray(result);

            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject jsonObject = rootArray.getJSONObject(i);
                list.add(jsonObject.getString("uid"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, list);
        mLv.setAdapter(adapter);

    }

}
