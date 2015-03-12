package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
    }

    public void openBook(View view,int position)
    {
        Intent intent = new Intent(this,BookActivity.class);

        BookModel bookModel = (BookModel) listView.getAdapter().getItem(position);
        intent.putExtra("book",bookModel);
        startActivity(intent);
    }

}
