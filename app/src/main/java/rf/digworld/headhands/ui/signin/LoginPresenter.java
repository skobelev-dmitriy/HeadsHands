package rf.digworld.headhands.ui.signin;

import javax.inject.Inject;

import rf.digworld.headhands.data.DataManager;
import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.ui.base.BasePresenter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Дмитрий on 27.03.2016.
 */
public class LoginPresenter extends BasePresenter<LogInMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;
    @Inject
    public LoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(LogInMvpView mvpView) {
        super.attachView(mvpView);
    }


    public void login(String email,String password){
        checkViewAttached();
        getMvpView().showProgress();
        mSubscription = mDataManager.login(email,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String result) {
                        if (result.equals("Success")) {
                            getWeather();
                        } else {
                            getMvpView().showError(result);
                        }
                    }
                });


    }
    public void getWeather(){
        checkViewAttached();
        getMvpView().showProgress();
        mSubscription = mDataManager.getWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WeatherResponce>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                        getMvpView().showError(e.getMessage());;
                    }

                    @Override
                    public void onNext(WeatherResponce result) {
                        if (result.getError()==null) {
                            getMvpView().showLoginSuccessful(result);
                        } else {
                            getMvpView().showError(result.getError());
                        }
                    }
                });


    }
    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
