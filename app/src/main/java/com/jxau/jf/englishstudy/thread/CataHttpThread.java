package com.jxau.jf.englishstudy.thread;

import com.jxau.jf.englishstudy.utils.HttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CataHttpThread extends Thread{
    private String result;
    private String path;
    public CataHttpThread(String path){
        this.path = path;
    }
    public void run() {
        //http请求
        try {
            URL url = new URL(HttpUtils.URL + path);
            HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5*1000);
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String temp;
            while((temp = bufferedReader.readLine()) != null){
                stringBuffer.append(temp);
            }
            setResult(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
