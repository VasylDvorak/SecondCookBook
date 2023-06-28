package ui.menu

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cookbook.R
import com.example.cookbook.application.App
import com.example.cookbook.ui.menu.MenuFragment
import com.example.cookbook.ui.menu.MenuRVAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ui.delay
import ui.tapOnItemWithId

const val clickedMenuItem = "Vegan Chocolate Cake"
const val scrollToMenuItem = "Vegan Lasagna"
const val positionMenuClick = 0

@RunWith(AndroidJUnit4::class)
class MenuRecyclerViewTest {

    private lateinit var scenario: FragmentScenario<MenuFragment>

    @Before
    fun setup() {
        App.instance.initCategorySubcomponent()

        scenario = launchFragmentInContainer(fragmentArgs, R.style.Base_Theme_MainActivity) {
            MenuFragment()
        }
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @Test
    fun categoryFragmentRecyclerView_ScrollTo() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.menu))
            .perform(
                RecyclerViewActions.scrollTo<MenuRVAdapter.ViewHolder>(
                    hasDescendant(withText(clickedMenuItem))
                )
            )
    }


    @Test
    fun categoryFragmentRecyclerView_PerformClickAtPosition() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.menu))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MenuRVAdapter.ViewHolder>(
                    positionMenuClick,
                    click()
                )
            )
    }

    @Test
    fun categoryFragmentRecyclerView_PerformClickOnItem() {
        onView(isRoot()).perform(delay())

        onView(withId(R.id.menu))
            .perform(
                RecyclerViewActions.scrollTo<MenuRVAdapter.ViewHolder>(
                    hasDescendant(withText(scrollToMenuItem))
                )
            )

        onView(withId(R.id.menu))
            .perform(
                RecyclerViewActions.actionOnItem<MenuRVAdapter.ViewHolder>(
                    hasDescendant(withText(clickedMenuItem)),
                    click()
                )
            )
    }

    @Test
    fun categoryFragmentRecyclerView_PerformCustomClick() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.menu))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<MenuRVAdapter.ViewHolder>(
                        positionMenuClick,
                        tapOnItemWithId(R.id.menu_root_linear_layout)
                    )
            )
    }
}



