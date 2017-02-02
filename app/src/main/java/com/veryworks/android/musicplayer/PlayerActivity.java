package com.veryworks.android.musicplayer;

import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton btnRew, btnPlay, btnFf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btnRew = (ImageButton) findViewById(R.id.btnRew);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnFf = (ImageButton) findViewById(R.id.btnFf);

        btnRew.setOnClickListener(clickListener);
        btnPlay.setOnClickListener(clickListener);
        btnPlay.setOnClickListener(clickListener);

        // 0. 데이터 가져오기
        ArrayList<Music> datas = DataLoader.get(this);

        // 1. 뷰페이저 가져오기
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        // 2. 뷰페이저용 아답터 생성
        PlayerAdapter adapter = new PlayerAdapter(datas ,this);
        // 3. 뷰페이저 아답터 연결
        viewPager.setAdapter( adapter );
    }

    View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPlay:
                    play();
                    break;
                case R.id.btnRew:
                    prev();
                    break;
                case R.id.btnFf:
                    next();
                    break;
            }
        }
    };

    private void play() {

    }

    private void prev() {

    }

    private void next() {

    }
}
