package com.ymd.rossellamorgante.ymd;

import android.os.SystemClock;

import com.ymd.rossellamorgante.ymd.controller.SearchController;
import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.view.MainActivity;

import androidx.annotation.Nullable;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SearchControllerTest {


    @Rule
    public ActivityTestRule<MainActivity> mactivity = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void searchRightLocation(){
        SearchController sc = new SearchController(mactivity.getActivity());
        sc.bindLiveData().observeForever (
                new Observer<ArrayList<FSPlace>>() {
                    @Override
                    public void onChanged(@Nullable final ArrayList<FSPlace> _venues) {
                        assertTrue("ok",_venues.size()>0);
                    }
                }
        );
        sc.query("Staines");
    }

    @Test
    public void searchBadLocation(){
        SearchController sc = new SearchController(mactivity.getActivity());
        sc.bindLiveData().observeForever (
                new Observer<ArrayList<FSPlace>>() {
                    @Override
                    public void onChanged(@Nullable final ArrayList<FSPlace> _venues) {
                        assertTrue("ok",_venues==null);
                    }
                }
        );
        sc.query("Stdadaadadadsaines");
    }

}
