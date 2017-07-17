package rf.digworld.headhands;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import rf.digworld.headhands.test.common.rules.TestComponentRule;
import rf.digworld.headhands.ui.main.MainActivity;
import rf.digworld.headhands.ui.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<LoginActivity> main =
            new ActivityTestRule<LoginActivity>(LoginActivity.class, false, false) {
                @Override
                protected Intent getActivityIntent() {
                    // Override the default intent so we pass a false flag for syncing so it doesn't
                    // start a sync service in the background that would affect  the behaviour of
                    // this test.
                    return MainActivity.getStartIntent(
                            InstrumentationRegistry.getTargetContext());
                }
            };

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public final TestRule chain = RuleChain.outerRule(component).around(main);


    @Test
    public void buttonLoginShows(){
        main.launchActivity(null);
        onView(ViewMatchers.withId(R.id.button_login)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.button_login)).check(matches(isClickable()));
    }
    @Test
    public void editEmailShows(){
        main.launchActivity(null);
        onView(ViewMatchers.withId(R.id.editText_email)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.editText_email)).check(matches(isEnabled()));
    }
    @Test
    public void editPasswordShows(){
        main.launchActivity(null);
        onView(ViewMatchers.withId(R.id.editText_password)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.editText_password)).check(matches(isEnabled()));
    }


}