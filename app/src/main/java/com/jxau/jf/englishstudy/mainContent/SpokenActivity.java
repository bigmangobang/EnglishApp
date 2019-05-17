package com.jxau.jf.englishstudy.mainContent;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jxau.jf.englishstudy.R;


public class SpokenActivity extends AppCompatActivity {
    private TextView title;
    private Button playVoice;
    private Boolean isPause = true;//是否为播放状态，true为暂停状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoken);
        playVoice = findViewById(R.id.spo_voice_button);
        title = findViewById(R.id.spo_title);
        title.setText(getIntent().getStringExtra("title"));
        //直接创建，不需要设置setDataSource
        final MediaPlayer mMediaPlayer = MediaPlayer.create(SpokenActivity.this, R.raw.girl);
        playVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPause) {
                    mMediaPlayer.start();
                    isPause = false;
                } else {
                    mMediaPlayer.pause();
                    isPause = true;
                }
                //监听音频播放完毕事件
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
