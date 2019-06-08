package com.jxau.jf.englishstudy.mainContent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jxau.jf.englishstudy.R;
import com.jxau.jf.englishstudy.thread.TxtHttpThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ReadActivity extends Activity {
    private TextView readTitle, readContent;
    private ScrollView readScroll;
    TxtHttpThread txtHttpThread = new TxtHttpThread("3.txt");

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        readContent = findViewById(R.id.read_content);
        readScroll = findViewById(R.id.read_scroll);
        readTitle = findViewById(R.id.read_title);
        readTitle.setText(getIntent().getStringExtra("title"));
        show_txt();
        readScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
    }

    void show_txt() {

        txtHttpThread.start();
        try {
            txtHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String conte = txtHttpThread.getTxtContent();
//        读取asset下的TXT文件
//        String conte = readAssetsTxt(this,"app");
        readContent.setText(conte);

    }

    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTxt(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName + ".txt");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
