package com.cst2335.xie00076;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * this is the main test to test if the password is too long or too short
     */
    @Test
    public void mainActivityTest() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edittext),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textview), withText("You shall not pass"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("You shall not pass")));
    }
    @Test
    public void testToolong(){
        //find the view
        ViewInteraction appCompatEditText= onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("password123#$*12359687"));
        //find the button
        ViewInteraction materialButton=onView(withId(R.id.button));
        materialButton.perform(click());
        //find the text view
        ViewInteraction textView=onView(withId(R.id.textview));
        textView.check(matches(withText("You shall not pass")));
    }
    /**
     * this is used to test if there is upper case in the input
     */
  @Test
  public void testFindUpperCase(){
        //find the view
      ViewInteraction appCompatEditText= onView(withId(R.id.edittext));
      appCompatEditText.perform(replaceText("password123#$*"));
      //find the button
      ViewInteraction materialButton=onView(withId(R.id.button));
      materialButton.perform(click());
      //find the text view
      ViewInteraction textView=onView(withId(R.id.textview));
      textView.check(matches(withText("You shall not pass")));
  }
    /**
     * this is used to test if there is lower case in the input
     */
  @Test
  public void testFindLowerCase(){
        ViewInteraction appCompatEditText= onView(withId(R.id.edittext));
        appCompatEditText.perform((replaceText("PASSWORD123&*")));
        ViewInteraction materialButton=onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView=onView(withId(R.id.textview));
        textView.check(matches(withText("You shall not pass")));
  }

    /**
     * this is used to test if there is special character in the input
     */
  @Test
  public void testSpecialChar(){
        ViewInteraction appCompatEditText=onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("PassWord123"));
        ViewInteraction materialButton=onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView=onView(withId(R.id.textview));
        textView.check(matches(withText("You shall not pass")));
  }

    /**
     * this is used to test if there is number in the input
     */
  @Test
  public void testFindNumber(){
        ViewInteraction appCompatEditText=onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("PassWord&%$"));
        ViewInteraction materialButton=onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView=onView(withId(R.id.textview));
        textView.check(matches(withText("You shall not pass")));
  }

    /**
     * this is used to test if it will succeed with all matching criteria
     */
  @Test
  public void testPassRequirements(){
        ViewInteraction appCompatEditText=onView(withId(R.id.edittext));
        appCompatEditText.perform(replaceText("Password123!*"));
        ViewInteraction materialButton=onView(withId(R.id.button));
        materialButton.perform(click());
        ViewInteraction textView=onView(withId(R.id.textview));
        textView.check(matches(withText("Your password is complex enough")));
  }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
