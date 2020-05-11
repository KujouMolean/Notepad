package com.molean.notepad;

public class Boot {
    private static int cnt=0;
    public static void main(String[] args) {
        boot(args);
    }
    public static void boot(String[] args){
        new Notepad(args);
        cnt++;
    }
    public static void die() {
        cnt--;
        if(cnt==0)
            System.exit(0);
    }
}
