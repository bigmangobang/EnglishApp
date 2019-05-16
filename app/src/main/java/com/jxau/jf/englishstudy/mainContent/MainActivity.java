package com.jxau.jf.englishstudy.mainContent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.jxau.jf.englishstudy.R;

public class MainActivity extends Activity implements View.OnClickListener {
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
        init_view();
    }

    private void init_view() {
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

}
