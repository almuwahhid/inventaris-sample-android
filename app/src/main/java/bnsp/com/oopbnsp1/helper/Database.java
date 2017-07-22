
package bnsp.com.oopbnsp1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import bnsp.com.oopbnsp1.kelas.Inventaris;

/**
 * Created by gueone on 5/4/2017.
 */

public class Database extends SQLiteOpenHelper{

    public Database(Context context) {
        super(context, "db_inv", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("fuck", "onCreate: ");

        String sql1 = "CREATE TABLE IF NOT EXISTS user(nim TEXT PRIMARY KEY, nama_user TEXT)";
        String sql2 = "CREATE TABLE IF NOT EXISTS inventaris(id_inv INTEGER PRIMARY KEY AUTOINCREMENT, nama_inv TEXT, id_kategori INTEGER, nim_inv TEXT)";
        db.execSQL(sql1);
        db.execSQL(sql2);

        ContentValues val = new ContentValues();
        val.put("nim", "135410025");
        val.put("nama_user", "Al Muwahhid");
        db.insert("user", "nama_user", val);


        val.put("nim", "135410026");
        val.put("nama_user", "Joko Bejo");
        db.insert("user", "nama_user", val);

        ContentValues val2 = new ContentValues();
        val2.put("nama_inv", "Sapu Ijuk");
        val2.put("id_kategori", "2");
        val2.put("nim_inv", "135410025");
        db.insert("inventaris", "nim", val2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS inventaris");
        onCreate(db);
    }
}
