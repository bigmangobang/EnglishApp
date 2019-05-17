package com.jxau.jf.englishstudy.mainContent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jxau.jf.englishstudy.R;
import com.jxau.jf.englishstudy.coverAdapter.SpokenAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private ListView listView1, listView2, listView3, listView4, listView5;
    private ImageButton button1, button2, button3, button4, button5;
    private RelativeLayout layout1, layout2, layout3, layout4, layout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.spoken_layout);
        layout2 = findViewById(R.id.listen_layout);
        layout3 = findViewById(R.id.word_layout);
        layout4 = findViewById(R.id.read_layout);
        layout5 = findViewById(R.id.other_layout);
        button1 = findViewById(R.id.spoken_button);
        button2 = findViewById(R.id.listen_button);
        button3 = findViewById(R.id.word_button);
        button4 = findViewById(R.id.read_button);
        button5 = findViewById(R.id.other_button);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        listView1 = findViewById(R.id.list_item_spo);
        listView4 = findViewById(R.id.list_item_read);
        init_view();
    }

    private void init_view() {
        //显示口语目录条目
        final List<String> spoItemList = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            spoItemList.add("这是第" + i + "本口语书");
        }
        //设置ListView的数据适配器
        listView1.setAdapter(new SpokenAdapter(this, spoItemList));
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String spoTitle = spoItemList.get(position);
                Intent intent = new Intent(MainActivity.this, SpokenActivity.class);
                intent.putExtra("title", spoTitle);
                startActivity(intent);
            }
        });
        show_spoken();
        //显示阅读目录条目
        final List<String> readItemList = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            readItemList.add("这是第" + i + "本阅读书");
        }
        //设置ListView的数据适配器
        listView4.setAdapter(new SpokenAdapter(this, readItemList));
        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String readTitle = readItemList.get(position);
                Intent intentToRead = new Intent(MainActivity.this, ReadActivity.class);
                intentToRead.putExtra("title", readTitle);
                startActivity(intentToRead);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spoken_button:
                show_spoken();
                break;
            case R.id.listen_button:
                show_listen();
                break;
            case R.id.word_button:
                show_word();
                break;
            case R.id.read_button:
                show_read();
                break;
            case R.id.other_button:
                show_other();
                break;
        }
    }

    //口语显示首页
    private void show_spoken() {
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
        layout5.setVisibility(View.GONE);
    }

    private void show_listen() {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
        layout5.setVisibility(View.GONE);
    }

    private void show_word() {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.VISIBLE);
        layout4.setVisibility(View.GONE);
        layout5.setVisibility(View.GONE);
    }

    private void show_read() {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.VISIBLE);
        layout5.setVisibility(View.GONE);
    }

    private void show_other() {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
        layout5.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        long exitTime = 0;
        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
