package com.java.threads;

/**
 * This program demonstrate how to create dead lock situation
 * 
 * @author Paramesh
 *
 */
public class DeadLockDemo {

    String str1 = "Java";

    String str2 = "UNIX";

    Thread trd1 = new Thread("My Thread 1") {
        @Override
        public void run() {
            int i = 1;
            while (true) {
                synchronized (str1) {
                    synchronized (str2) {
                        System.out.println(String.join("", String.valueOf(i++), ". ", trd1.getName(), " : ", str1, str2));
                    }
                }
            }
        }
    };

    Thread trd2 = new Thread("My Thread 2") {
        @Override
        public void run() {
            int i = 1;
            while (true) {
                synchronized (str2) {
                    synchronized (str1) {
                        System.out.println(String.join("", String.valueOf(i++), ". ", trd2.getName(), " : ", str2, str1));
                        ;
                    }
                }
            }
        }
    };

    public static void main(String a[]) {
        DeadLockDemo lock = new DeadLockDemo();
        lock.trd1.setPriority(3);
        lock.trd2.setPriority(2);
        
        lock.trd2.start();
        lock.trd1.start();
    }
}
