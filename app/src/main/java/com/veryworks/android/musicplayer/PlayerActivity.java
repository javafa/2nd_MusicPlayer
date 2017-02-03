package com.veryworks.android.musicplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class PlayerActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton btnRew, btnPlay, btnFf;

    ArrayList<Music> datas;
    PlayerAdapter adapter;

    MediaPlayer player;
    SeekBar seekBar;
    TextView txtDuration,txtCurrent;

    // 플레이어 상태 플래그
    private static final int PLAY = 0;
    private static final int PAUSE = 1;
    private static final int STOP = 2;

    // 현재 플레이어 상태
    private static int playStatus = STOP;

    int position = 0; // 현재 음악 위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playStatus = STOP;

        // 볼륨 조절 버튼으로 미디어 음량만 조절하기 위한 설정
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        txtDuration = (TextView) findViewById(R.id.txtDuration);
        txtCurrent = (TextView) findViewById(R.id.txtCurrent);

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
        // 플레이중이 아니면 음악 실행
        switch(playStatus) {
            case STOP:
                Uri musicUri = datas.get(position).uri;
                // 플레이어에 음원 세팅
                player = MediaPlayer.create(this, musicUri);
                player.setLooping(false); // 반복여부

                // seekBar 길이
                seekBar.setMax(player.getDuration());
                txtDuration.setText(player.getDuration()/1000 + " Sec.");
                player.start();

                playStatus = PLAY;
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);

                // sub thread 를 생성해서 mediaplayer 의 현재 포지션 값으로 seekbar 를 변경해준다. 매 1초마다
                // sub thread 에서 동작할 로직 정의
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        while (playStatus < STOP) {
                            if(player != null) {
                                // 이 부분은 메인쓰레드에서 동작하도록 Runnable 객체를 메인쓰레드에 던져준다
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        seekBar.setProgress(player.getCurrentPosition());
                                        txtCurrent.setText(player.getCurrentPosition()/1000 + "");
                                    }
                                });
                            }

                            try { Thread.sleep(1000); } catch (InterruptedException e) {}
                        }
                    }
                };
                // 새로운 쓰레드로 스타트
                thread.start();

                break;
            // 플레이중이면 멈춤
            case PLAY :
                player.pause();
                playStatus = PAUSE;
                btnPlay.setImageResource(android.R.drawable.ic_media_play);
                break;
            // 멈춤상태이면 거기서 부터 재생
            case PAUSE:
                //player.seekTo(player.getCurrentPosition());
                player.start();

                playStatus = PLAY;
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                break;
        }
    }

    private void prev() {

    }

    private void next() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.release(); // 사용이 끝나면 해제해야만 한다.
        }
        playStatus = STOP;
    }
}


