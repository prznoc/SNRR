package com.example.snrr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "rental.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_IMAGE = "image";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_PRICE + " DOUBLE,"+
                COLUMN_IMAGE + " TEXT);";
        db.execSQL(createQuery);
        prepopulateDb();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addProduct(String name, String desc, double price, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_IMAGE, image);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllProducts() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getProducts(String searchPhrase) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_NAME +
                " LIKE '%" + searchPhrase + "%'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String name, String desc, double price, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_IMAGE, image);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    private void prepopulateDb(){
        this.addProduct("Aparat Nikon", "Nikon D3100 lustrzanka z obiektywem, pokrowcem, ładowarką do baterii, kartą pamięci SanDisc 8 GB, przejściówką na karty pamięci i kablem USD. Aparat w stanie idealnym bez rys na obiektywie.", 120.0, "https://ireland.apollo.olxcdn.com/v1/files/3bpo0ugbozvp3-PL/image;s=1000x700");
        this.addProduct("Rower Kross", "Rower marki Kross w świetnym stanie. Koła 28 cali.", 52.0, "https://ireland.apollo.olxcdn.com/v1/files/04vecewd94g3-PL/image;s=644x461");
        this.addProduct("Statyw fotograficzny", "Statyw Digipod", 15.0, "https://ireland.apollo.olxcdn.com/v1/files/lmgr7ljw6p15-PL/image;s=644x461");
        this.addProduct("Gitara akustyczna", "Gitara elektroakustyczna IBANEZ AEWC32 MOD", 86.50, "https://ireland.apollo.olxcdn.com/v1/files/xaojzmv6eip9-PL/image;s=1000x700");
        this.addProduct("Rower Giant Revel", "Rower marki Giant Revel 2, rama S, stan bardzo dobry.", 37.0, "https://ireland.apollo.olxcdn.com/v1/files/h6t4azxv9m032-PL/image;s=1000x700");
    }

}