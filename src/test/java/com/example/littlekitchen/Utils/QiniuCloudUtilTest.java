package com.example.littlekitchen.Utils;

import org.junit.Test;

import java.io.IOException;

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
    }

    @Test
    public void delete() {
    }
}