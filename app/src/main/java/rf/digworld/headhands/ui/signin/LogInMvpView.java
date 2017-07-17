package rf.digworld.headhands.ui.signin;

import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.ui.base.MvpView;

/**
 * Created by Дмитрий on 27.03.2016.
 */
public interface LogInMvpView extends MvpView{
    void showLoginSuccessful(WeatherResponce weatherResponce);
    void showError(String string);
    void showProgress();
}
