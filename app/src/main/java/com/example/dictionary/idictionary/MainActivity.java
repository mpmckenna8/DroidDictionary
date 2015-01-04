package com.example.dictionary.idictionary;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.provider.UserDictionary.Words;


public class MainActivity extends ActionBarActivity {

    // For the SimpleCursorAdapter to match the UserDictionary columns to layout items

    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            Words.WORD,
            Words.FREQUENCY
    };

    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[]{
         android.R.id.text1,
            android.R.id.text2
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView dictText = (ListView) findViewById(R.id.dictionary_list_view);

        // need to get the content resolver next
        ContentResolver resolver = getContentResolver();

        //getting a cursor containing all the words in a table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI,null,null,null,null);

        int curCount = cursor.getCount();


        String currCount = String.valueOf(curCount);
        Log.v("curLen", currCount);

        try{

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.two_line_list_item,
                    cursor,
                    //COLUMNS_TO_BE_BOUND
                    COLUMNS_TO_BE_BOUND,
                    LAYOUT_ITEMS_TO_FILL,
                    0);

            dictText.setAdapter(adapter);

        }
        finally {

           //cursor.close();
        }
    }

    @Override
    protected void onDestroy(){

        Log.v("destorying", "closing the curosr");
       // cursor.close();

        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
