package com.example.dictionary.idictionary;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.provider.UserDictionary.Words;


public class MainActivity extends ActionBarActivity {


    public int bleppw = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView dictText = (TextView) findViewById(R.id.dictionary_list_view);

        // need to get the content resolver next
        ContentResolver resolver = getContentResolver();

        //getting a cursor containing all the words in a table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI,null,null,null,null);

        int curCount = cursor.getCount();


        String currCount = String.valueOf(curCount);
        Log.v("curLen", currCount);

        try{

            dictText.setText("The user dictionary contains " + currCount + " words. \n");

            dictText.append("Columns: " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY + " - " + UserDictionary.Words.WORD);

            // getting index of columns using Words con stants, which contain headers of columns
            int idColumn = cursor.getColumnIndex(Words._ID);
            int frequencyColumn = cursor.getColumnIndex(Words.FREQUENCY);
            int wordColumn = cursor.getColumnIndex(Words.WORD);

            // go through all the cursor
            while(cursor.moveToNext()){
                // use the index to extract the string value of the word
                int id = cursor.getInt(idColumn);
                int frequency = cursor.getInt(frequencyColumn);
                String word = cursor.getString(wordColumn);

                dictText.append("\n" + id + " - " + frequency + " - " + word);
            }
        }
        finally {

            cursor.close();
        }
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
