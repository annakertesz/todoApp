package com.example.annakertesz.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annakertesz on 5/22/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoManager";
    private static final String TABLE_CARDS = "cards";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ISDONE = "is_done";

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("!!! It's my dbhandler constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("!!! I started to create db");
        String CREATE_CARD_TABLE = "CREATE TABLE " + TABLE_CARDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TITLE + " Text, " + KEY_ISDONE + " BOOL);";
        db.execSQL(CREATE_CARD_TABLE);
        System.out.println("!!! I created database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);

        // Create tables again
        onCreate(db);
    }

    // Adding new CARD
    public void addCard(ToDoCard card) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, card.getTitle());
        values.put(KEY_ISDONE, card.getIsDone());

        // Inserting Row
        db.insert(TABLE_CARDS, null, values);
        db.close();
    }

    // Getting single card
    public ToDoCard getCard(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CARDS, new String[]{KEY_TITLE, KEY_ISDONE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ToDoCard card = new ToDoCard(cursor.getString(0), Boolean.valueOf(cursor.getString(1)));
        // return contact
        return card;
    }

    // Getting All Cards
    public List<ToDoCard> getAllCards() {
        List<ToDoCard> cardList = new ArrayList<ToDoCard>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CARDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDoCard card = new ToDoCard();
                card.setTitle(cursor.getString(0));
                card.setIsDone(Boolean.valueOf(cursor.getString(2)));
                // Adding contact to list
                cardList.add(card);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cardList;
    }

    // Updating single card
    public int markdCardAsDone(ToDoCard card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, card.getTitle());
        values.put(KEY_ISDONE, card.getIsDone());

        // updating row
        return db.update(TABLE_CARDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(card.getId()) });
    }

    // Deleting single card
    public void deleteCard(ToDoCard card) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARDS, KEY_ID + " = ?",
                new String[] { String.valueOf(card.getId()) });
        db.close();
    }
}
