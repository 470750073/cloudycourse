package com.example.star.myapplication1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class Video extends AppCompatActivity {

    private VideoView video;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video=(VideoView)findViewById(R.id.video);
        //判断sd卡是否存在
        String SdPath="/sdcard";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //如果SD卡存在，则获取根目录
            SdPath=Environment.getExternalStorageDirectory().toString();
        }
        File file=new File(SdPath+"/Movies/my.mp4");//获取SD卡上要播放的文件
        MediaController mc=new MediaController(Video.this);
        if(file.exists()){//判断要播放的视频是否存在
            video.setVideoPath(file.getAbsolutePath());//指定要播放的视频
            video.setMediaController(mc);//设置VedioView与MediaController相关联
            video.requestFocus();//让VedioView获得焦点
            try {
                video.getBackground().setAlpha(0);//将背景图片设为透明
                video.start();//开始播放视频
            } catch (Exception e) {
                // TODO: handle exception
            }

            //为VideoView添加完成事件监听器
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    //弹出消息提示框显示播放完毕
                    Toast.makeText(Video.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            //todo 视频位置需要修改
            ////Uri uri = Uri.parse( "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4" );
            Uri uri = Uri.parse( "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4" );
            video.setVideoURI( uri );
            video.setMediaController(mc);//设置VedioView与MediaController相关联
            video.requestFocus();//让VedioView获得焦点
            video.getBackground().setAlpha(0);//将背景图片设为透明
            video.start();//开始播放视频
            //弹出消息提示框显示文件不存在
            Toast.makeText(Video.this, "要播放的视频文件不存在！", Toast.LENGTH_SHORT).show();
        }
    }
}
