/**
 * 
 */
package com.foobnix.android.utils;

import java.util.HashSet;
import java.util.Set;

import android.os.Handler;

/**
 * @author dmytro
 * 
 */
public class Ticker {

    private final Handler handler = new Handler();
    private final Runnable r = new Runnable() {

        @Override
        public void run() {
            for (OnTickListener listener : listeners) {
                listener.onTick();
            }

            tick();

        }
    };;
    private long period = 1000;
    private boolean started = false;
    private final Set<OnTickListener> listeners = new HashSet<OnTickListener>();

    /**
     * @param period
     *            - tick period
     */

    private volatile static Ticker instance;

    /** Returns singleton class instance */
    public static Ticker getInstance() {
        if (instance == null) {
            synchronized (Ticker.class) {
                if (instance == null) {
                    instance = new Ticker();
                }
            }
        }
        return instance;
    }

    private Ticker() {
    }


    private void startTimer() {
        if (!started) {
            handler.postDelayed(r, period);
            started = true;
        }

    }

    private void tick() {
        handler.postDelayed(r, period);
    }

    public void setOnTickListener(OnTickListener listener) {
        listeners.add(listener);
        startTimer();
    }

    public void removeOnTickListener(OnTickListener listener) {
        listeners.remove(listener);
        if (listeners.size() < 1) {
            handler.removeCallbacks(r);
            started = false;
        }

    }

    public boolean isStarted() {
        return started;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

}
