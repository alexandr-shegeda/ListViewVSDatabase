package com.example.myapplication.repository;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.ui.pojo.BookModel;
import com.example.myapplication.repository.BookDatabaseHelper;


public class BookCreatorActivity extends ActionBarActivity {

    private BookDatabaseHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_creator);

        // Инициализируем наш класс-обёртку
        sqlHelper = new BookDatabaseHelper(this);
        // База нам нужна для записи и чтения
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_creator, menu);
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

    public void addNewBookToDB(View view)
    {
        EditText book_title = (EditText) findViewById(R.id.txt_book_title);
        EditText book_author = (EditText) findViewById(R.id.txt_book_author);
        EditText book_desc = (EditText) findViewById(R.id.txt_book_desc);
        EditText book_year = (EditText) findViewById(R.id.txt_book_year);

        sqlHelper.addBook(new BookModel(1,
                book_title.getText().toString(),
                book_author.getText().toString(),
                book_desc.getText().toString(),
                book_year.getText().toString(),
                R.drawable.book_1));

        book_title.setText("");
        book_author.setText("");
        book_desc.setText("");
        book_year.setText("");
    }
}
