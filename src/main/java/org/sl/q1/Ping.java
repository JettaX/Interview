package org.sl.q1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Ping implements Runnable{
    private final Lock lock;
    private final Condition pingCond;
    private final Condition pongCond;

    public Ping(Lock lock, Condition pingCond, Condition pongCond) {
        this.lock = lock;
        this.pingCond = pingCond;
        this.pongCond = pongCond;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                System.out.println("ping");
                pongCond.signal();
                pingCond.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
