package tests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.foobnix.android.utils.EmptyModelFragment;
import com.foobnix.android.utils.R;
import com.foobnix.android.utils.res.ExtraArgument_1;
import com.foobnix.android.utils.res.ResId;
import com.foobnix.android.utils.res.ResIdOnClick;
import com.foobnix.android.utils.res.ResInjector;
import com.foobnix.android.utils.res.SaveState;

public class SimpleExampleFragment extends EmptyModelFragment {

    @ResId
    EditText textLogin, textPassword;

    @ExtraArgument_1
    int currentTab;

    @SaveState
    boolean myValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        ResInjector.inject(view, this, savedInstanceState);

        return view;
    }

    @ResIdOnClick(R.id.onLogin)
    public void doAction() {

    }

}
