package com.example.firedom.androidbluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


/**
 * Created by firedom on 6/26/15.
 * 1.初始化界面控件
 * 2.建立服务器端
 * 3.建立读取线程
 * 4.获取发送的信息
 */
public class ChatActivity extends Activity {

    ListView listView;

    ArrayList<String> list;

    ArrayAdapter<String> adapter;

    BluetoothServerSocket mysocket;

    BluetoothSocket socket;

    BluetoothAdapter myAdapter = BluetoothAdapter.getDefaultAdapter();

    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);


        list = new ArrayList<String>();

        listView = (ListView) findViewById(R.id.listView1);

        btn = (Button) findViewById(R.id.button1);
        et = (EditText) findViewById(R.id.editText1);

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String str = et.getText().toString();
                sendMessage(str);

            }
        });

        adapter = new ArrayAdapter<String>(ChatActivity.this,
                android.R.layout.simple_expandable_list_item_1, list);
        listView.setAdapter(adapter);

        ServerThread serverThread = new ServerThread();
        serverThread.start();

    }

    // 利用此方法，调用蓝牙协议中的发送信息的方法
    public void sendMessage(String msg) {

        if (socket != null) {
            try {
                OutputStream os = socket.getOutputStream();

                os.write(msg.getBytes());
                list.add(msg);
                adapter.notifyDataSetChanged();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    // 在安卓中无法更新主线程显示的内容
    // 使用Handler可以在子线程与主线程中交换数据
    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            list.add(msg.obj.toString());
            // 利用adapter刷新数据的方法。可以使数据改变的时候。改变ListVIew中显示的内容
            adapter.notifyDataSetChanged();
            return false;
        }
    });

    private class ServerThread extends Thread {
        @Override
        public void run() {

            try {
                // 创建一个以蓝牙为基础的服务器，服务器名称，UUID（蓝牙固定的一种ID格式，必须有）
                mysocket = myAdapter
                        .listenUsingRfcommWithServiceRecord(
                                "btspp",
                                UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));

                socket = mysocket.accept();
                readThread read = new readThread();
                read.start();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private class readThread extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                InputStream input = socket.getInputStream();

                byte[] buffer = new byte[1024];

                int bytes;
                while (true) {
                    if ((bytes = input.read(buffer)) > 0) {
                        byte[] buf_data = new byte[bytes];
                        for (int i = 0; i < bytes; i++) {
                            buf_data[i] = buffer[i];
                        }
                        String s = new String(buf_data);
                        Message msg = new Message();
                        msg.obj = s;
                        handler.sendMessage(msg);
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
