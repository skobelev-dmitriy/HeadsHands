package rf.digworld.headhands.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rf.digworld.headhands.TestApplication;
import rf.digworld.headhands.injection.component.ActivityComponent;
import rf.digworld.headhands.injection.component.DaggerActivityComponent;
import rf.digworld.headhands.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(TestApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}
