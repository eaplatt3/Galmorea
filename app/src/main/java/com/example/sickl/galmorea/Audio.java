//////////////////////////
//     Galmoreas        //
//   Earl Platt III     //
//     © 2018-2019      //
//////////////////////////

package com.example.sickl.galmorea;

import android.media.MediaPlayer;

import java.io.File;

public class Audio {

    public void playAudio(String path, String fileName)
    {
        MediaPlayer mp = new MediaPlayer();

        try{
            mp.setDataSource(path + File.separator + fileName);
            mp.prepare();
            mp.start();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

