package com.jxau.jf.englishstudy.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private PlayMusicBinder playMusicBinder;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public class PlayMusicBinder extends Binder {

        public void start() {
        }
    }
        class ProAsyncTask extends AsyncTask<Void, Integer, Integer> {


        @Override
        protected Integer doInBackground(Void... parms) {
            //设置while（true）以达到循环不断的目的
            while(true){
                //线程沉睡500毫秒
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int pro = mediaPlayer.getCurrentPosition();
                //获得MEDIAPLAYER当前播放进度如果（位置
                Intent mIntent = new Intent("com.example.musicplayer.update_seekbar");
                mIntent.putExtra("位置",pro);
                sendBroadcast(mIntent); //发送广播
            }
        }
    }
}
