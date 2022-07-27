package org.example;

class PrintInOrder_1114 {

    private volatile boolean t1;
    private volatile boolean t2;

    public PrintInOrder_1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        t1 = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {

        while (!t1) {}
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        t2 = true;
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (!t2) {}
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}