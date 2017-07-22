
package bnsp.com.oopbnsp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import bnsp.com.oopbnsp1.Adapter.AdapterUtama;
import bnsp.com.oopbnsp1.helper.Database;
import bnsp.com.oopbnsp1.helper.Handler;
import bnsp.com.oopbnsp1.kelas.Inventaris;


public class MainActivity extends AppCompatActivity implements AdapterUtama.clickAdapter{
    SharedPreferences sp;
    ArrayList<Inventaris> all_data;
    Database db;
    AdapterUtama adapter;
    Handler handler;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_icon){
            startActivity(new Intent(MainActivity.this, AddInventaris.class));
        }else if(item.getItemId() == R.id.logout_icon){
            SharedPreferences.Editor editor = this.sp.edit();
            editor.putString("user", null);
            editor.putString("nim", null);
            editor.commit();
            startActivity(new Intent(MainActivity.this, Login.class));
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(this);


        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        if(handler.auth(sp)==true){
            Log.e("asd", "onCreate: "+sp.getString("nim", null) );
            all_data = new ArrayList();


            RecyclerView rv = (RecyclerView) findViewById(R.id.rc);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
            rv.setLayoutManager(lm);

            adapter = new AdapterUtama(handler.getAllData(all_data), this, sp.getString("nim", null), this);
            rv.setAdapter(adapter);
        }else{
            Log.d("hei", "onCreate: ");
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    @Override
    public void klikItem(int position) {
        if(adapter != null){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            Log.d("parah", "klikItem: ");
        }

    }
}
