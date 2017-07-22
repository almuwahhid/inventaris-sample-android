package bnsp.com.oopbnsp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bnsp.com.oopbnsp1.helper.Database;
import bnsp.com.oopbnsp1.helper.Handler;

public class Login extends AppCompatActivity {
    Button btn;
    EditText edt;
    Handler handler;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handler = new Handler(this);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        if(handler.auth(sp)==true){
            startActivity(new Intent(Login.this, MainActivity.class));
        }

        btn = (Button) findViewById(R.id.login_btn);
        edt = (EditText) findViewById(R.id.login_edt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifikasi(edt.getText().toString());
            }
        });
    }

    void verifikasi(String username){
        if(handler.check_user(username) != ""){
            Log.d("asd", "verifikasi: "+handler.check_user(username));
            SharedPreferences pref = getApplicationContext().getSharedPreferences("user", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("nim", username);
            editor.commit();
            startActivity(new Intent(Login.this, MainActivity.class));
        }else{
            Toast.makeText(this, "Maaf NIM tidak terdaftar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(handler.auth(sp)==true){
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }
}
