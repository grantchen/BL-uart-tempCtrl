package com.example.firedom.listview;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    ListView listView; // 表格视图
    ArrayAdapter<String> adapter; // 给表格视图添加数据的工具
    String[] str = new String[10]; // 初始化数据存放数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 10; i++) { // 添加数据到数组中
            str[i] = "张" + i;
        }
        listView = (ListView) findViewById(R.id.listView); // 初始化视图

        // 初始化数据添加工具
        // 显示每一行里的布局数据等东西
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, str);
        listView.setAdapter(adapter); // 添加工具给表格视图
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 设置单击事件
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, i + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }
}