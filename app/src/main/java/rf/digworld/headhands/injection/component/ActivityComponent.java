package rf.digworld.headhands.injection.component;

import android.app.Activity;

import dagger.Component;
import rf.digworld.headhands.injection.PerActivity;
import rf.digworld.headhands.injection.module.ActivityModule;
import rf.digworld.headhands.ui.login.LoginActivity;
import rf.digworld.headhands.ui.signin.SigninActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(Activity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(SigninActivity signinActivity);

}
