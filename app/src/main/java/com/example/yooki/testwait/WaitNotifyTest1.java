package com.example.yooki.testwait;


/**
 */
public class WaitNotifyTest1 {

    // 在多线程间共享的对象上使用wait
    private String[] shareObj = {"true"};

    public static void main(String[] args) {
        WaitNotifyTest1 test = new WaitNotifyTest1();
        ThreadWait threadWait1 = test.new ThreadWait("wait thread1");
        threadWait1.setPriority(2);


        ThreadNotify threadNotify = test.new ThreadNotify("notify thread");

        threadNotify.start();
        threadWait1.start();
    }

    class ThreadWait extends Thread {

        public ThreadWait(String name) {
            super(name);
        }

        public void run() {
            synchronized (shareObj) {
                while ("true".equals(shareObj[0])) {
                    System.out.println("线程  " + getName() + "  ====无限循环");
                }
            }
            System.out.println("线程" + getName() + "等待结束");
        }
    }

    class ThreadNotify extends Thread {

        public ThreadNotify(String name) {
            super(name);
        }


        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程  " + getName() + "  休眠结束========================");
            shareObj[0] = "false";
        }
    }
}