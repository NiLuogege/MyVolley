package com.example.well.myvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.well.myvolley.netcore.IJSonListener;
import com.example.well.myvolley.netcore.Volley;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private TextView mTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTV = (TextView) findViewById(R.id.tv);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBean requestBean = new RequestBean("104");
                String url ="http://www.ccholterserver.com:9093/public/get_miss_msg_count/user_id/104";
                Volley.sendRequest(requestBean, url, MissCountResponse.class, new IJSonListener<MissCountResponse>() {
                    @Override
//                    public void onSuccess(MissCountResponse missCountResponse) {
                    public void onSuccess(String missCountResponse) {
                        if (BuildConfig.DEBUG) Log.e("MainActivity", missCountResponse);
                        mTV.setText(missCountResponse);
                    }

                    @Override
                    public void onErro() {
                        if (BuildConfig.DEBUG) Log.e("qingqiushibai", "qingqiushibai");
                    }
                });
            }
        });
    }
}
