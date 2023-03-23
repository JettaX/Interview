package org.sl.q1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Pong implements Runnable {
    private final Lock lock;
    private final Condition pingCond;
    private final Condition pongCond;

    public Pong(Lock lock, Condition pingCond, Condition pongCond) {
        this.lock = lock;
        this.pingCond = pingCond;
        this.pongCond = pongCond;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                System.out.println("\tpong");
                pingCond.signal();
                pongCond.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}