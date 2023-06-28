package ui.categories

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cookbook.R
import com.example.cookbook.ui.categories.CategoriesFragment
import com.example.cookbook.ui.categories.CategoriesRVAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ui.delay
import ui.tapOnItemWithId

const val clickedItem = "Vegan"
const val scrollToItem = "Goat"
const val positionClick = 1

@RunWith(AndroidJUnit4::class)
class CategoriesRecyclerViewTest {

    private lateinit var scenario: FragmentScenario<CategoriesFragment>
    val bundle = Bundle()


    @Before
    fun setup() {

        scenario = launchFragmentInContainer(bundle, R.style.Base_Theme_MainActivity) {
            CategoriesFragment()
        }
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @Test
    fun categoryFragmentRecyclerView_ScrollTo() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.categories))
            .perform(
                RecyclerViewActions.scrollTo<CategoriesRVAdapter.ViewHolder>(
                    hasDescendant(withText(clickedItem))
                )
            )
    }


    @Test
    fun categoryFragmentRecyclerView_PerformClickAtPosition() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.categories))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CategoriesRVAdapter.ViewHolder>(
                    positionClick,
                    click()
                )
            )
    }

    @Test
    fun categoryFragmentRecyclerView_PerformClickOnItem() {
        onView(isRoot()).perform(delay())

        onView(withId(R.id.categories))
            .perform(
                RecyclerViewActions.scrollTo<CategoriesRVAdapter.ViewHolder>(
                    hasDescendant(withText(scrollToItem))
                )
            )

        onView(withId(R.id.categories))
            .perform(
                RecyclerViewActions.actionOnItem<CategoriesRVAdapter.ViewHolder>(
                    hasDescendant(withText(clickedItem)),
                    click()
                )
            )
    }

    @Test
    fun categoryFragmentRecyclerView_PerformCustomClick() {
        onView(isRoot()).perform(delay())



        onView(withId(R.id.categories))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<CategoriesRVAdapter.ViewHolder>(
                        positionClick,
                        tapOnItemWithId(R.id.root_linear_layout)
                    )
            )
    }
}



