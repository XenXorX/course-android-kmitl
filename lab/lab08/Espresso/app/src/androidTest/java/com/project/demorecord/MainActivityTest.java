package com.project.demorecord;

import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    public static RecycleViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecycleViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Please Enter user info"), isDisplayed()));
        textView.check(matches(withText("Please Enter user info")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton2.perform(scrollTo(), click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest2() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Please Enter user info"), isDisplayed()));
        textView.check(matches(withText("Please Enter user info")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton2.perform(scrollTo(), click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest3() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textNotFound), withText("Not Found"), isDisplayed()));
        textView.check(matches(withText("Not Found")));

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest4() {
        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("Ying")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Please Enter user info"), isDisplayed()));
        textView.check(matches(withText("Please Enter user info")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton2.perform(scrollTo(), click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest5() {
        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("Ying")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST")));
        appCompatButton2.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction textView = onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName));
        textView.check(matches(withText("Ying")));

        ViewInteraction textView2 = onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge));
        textView2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonClearList), withText("CLEAR LIST"), isDisplayed()));
        appCompatButton3.perform(click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest6() {
        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("Ying")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ladarat"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText3.check(matches(withText("Ladarat")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText4.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText4.check(matches(withText("20")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST")));
        appCompatButton2.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction textView = onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName));
        textView.check(matches(withText("Ladarat")));

        ViewInteraction textView2 = onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge));
        textView2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonClearList), withText("CLEAR LIST"), isDisplayed()));
        appCompatButton3.perform(click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest7() {
        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("Ying")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ladarat"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText3.check(matches(withText("Ladarat")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText4.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText4.check(matches(withText("20")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText5.perform(replaceText("Somkait"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText5.check(matches(withText("Somkait")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText6.perform(replaceText("80"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText6.check(matches(withText("80")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST")));
        appCompatButton2.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction textView = onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName));
        textView.check(matches(withText("Somkait")));

        ViewInteraction textView2 = onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge));
        textView2.check(matches(withText("80")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonClearList), withText("CLEAR LIST"), isDisplayed()));
        appCompatButton3.perform(click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest8() {
        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("Ying")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ladarat"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText3.check(matches(withText("Ladarat")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText4.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText4.check(matches(withText("20")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText5.perform(replaceText("Somkait"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText5.check(matches(withText("Somkait")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText6.perform(replaceText("80"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText6.check(matches(withText("80")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText7.perform(replaceText("Prayoch"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText7.check(matches(withText("Prayoch")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText8.perform(replaceText("60"), closeSoftKeyboard());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText8.check(matches(withText("60")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST")));
        appCompatButton2.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction textView = onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName));
        textView.check(matches(withText("Prayoch")));

        ViewInteraction textView2 = onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge));
        textView2.check(matches(withText("60")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonClearList), withText("CLEAR LIST"), isDisplayed()));
        appCompatButton3.perform(click());

        SystemClock.sleep(3000);
    }

    @Test
    public void mainActivityTest9() {
        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText.check(matches(withText("Ying")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText2.check(matches(withText("20")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText3.perform(replaceText("Ladarat"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText3.check(matches(withText("Ladarat")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText4.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText4.check(matches(withText("20")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText5.perform(replaceText("Somkait"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText5.check(matches(withText("Somkait")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText6.perform(replaceText("80"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText6.check(matches(withText("80")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText7.perform(replaceText("Prayoch"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText7.check(matches(withText("Prayoch")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText8.perform(replaceText("60"), closeSoftKeyboard());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText8.check(matches(withText("60")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText9.perform(replaceText("Prayoch"), closeSoftKeyboard());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        editText9.check(matches(withText("Prayoch")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText10.perform(replaceText("50"), closeSoftKeyboard());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        editText10.check(matches(withText("50")));

        SystemClock.sleep(3000);

        appCompatButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST")));
        appCompatButton2.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction textView = onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textName));
        textView.check(matches(withText("Prayoch")));

        ViewInteraction textView2 = onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textAge));
        textView2.check(matches(withText("50")));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonClearList), withText("CLEAR LIST"), isDisplayed()));
        appCompatButton3.perform(click());

        SystemClock.sleep(3000);
    }
}
