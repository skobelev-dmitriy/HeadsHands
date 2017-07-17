package rf.digworld.headhands.ui.signin;

import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.ui.base.MvpView;

/**
 * Created by Дмитрий on 27.03.2016.
 */
public interface SigninMvpView extends MvpView{
    void showSuccessful();
    void showError(String string);
    void showProgress();
}
