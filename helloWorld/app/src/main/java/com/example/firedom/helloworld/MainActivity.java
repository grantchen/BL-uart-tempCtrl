package com.example.firedom.helloworld;

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
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 定义了一个变量，用来显示文字到屏幕上变量
        // 实例化试图变量
        final TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setText("gd");

        Button btn = (Button)findViewById(R.id.button1);
        // 设置监听器
        // alt+? 自动提示快捷键
        // Java语言的每句话结尾以分号作为结束位
        btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tv.setText("gder");
            }
        });

    }
}
