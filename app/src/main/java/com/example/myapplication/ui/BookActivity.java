package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.pojo.BookModel;

/**
 * Created by Alexandr Shegeda on 22.02.2015.
 */
public class BookActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        BookModel book = (BookModel) getIntent().getParcelableExtra("book");

        TextView bookTitle  = (TextView)findViewById(R.id.text_title);
        TextView bookAuthor  = (TextView)findViewById(R.id.text_author);
        TextView bookDesc   = (TextView)findViewById(R.id.text_description);
        TextView bookYear   = (TextView)findViewById(R.id.text_year);
        ImageView bookCover = (ImageView)findViewById(R.id.imageView);

        bookTitle.setText( book.getBookTitle() );
        bookAuthor.setText( book.getBookAuthor() );
        bookDesc.setText( book.getDescription() );
        bookYear. setText(book.getYear());
        bookCover.setBackground(getApplicationContext().getResources().getDrawable(book.getImage()));
    }
}
