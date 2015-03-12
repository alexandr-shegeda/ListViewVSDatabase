package com.example.myapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.myapplication.pojo.BookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Shegeda on 09.03.2015.
 */
public class BookDatabaseHelper extends SQLiteOpenHelper implements BaseColumns
{

    private static final String DATABASE_NAME = "book_database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "book_table";
    public static final String UID = "BookData_id";
    public static final String BOOK_TITLE = "bookTitle";
    public static final String BOOK_AUTHOR = "bookAuthor";
    public static final String BOOK_DESC = "description";
    public static final String BOOK_YEAR = "year";
    public static final String IMAGE_PATH = "imagePath";


    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BOOK_TITLE + " TEXT, "+ BOOK_AUTHOR + " TEXT, "+BOOK_DESC + " TEXT, "
            + BOOK_YEAR + " INTEGER, "+ IMAGE_PATH + " INTEGER);";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    public BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
                + " до версии " + newVersion + ", которое удалит все старые данные");
        // Удаляем предыдущую таблицу при апгрейде
        db.execSQL(SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(db);
    }

    public void addBook(BookModel book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(BOOK_TITLE, book.getBookTitle());
        cv.put(BOOK_AUTHOR,book.getBookAuthor());
        cv.put(BOOK_DESC,  book.getDescription());
        cv.put(BOOK_YEAR,  book.getYear());
        cv.put(IMAGE_PATH, book.getImage());

        db.insert(TABLE_NAME, UID, cv);
        db.close();
    }

    public BookModel getBookById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { BOOK_TITLE,
                        BOOK_AUTHOR, BOOK_DESC, BOOK_YEAR, IMAGE_PATH}, UID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        BookModel book = new BookModel(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4), Integer.parseInt(cursor.getString(5)));

        return book;
    }

    public List<BookModel> getAllContacts() {
        List<BookModel> contactList = new ArrayList<BookModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookModel book = new BookModel(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2),cursor.getString(3),
                        cursor.getString(4), Integer.parseInt(cursor.getString(5)));
                contactList.add(book);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateBook(BookModel book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(BOOK_TITLE,  book.getBookTitle());
        cv.put(BOOK_AUTHOR, book.getBookAuthor());
        cv.put(BOOK_DESC,   book.getDescription());
        cv.put(BOOK_YEAR,   book.getYear());
        cv.put(IMAGE_PATH,  book.getImage());

        return db.update(TABLE_NAME, cv, UID + " = ?",
                new String[] { String.valueOf(book.getBookId()) });
    }

    public void deleteBook(BookModel book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, UID + " = ?", new String[] { String.valueOf(book.getBookId()) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
