package rf.digworld.headhands.ui.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rf.digworld.headhands.R;
import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.ui.base.BaseActivity;
import rf.digworld.headhands.util.DialogFactory;
import rf.digworld.headhands.util.Validators;
import rf.digworld.headhands.util.ViewUtil;

public class SigninActivity extends BaseActivity implements SigninMvpView {

    @Inject
    SigninPresenter signinPresenter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editText_email)
    EditText edit_email;
    @Bind(R.id.editText_password)
    EditText edit_password;
    @Bind(R.id.editText_password2)
    EditText edit_password2;
    @Bind(R.id.input_layout_password)
    TextInputLayout textInputLayout_pass;
    @Bind(R.id.input_layout_password2)
    TextInputLayout textInputLayout_pass2;
    @Bind(R.id.input_layout_mail)
    TextInputLayout textInputLayout_mail;
    @Bind(R.id.button_signin)
    Button button_signin;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @BindString(R.string.password_error) String passwordError;
    @BindString(R.string.empty_field_error) String emptyFieldError;
    @BindString(R.string.equals_password) String passwordDontEquals;
    @BindString(R.string.email_error) String emailFormatError;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SigninActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        signinPresenter.attachView(this);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_black_24_px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.menu_login);
        ViewUtil.setMiuiStatusBarDarkMode(SigninActivity.this,true);


    }
    @OnClick(R.id.button_signin)
    void onSigninClick(){
        textInputLayout_mail.setError(null);
        textInputLayout_pass.setError(null);
        textInputLayout_pass2.setError(null);
        if(verifyData()){
            String email=edit_email.getText().toString();
            String password=edit_password.getText().toString();

            signinPresenter.signin(email,password);
        }
    }


    public boolean verifyData(){
        return isValidEmailAndShowError()&&(isValidPassAndShowError())&&(isEqualPassAndShowError());
    }
    public  boolean isValidEmailAndShowError() {
        String email=edit_email.getText().toString();

        if(!TextUtils.isEmpty(email) ){
            return true;
        }else{
           if(TextUtils.isEmpty(email)){
               textInputLayout_mail.setError(emptyFieldError);
           }else if( !Validators.isValidEmail(email)){
               textInputLayout_mail.setError(emailFormatError);
           }
        }
        return false;
    }
    public  boolean isValidPassAndShowError() {
        String password=edit_password.getText().toString();
        if(!TextUtils.isEmpty(password)&&Validators.isValidPassword(password)){
            return true;
        }else{
            if(TextUtils.isEmpty(password)){
                textInputLayout_pass.setError(emptyFieldError);
            }else if(!Validators.isValidPassword(password)){
                textInputLayout_pass.setError(passwordError);
            }
        }
        return false;
    }
    public  boolean isEqualPassAndShowError() {
        String password=edit_password.getText().toString();
        String password2=edit_password2.getText().toString();
        if(password.equals(password2)){
            return true;
        }else{
            textInputLayout_pass2.setError(passwordDontEquals);
        }
        return false;
    }


    @OnClick(R.id.button_pass_question)
    void onQuestionClick(){
        DialogFactory.createGenericErrorDialog(this,passwordError).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        signinPresenter.detachView();
    }

    @Override
    public void showSuccessful() {
        Log.d("myLogs", "showLoginSuccessful");
        progressBar.setVisibility(View.GONE);

        Snackbar.make(button_signin, "Успешная регистрация", Snackbar.LENGTH_LONG)
                .setAction("Ok", null).show();
        onBackPressed();
    }

    @Override
    public void showProgress() {
        edit_email.setEnabled(false);
        edit_password.setEnabled(false);
        button_signin.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError(String error) {
        edit_email.setEnabled(true);
        edit_password.setEnabled(true);
        button_signin.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        Snackbar.make(button_signin,error,Snackbar.LENGTH_LONG).setAction("",null).show();
    }


}
