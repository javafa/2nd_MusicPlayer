package com.veryworks.android.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton btnRew, btnPlay, btnFf;

    ArrayList<Music> datas;
    PlayerAdapter adapter;

    MediaPlayer player;
    SeekBar seekBar;
    boolean isPlaying = false;

    int position = 0; // 현재 음악 위치


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        btnRew = (ImageButton) findViewById(R.id.btnRew);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnFf = (ImageButton) findViewById(R.id.btnFf);

        btnRew.setOnClickListener(clickListener);
        btnPlay.setOnClickListener(clickListener);
        btnPlay.setOnClickListener(clickListener);

        // 0. 데이터 가져오기
        datas = DataLoader.get(this);

        // 1. 뷰페이저 가져오기
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        // 2. 뷰페이저용 아답터 생성
        adapter = new PlayerAdapter(datas ,this);
        // 3. 뷰페이저 아답터 연결
        viewPager.setAdapter( adapter );

        // 4. 특정 페이지 호출
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            position = bundle.getInt("position");

            // 실제 페이지 값 계산 처리...
            // 페이지 이동
            viewPager.setCurrentItem(position);
        }
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
        Uri musicUri = datas.get(position).uri;
        player = MediaPlayer.create(this, musicUri);

        player.setLooping(false); // 반복여부
        player.start();
        isPlaying = true;
    }

    private void prev() {

    }

    private void next() {

    }
}
