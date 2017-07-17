package rf.digworld.headhands;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.data.remote.NetworkService;
import rf.digworld.headhands.data.DataManager;
import rf.digworld.headhands.data.local.DatabaseHelper;
import rf.digworld.headhands.data.local.PreferencesHelper;
import rf.digworld.headhands.test.common.TestDataFactory;
import rf.digworld.headhands.util.EventPosterHelper;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock DatabaseHelper mMockDatabaseHelper;
    @Mock PreferencesHelper mMockPreferencesHelper;
    @Mock
    NetworkService mMockNetworkService;
    @Mock EventPosterHelper mEventPosterHelper;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockNetworkService, mMockPreferencesHelper,
                mMockDatabaseHelper, mEventPosterHelper);
    }
    @Test
    public void syncWeatherEmitsValues() {
        WeatherResponce weatherResponce= TestDataFactory.makeWeather();
        stubSyncRibotsHelperCalls(weatherResponce);

        TestSubscriber<WeatherResponce> result = new TestSubscriber<>();
        mDataManager.getWeather().subscribe(result);
        result.assertNoErrors();
       // result.assertReceivedOnNext(weatherResponce);
    }

    private void stubSyncRibotsHelperCalls(WeatherResponce weatherResponce) {

        when(mMockNetworkService.getWeather(22))
                .thenReturn(Observable.just(weatherResponce));
    }
   /*

    @Test
    public void syncRibotsCallsApiAndDatabase() {
        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot("r1"),
                TestDataFactory.makeRibot("r2"));
        stubSyncRibotsHelperCalls(ribots);

        mDataManager.syncRibots().subscribe();
        // Verify right calls to helper methods
        verify(mMockNetworkService).getRibots();
        verify(mMockDatabaseHelper).setRibots(ribots);
    }

    @Test
    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
        when(mMockNetworkService.getRibots())
                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));

        mDataManager.syncRibots().subscribe(new TestSubscriber<Ribot>());
        // Verify right calls to helper methods
        verify(mMockNetworkService).getRibots();
        verify(mMockDatabaseHelper, never()).setRibots(anyListOf(Ribot.class));
    }

    */

}
