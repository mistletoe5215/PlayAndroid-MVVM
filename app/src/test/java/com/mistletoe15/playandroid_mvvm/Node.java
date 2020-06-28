package com.mistletoe15.playandroid_mvvm;

import androidx.annotation.NonNull;

/**
 * Created by Mistletoe on 2020/6/22
 **/
public class Node <T>{
    private T data;
    Node next = null;
    void setData(T data){
        this.data = data;
    }
    public T getData() {
        return data;
    }
    @NonNull
    @Override
    public String toString() {
        return this.data.toString();
    }
}
