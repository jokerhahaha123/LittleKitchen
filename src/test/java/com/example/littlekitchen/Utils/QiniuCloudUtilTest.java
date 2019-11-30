package com.example.littlekitchen.Utils;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class QiniuCloudUtilTest {

    @Test
    public void getUpToken() {
    }

    @Test
    public void upload() {
        try {
            QiniuCloudUtil.upload("C:\\Users\\Think\\Pictures\\229561.jpg", "123");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void put64image() {
        InputStream in = null;
        byte[] data = null;
        try
        {
            in = new FileInputStream("C:\\Users\\Think\\Pictures\\229561.jpg");
            data = new byte[in.available()];
            QiniuCloudUtil.put64image(data, "test1");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
    }
}