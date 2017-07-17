package rf.digworld.headhands.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import rf.digworld.headhands.data.DataManager;
import rf.digworld.headhands.data.local.DatabaseHelper;
import rf.digworld.headhands.data.local.PreferencesHelper;
import rf.digworld.headhands.data.remote.NetworkService;
import rf.digworld.headhands.injection.ApplicationContext;
import rf.digworld.headhands.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {



    @ApplicationContext Context context();
    Application application();
    NetworkService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
