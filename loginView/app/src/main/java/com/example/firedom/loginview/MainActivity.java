package com.example.firedom.loginview;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    EditText et1;
    EditText et2;
    String login = "123";
    String password = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 输入文本
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        Button btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (et1.getText().toString().equals(login)
                        && et2.getText().toString().equals(password)) {
                    Toast.makeText(MainActivity.this, "恭喜你登陆成功~！", 0).show();
                    Intent intent =  new Intent(MainActivity.this, Activity01.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "恭喜你手机炸了~！", 0).show();
                }
            }
        });

    }
}
