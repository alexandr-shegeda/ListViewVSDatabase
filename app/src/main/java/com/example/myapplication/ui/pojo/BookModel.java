package com.example.myapplication.ui.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Александр on 22.02.2015.
 */
public class BookModel implements Parcelable {

    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private String description;
    private String year;
    private int image;

    public BookModel(int bookId, String bookTitle, String bookAuthor, String description, String year, int image) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.description = description;
        this.year = year;
        this.image = image;
    }

    public BookModel(Parcel in) {
        String[] data = new String[6];
        in.readStringArray(data);
        bookId = Integer.valueOf(data[0]);
        bookTitle = data[1];
        bookAuthor = data[2];
        description = data[3];
        year = data[4];
        image = Integer.parseInt(data[5]);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{"" + bookId, bookTitle,bookAuthor, description, year, ""+ image});
    }

    public static final Parcelable.Creator<BookModel> CREATOR = new Parcelable.Creator<BookModel>() {

        @Override
        public BookModel createFromParcel(Parcel source) {
            return new BookModel(source);
        }

        @Override
        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };
}
