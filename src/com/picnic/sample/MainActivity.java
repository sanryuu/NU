package com.picnic.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnApiListener{

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String[] members = { "mhidaka", "rongon_xp", "kacchi0516", "kobashinG",
                "seit", "kei_i_t", "furusin_oriver" };
         
        lv = (ListView) findViewById(R.id.nearFriend);
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, members);
 
        lv.setAdapter(adapter);
        
        
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


        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText(result);

    }

}
