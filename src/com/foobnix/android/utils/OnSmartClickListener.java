package com.foobnix.android.utils;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class OnSmartClickListener implements OnClickListener {

    private long clickTime = 0;
    private final long delay;

    public OnSmartClickListener(long delay) {
        this.delay = delay;
    }

    public abstract void onSmartClick(final View v);

    @Override
    public void onClick(final View v) {
        long time = System.currentTimeMillis();
        if (time - clickTime < delay) {
            LOG.d("Smart click not ready");
            clickTime = time;
            return;
        }
        LOG.d("Smart click OK");
        if (v != null) {
            v.setEnabled(false);
            v.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (v != null) {
                        v.setEnabled(true);
                    }

                }
            }, delay);
        }
        onSmartClick(v);

    }

}
