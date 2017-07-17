package rf.digworld.headhands.ui.signin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class LoginActivity extends BaseActivity implements LogInMvpView {

    @Inject
    LoginPresenter loginPresenter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editText_email)
    EditText edit_email;
    @Bind(R.id.editText_password)
    EditText edit_password;
    @Bind(R.id.button_login)
    Button button_login;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @BindString(R.string.password_error) String passwordError;
    @BindString(R.string.empty_field_error) String emptyFieldError;

    @BindString(R.string.email_error) String emailFormatError;
    Dialog progressDialog;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter.attachView(this);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_black_24_px);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.menu_login);
        //setSupportActionBar(toolbar);


    }
    @OnClick(R.id.button_login)
    void onLoginClick(){
        if(verifyData()){
            String email=edit_email.getText().toString();
            String password=edit_password.getText().toString();

            loginPresenter.login(email,password);
        }
    }


    public boolean verifyData(){
        return isValidEmailAndShowError()&&(isValidPassAndShowError());
    }
    public  boolean isValidEmailAndShowError() {
        String email=edit_email.getText().toString();

        if(!TextUtils.isEmpty(email) ){
            return true;
        }else{
           if(TextUtils.isEmpty(email)){
               edit_email.setError(emptyFieldError);
           }else if( !Validators.isValidEmail(email)){
               edit_email.setError(emailFormatError);
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
                edit_password.setError(emptyFieldError);
            }else if(!Validators.isValidPassword(password)){
                edit_password.setError(passwordError);
            }
        }
        return false;
    }

    public void startDetailActivity(){

    }
    @OnClick(R.id.button_pass_question)
    void onQuestionClick(){
        DialogFactory.createGenericErrorDialog(this,passwordError).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void showLoginSuccessful(WeatherResponce weather) {
        Log.d("myLogs", "showLoginSuccessful");
        progressBar.setVisibility(View.GONE);

        Snackbar.make(button_login, "В Москве сейчас "+weather.getMain().getTemp(), Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    @Override
    public void showProgress() {
        edit_email.setEnabled(false);
        edit_password.setEnabled(false);
        button_login.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError(String error) {
        edit_email.setEnabled(true);
        edit_password.setEnabled(true);
        button_login.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        Snackbar.make(button_login,error,Snackbar.LENGTH_LONG).setAction("",null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }
}
