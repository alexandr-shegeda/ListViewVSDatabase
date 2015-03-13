package com.example.myapplication;

import android.app.SearchManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.myapplication.repository.BookDatabaseHelper;
import com.example.myapplication.ui.BookActivity;
import com.example.myapplication.ui.BookAdapter;
import com.example.myapplication.ui.pojo.BookModel;
import com.example.myapplication.ui.BookUtil;

public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private BookDatabaseHelper sqlHelper;
    private SQLiteDatabase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openBook(view,position);
            }
        });

        sqlHelper = new BookDatabaseHelper(this);

        BaseAdapter adapter = new BookAdapter(this, BookUtil.initList(sqlHelper));
        listView.setAdapter(adapter);
        BookUtil db = new BookUtil();

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getBookByYear(query);
        }
    }

    private ListView getBookByYear(String query) {
        return new ListView(this);
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
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openBook(View view,int position)
    {
        Intent intent = new Intent(this,BookActivity.class);

        BookModel bookModel = (BookModel) listView.getAdapter().getItem(position);
        intent.putExtra("book",bookModel);
        startActivity(intent);
    }

}
