package rf.digworld.headhands.ui.signin;

import javax.inject.Inject;

import rf.digworld.headhands.data.DataManager;
import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.ui.base.BasePresenter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Дмитрий on 27.03.2016.
 */
public class SigninPresenter extends BasePresenter<SigninMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;
    @Inject
    public SigninPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SigninMvpView mvpView) {
        super.attachView(mvpView);
    }


    public void signin(String email,String password){
        checkViewAttached();
        getMvpView().showProgress();
        mSubscription = mDataManager.login(email,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().showSuccessful();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String result) {
                        if (!result.equals("Success")) {
                            getMvpView().showError(result);
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
