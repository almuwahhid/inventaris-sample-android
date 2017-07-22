package bnsp.com.oopbnsp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import bnsp.com.oopbnsp1.Adapter.AdapterUtama;
import bnsp.com.oopbnsp1.helper.Handler;

public class AddInventaris extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    EditText edt;
    Button btn;
    Handler handler;
    SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventaris);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        handler = new Handler(this);

        if(handler.auth(sp) == true){
            rg = (RadioGroup) findViewById(R.id.add_inventaris_radio);
            edt = (EditText) findViewById(R.id.add_inventaris_edt);
            btn = (Button) findViewById(R.id.add_inventaris_btn);

            rg.check(R.id.rb_1);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    if(edt.getText().toString().isEmpty()){
                        Toast.makeText(AddInventaris.this, "Isi dulu nama Inventarisnya", Toast.LENGTH_SHORT).show();
                    }else{
                        int id_kategori = 0;
                        switch (rb.getText().toString()){
                            case "Perkakas" :
                                id_kategori = 2;
                                break;
                            case "Berkas" :
                                id_kategori = 1;
                                break;
                        }
                        Log.d("asd", "onClick: "+id_kategori);
                        if(handler.addData(sp.getString("nim",null), id_kategori, edt.getText().toString())){
                            startActivity(new Intent(AddInventaris.this, MainActivity.class));
                        }
                    }
                }
            });
        }else {
            startActivity(new Intent(AddInventaris.this, Login.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}

