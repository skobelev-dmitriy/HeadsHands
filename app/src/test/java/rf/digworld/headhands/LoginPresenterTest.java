package rf.digworld.headhands;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rf.digworld.headhands.data.DataManager;
import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.test.common.TestDataFactory;
import rf.digworld.headhands.ui.signin.LogInMvpView;
import rf.digworld.headhands.ui.signin.LoginPresenter;
import rf.digworld.headhands.util.RxSchedulersOverrideRule;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    LogInMvpView mMockLoginMvpView;
    @Mock DataManager mMockDataManager;
    private LoginPresenter loginPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        loginPresenter = new LoginPresenter(mMockDataManager);
        loginPresenter.attachView(mMockLoginMvpView);
    }

    @After
    public void tearDown() {
        loginPresenter.detachView();
    }

    @Test
    public void loadWeatherShowWeather() {
        WeatherResponce weatherResponce = TestDataFactory.makeWeather();
        doReturn(Observable.just(weatherResponce))
                .when(mMockDataManager)
                .getWeather();

        loginPresenter.getWeather();
        verify(mMockLoginMvpView).showLoginSuccessful(weatherResponce);
        verify(mMockLoginMvpView, never()).showError();
    }



    @Test
    public void loadWeatherFails() {
        doReturn(Observable.error(new RuntimeException()))
                .when(mMockDataManager)
                .getWeather();

        loginPresenter.getWeather();
        verify(mMockLoginMvpView).showError();
        verify(mMockLoginMvpView, never()).showLoginSuccessful(any(WeatherResponce.class));
    }
}
