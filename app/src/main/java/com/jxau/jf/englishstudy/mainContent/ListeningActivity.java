package com.jxau.jf.englishstudy.mainContent;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jxau.jf.englishstudy.R;

import java.io.File;
import java.io.IOException;

public class ListeningActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private TextView music_time;
    private Button music_start;
    private boolean playFlag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        music_start = (Button)findViewById(R.id.music_start);
        music_time = (TextView) findViewById(R.id.music_time);
        if (ContextCompat.checkSelfPermission(ListeningActivity.this, Manifest. permission.
                WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ListeningActivity.this, new String[]{
                    Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            initMediaPlayer(); // 初始化MediaPlayer
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "girl.mp3");
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playAndPause(View view){
        if (playFlag){
            mediaPlayer.start();
            music_start.setBackgroundResource(R.mipmap.stop);
            playFlag=false;
        }else {
            mediaPlayer.pause();
            music_start.setBackgroundResource(R.mipmap.start);
            playFlag=true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
