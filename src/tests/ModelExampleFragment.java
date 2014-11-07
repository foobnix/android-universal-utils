package tests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.foobnix.android.utils.ModelFragment;
import com.foobnix.android.utils.R;
import com.foobnix.android.utils.res.ExtraArgument_1;
import com.foobnix.android.utils.res.ResId;
import com.foobnix.android.utils.res.ResInjector;
import com.foobnix.android.utils.res.SaveState;

public class ModelExampleFragment extends ModelFragment<User> {

    @ResId
    EditText textLogin, textPassword;

    @ResId
    CheckBox checkboxAggrement;

    @ExtraArgument_1
    int currentTab;

    @SaveState
    boolean isSignAgreement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        ResInjector.inject(view, this, savedInstanceState);

        // activeTab(currentTab);
        populateModel();
        return view;
    }

    // @ResIdOnClick(R.id.onLogin)
    public void onLogin() {
        saveModel();
        // doLoginUser(model);
    }

    @Override
    public void populateModel() {
        checkboxAggrement.setChecked(isSignAgreement);
        textLogin.setText(model.getName());
    }

    @Override
    public void saveModel() {
        model.setName(textLogin.getText().toString());
    }

}
