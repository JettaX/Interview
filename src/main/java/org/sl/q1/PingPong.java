package org.sl.q1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition pingCond = lock.newCondition();
        Condition pongCond = lock.newCondition();

        Thread ping = new Thread(new Ping(lock, pingCond, pongCond));
        Thread pong = new Thread(new Pong(lock, pingCond, pongCond));

        ping.start();
        pong.start();
    }
}
