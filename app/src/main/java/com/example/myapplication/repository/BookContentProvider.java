package com.example.myapplication.repository;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.example.myapplication.repository.BookDatabaseHelper.Tables;

/**
 * Created by alexandr on 03.04.15.
 */
public class BookContentProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public static final String CONTENT_AUTHORITY = "com.example.myapplication.provider";
    private static final int BOOKS = 100;

    private static UriMatcher buildUriMatcher() {
        final String authority = CONTENT_AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(authority, "books", BOOKS);
        return matcher;
    }

    private BookDatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new BookDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

        final SelectionBuilder builder = buildSelection(uri);
        final Cursor result = builder.where(selection, selectionArgs)
                .query(db, projection, sortOrder);
        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            default:
                return "/vnd.com.example.myapplication.books";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case BOOKS:
                db.insertOrThrow(Tables.BOOKS, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return uri;

            default:
                throw new UnsupportedOperationException("Insert Uri not matched: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        final SelectionBuilder builder = buildSelection(uri);
        int result = builder.where(selection, selectionArgs).delete(db);
        getContext().getContentResolver().notifyChange(uri, null);

        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        SelectionBuilder builder = buildSelection(uri);
        int result = builder.where(selection, selectionArgs).update(db, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return result;
    }

    private SelectionBuilder buildSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case BOOKS:
                builder.table(Tables.BOOKS);
                break;

            default:
                throw new UnsupportedOperationException("Uri not matched for query builder: " + uri);
        }

        return builder;
    }
}
