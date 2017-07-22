package bnsp.com.oopbnsp1.helper;

import android.content.SharedPreferences;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import bnsp.com.oopbnsp1.kelas.Inventaris;

/**
 * Created by gueone on 5/5/2017.
 */

public class Handler {
    Database db;
    SQLiteDatabase database;
    Cursor csr;

    public Handler(Context ctx) {
        this.db = new Database(ctx);
    }

    public Handler() {
    }

    public boolean auth(SharedPreferences s){
        if(s.getString("nim", null) != null){
           return true;
        }else{
            return false;
        }
    }
    public String check_user(String nim){
        String asd = "";
        try {
            database = db.getWritableDatabase();
            String query = "SELECT * FROM user WHERE nim = '"+nim+"'";
            csr = database.rawQuery(query, null);
                if(csr != null && !csr.isClosed()){
                    while (csr.moveToNext()){
                        asd = csr.getString(csr.getColumnIndex("nim"));
                    }
                }
            return asd;
        }catch (Exception e){
            Log.e("error", "check_user: "+e );
            return "";
        }
    }

    public ArrayList getAllData(ArrayList e){
        try {
            database = db.getWritableDatabase();
            Log.d("asd", "getAllData: ");
            String query = "SELECT * FROM inventaris";
            csr = database.rawQuery(query, null);
            Log.d("asd", "getAllData: "+csr.getCount());
            while (csr.moveToNext()){
                Inventaris inv = new Inventaris();
                Log.d("asd", "getAllData: "+csr.getString(csr.getColumnIndex("nim_inv")));
                inv.setId(csr.getInt(csr.getColumnIndex("id_inv")));
                inv.setNama_inv(csr.getString(csr.getColumnIndex("nama_inv")));
                inv.setId_kategori(csr.getInt(csr.getColumnIndex("id_kategori")));
                inv.setNim_inv(csr.getString(csr.getColumnIndex("nim_inv")));
                e.add(inv);
            }
        }catch (Exception a){
            Log.e("err", "getAllData: "+a);
        }
        return e;
    }

    public boolean deleteData(int i){
        try {
            database = db.getWritableDatabase();
            String query = "DELETE FROM inventaris WHERE id_inv = "+i;
            database.execSQL(query);
            return true;
        }catch (Exception e){
            Log.e("error", "deleteData: "+e );
            return false;
        }
    }

    public boolean addData(String i, int a, String b){
        try {
            database = db.getWritableDatabase();
            String query = "INSERT INTO inventaris ('nim_inv', 'id_kategori', 'nama_inv') VALUES ('"+i+"', '"+a+"', '"+b+"')";
            database.execSQL(query);
            return true;
        }catch (Exception e){
            Log.e("error", "addData: "+e );
            return false;
        }
    }
}
