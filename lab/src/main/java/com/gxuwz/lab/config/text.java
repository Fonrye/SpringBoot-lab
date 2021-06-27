package com.gxuwz.lab.config;

import com.gxuwz.lab.entry.Form;

public class text {
    public static void main(String[] args) {
        Form form = new Form();


        if (form==null){
            System.out.println("没有form");

        }else {
            System.out.println("new一个对象form不为空");
        }
    }
}
