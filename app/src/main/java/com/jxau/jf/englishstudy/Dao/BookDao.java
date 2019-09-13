package com.jxau.jf.englishstudy.Dao;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.jxau.jf.englishstudy.thread.TxtHttpThread;
import com.jxau.jf.englishstudy.utils.HttpUtils;
import com.jxau.jf.englishstudy.vo.Books;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BookDao {

    private TxtHttpThread txtHttpThread;
    private String num = "00";
    private Context context;
    private MediaPlayer mMediaPlayer;

    public BookDao(@NonNull Context context) {
        this.context = context;
    }

    public ArrayList<Books> findAll(String BooksName) {
        ArrayList<Books> list = new ArrayList<>();
        String a =  URLEncoder.encode(BooksName);
        String  bb = a.replace("+","%20");
        for (int i = 0; i < 10; i++) {
            int no = Integer.valueOf(num);
            if (no < 10) {
                num = "0" + (no + 1);
            } else {
                num = String.valueOf(no + 1);
            }
//            String path = BooksName + "/" + num;

//            if (detection(HttpUtils.URL + HttpUtils.READ + bb  + "/" + num + ".txt")) {
              if(getTxt("牛津书虫二级 莫尔格街凶杀案" + "/" + num + ".txt").equals("0")){
                return list;
            }else{
            Books books = new Books();//将每一行数据存放在用户对象中
//                books.setTxt("这是第"+ i + "本书");
            books.setTxt(getTxt("牛津书虫二级 莫尔格街凶杀案" + "/" + num + ".txt"));
            Uri uri = Uri.parse(HttpUtils.URL + HttpUtils.READ + "牛津书虫二级 莫尔格街凶杀案" + "/" + num + ".mp3");
            mMediaPlayer = MediaPlayer.create(context, uri);
            books.setMp3(mMediaPlayer);
            list.add(books);//将用户对象存储到集合
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String num = "00";
        ArrayList<Books> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i < 10) {
                num = "0" + i;
            } else {
                num = String.valueOf(i + num);
            }
            String path = "牛津书虫二级 莫尔格街凶杀案" + "/" + num;
            String aaa = URLEncoder.encode("牛津书虫二级 莫尔格街凶杀案");
            String b = aaa.replace("+", "%20");
        }
    }

    private static Boolean detection(String url) {
        URL pathUrl = null;
        try {
            pathUrl = new URL(url);
            HttpURLConnection urlcon = (HttpURLConnection) pathUrl.openConnection();
            if (urlcon.getResponseCode() >= 400) {
                return false;
            } else {
                return true;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public String getTxt(String url) {
        txtHttpThread = new TxtHttpThread(url);
        txtHttpThread.start();
        try {
            txtHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String conte = txtHttpThread.getTxtContent();
//        读取asset下的TXT文件
//        String conte = readAssetsTxt(this,"app");
        if (conte == null) {
            return "0";
        }
        return conte;
    }

    private MediaPlayer getMp3(String url) {
        MediaPlayer mMediaPlayer = new MediaPlayer();
        Uri uri = Uri.parse(url + ".mp3");
        mMediaPlayer = MediaPlayer.create(context, uri);
        return mMediaPlayer;
    }


    public static boolean isNetFileAvailable(String strUrl) {
        InputStream netFileInputStream = null;
        try {
            URLEncoder.encode(strUrl);
            URL url = new URL(strUrl);
            URLConnection urlConn = url.openConnection();
            netFileInputStream = urlConn.getInputStream();
            if (null != netFileInputStream) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (netFileInputStream != null)
                    netFileInputStream.close();
            } catch (IOException e) {
            }
        }
    }
}
