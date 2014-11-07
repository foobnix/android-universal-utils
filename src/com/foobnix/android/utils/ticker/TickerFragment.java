package com.foobnix.android.utils.ticker;

import android.support.v4.app.Fragment;

public abstract class TickerFragment extends Fragment implements OnTickListener {

    @Override
    public void onResume() {
        super.onResume();
        Ticker.getInstance().addOnTickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Ticker.getInstance().removeOnTickListener(this);
    }

}
