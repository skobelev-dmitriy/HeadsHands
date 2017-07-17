package rf.digworld.headhands.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rf.digworld.headhands.R;
import rf.digworld.headhands.ui.base.BaseActivity;
import rf.digworld.headhands.ui.login.LoginActivity;
import rf.digworld.headhands.util.ViewUtil;

public class MainActivity extends BaseActivity {


    @Bind(R.id.button_login)
    Button button_login;
    @Bind(R.id.imageView)
    ImageView logoView;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.logo).into(logoView);

        ViewUtil.setMiuiStatusBarDarkMode(MainActivity.this,true);

    }
    @OnClick(R.id.button_login)
    public void loginOnClick(){
        startActivity(LoginActivity.getStartIntent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


}
