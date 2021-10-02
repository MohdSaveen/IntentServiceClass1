package com.example.intentserviceclass1;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class DownloadService extends IntentService {


    public DownloadService() {
        super("DownloadService");
    }

    public DownloadService(String name) {
        super("async");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        downloadFile();
    }


    private void downloadFile(){

        try{
            File directory=new File(getFilesDir() +File.separator+"Vogella");

            if(!directory.exists()){
                directory.mkdir();
            }
            File file=new File(directory,"vogella.html");

            if(!file.exists()){
                file.createNewFile();
            }

            URL url=new URL("http://www.vogella.com/index.html");

            InputStream inputStream=url.openConnection().getInputStream();

            InputStreamReader reader=new InputStreamReader(inputStream);

            FileOutputStream writer=new FileOutputStream(file,true);
            int data=reader.read();
            while(data !=-1){
                char ch= (char) data;
                writer.write(ch);
                data =reader.read();
            }

            FileInputStream fileInputStream=new FileInputStream(file);
            int fileData=fileInputStream.read();
            StringBuffer stringBuffer=new StringBuffer();
            while(fileData !=-1){
                char ch= (char) fileData;
                stringBuffer =stringBuffer.append(ch);
                fileData=fileInputStream.read();
            }
        }catch (Exception e){


        }
    }
}