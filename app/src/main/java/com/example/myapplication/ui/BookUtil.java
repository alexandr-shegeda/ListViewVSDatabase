package com.example.myapplication.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import com.example.myapplication.R;
import com.example.myapplication.pojo.BookModel;
import com.example.myapplication.repository.BookDatabaseHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr_Shegeda on 23.02.2015.
 */
public class BookUtil
{
    public static List<BookModel> initList(BookDatabaseHelper sqlHelper,SQLiteDatabase sdb)
    {
        List<BookModel> list = new ArrayList<BookModel>();
        list.add(new BookModel(1,"Book 1", "author 1","Some book description","1890", R.drawable.book_1));
        list.add(new BookModel(2,"Book 2", "author 2","Some book description","1920",R.drawable.book_2));
        list.add(new BookModel(3,"Book 3", "author 3","Some book description","1932",R.drawable.book_3));
        list.add(new BookModel(4,"Book 4", "author 4","Some book description","1967",R.drawable.book_4));
        list.add(new BookModel(5,"Book 5", "author 5","Some book description","1951",R.drawable.book_5));
        list.add(new BookModel(6,"Book 6", "author 6","Some book description","2001",R.drawable.book_6));
        list.add(new BookModel(7,"Book 7", "author 7","Some book description","1987",R.drawable.book_7));
        list.add(new BookModel(8,"Book 8", "author 8","Some book description","1994",R.drawable.book_8));
        list.add(new BookModel(9,"Book 9", "author 9","Some book description","2003",R.drawable.book_9));
        list.add(new BookModel(10,"Book 10", "author 10","Some book description","2007",R.drawable.book_10));
        list.add(new BookModel(11,"Book 11", "author 11","Some book description","2013",R.drawable.book_11));
        list.add(new BookModel(12,"Book 12", "author 12","Some book description","2014",R.drawable.book_12));
        list.add(new BookModel(13,"Book 13", "author 13","Some book description","2002",R.drawable.book_13));
        list.add(new BookModel(14,"Book 14", "author 14","Some book description","1888",R.drawable.book_14));
        list.add(new BookModel(15,"Book 15", "author 15","Some book description","2001",R.drawable.book_15));

        return list;
    }

}
