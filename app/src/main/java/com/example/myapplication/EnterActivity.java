package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.repository.BookDatabaseHelper;


public class EnterActivity extends ActionBarActivity {

    private BookDatabaseHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        sqlHelper = new BookDatabaseHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter, menu);
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

    public void goToBookShelf(View view)
    {
        Intent intent = new Intent(getBaseContext(),MainActivity.class);
        this.startActivity(intent);
    }

    public void addNewBook(View view)
    {
        Intent intent = new Intent(getBaseContext(),BookCreatorActivity.class);
        this.startActivity(intent);
    }

    public void dropTable(View view)
    {
        sqlHelper.deleteAll();
    }
}
