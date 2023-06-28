package ui

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


fun tapOnItemWithId(id: Int) = object : ViewAction {
    override fun getConstraints(): Matcher<View>? {
        return null
    }

    override fun getDescription(): String {
        return "Нажимаем на view с указанным id"
    }

    override fun perform(uiController: UiController, view: View) {
        val v = view.findViewById(id) as View
        v.performClick()
    }

}



fun delay(): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
        override fun getDescription(): String = "wait for $20 seconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(20000L)
        }
    }
}
