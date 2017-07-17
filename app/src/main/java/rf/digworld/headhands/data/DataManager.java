package rf.digworld.headhands.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rf.digworld.headhands.data.model.WeatherResponce;
import rx.Observable;
import rx.functions.Action0;
import rf.digworld.headhands.data.local.DatabaseHelper;
import rf.digworld.headhands.data.local.PreferencesHelper;
import rf.digworld.headhands.data.remote.NetworkService;
import rf.digworld.headhands.util.EventPosterHelper;

@Singleton
public class DataManager {

    private final NetworkService mNetworkService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(NetworkService networkService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mNetworkService = networkService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


    /**
     * Возвращает success в любом случае
     * @param email
     * @param pass
     * @return
     */
    public Observable<String> login(String email, String pass) {
        Observable<String> myObservable = Observable.just("Success");
        return myObservable;
    }

    /**
     * Получение текущей погоды для города Москвы (id=5601538)
     * @return
     */
    public Observable<WeatherResponce> getWeather(){
        return mNetworkService.getWeather(5601538);
    }


    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }

}
