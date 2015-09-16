package com.afc.biblereading.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.afc.biblereading.R;
import com.afc.biblereading.helper.DataHolder;
import com.afc.biblereading.helper.DialogUtils;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.List;

public class SignUpUserActivity extends BaseActivity {

    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();
    }

    private void initUI() {
        loginEditText = (EditText) findViewById(R.id.login_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                progressDialog.show();

                // Sign Up user
                //
                QBUser qbUser = new QBUser();
                qbUser.setLogin(loginEditText.getText().toString());
                qbUser.setPassword(passwordEditText.getText().toString());
                QBUsers.signUpSignInTask(qbUser, new QBEntityCallbackImpl<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        progressDialog.hide();

                        DataHolder.getDataHolder().addQbUserToList(qbUser);
                        DataHolder.getDataHolder().setSignInQbUser(qbUser);
                        DataHolder.getDataHolder().setSignInUserPassword(passwordEditText.getText().toString());

                        finish();
                    }

                    @Override
                    public void onError(List<String> strings) {
                        progressDialog.hide();
                        DialogUtils.showLong(context, strings.get(0));
                    }
                });

                break;
        }
    }
}