package com.ymd.rossellamorgante.ymd;

import android.os.SystemClock;
import com.ymd.rossellamorgante.ymd.view.MainActivity;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mactivity = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void writeRightLocation() {

        onView(withId(R.id.app_bar_search))
                .perform(click())
                .perform(typeText("Staines") , pressKey(KEYCODE_ENTER));

        SystemClock.sleep(10000);

        onView(withId(R.id.venueList)).check(matches(isDisplayed()));


    }

    @Test
    public void writeBadLocation() {

        onView(withId(R.id.app_bar_search))
                .perform(click())
                .perform(typeText("addhakdhaskjd") , pressKey(KEYCODE_ENTER));

        SystemClock.sleep(10000);

        onView(withId(R.id.textSpinner))
                .check(matches(withText("Something was gone wrong. Try again!")));

    }

}
