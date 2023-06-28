package ui.recipe

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cookbook.R
import com.example.cookbook.application.App
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.ui.recipe.CURRENT_RECIPE
import com.example.cookbook.ui.recipe.RecipeFragment
import org.hamcrest.CoreMatchers.not
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ui.delay

val fragmentArgs = bundleOf(CURRENT_RECIPE to Menu(idMeal = "52942"))

@RunWith(AndroidJUnit4::class)
class RecipeFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<RecipeFragment>
    private lateinit var assertion: ViewAssertion

    @Before
    fun setup() {

        App.instance.initCategorySubcomponent()
        App.instance.initMenuSubcomponent()

        scenario = launchFragmentInContainer(fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @Test
    fun fragment_testPresenter() {
        scenario.onFragment { fragment ->
            Assert.assertNotNull(fragment.presenter)
        }
    }

    @Test
    fun fragment_testCurrentItemMenu() {
        scenario.onFragment { fragment ->
            Assert.assertNotNull(fragment.currentItemMenu)
        }
    }

    @Test
    fun fragment_testOnDestroy() {
        scenario.onFragment { fragment ->
            fragment.onDestroyView()
            Assert.assertNull(fragment._vb)
        }
    }

    @Test
    fun fragment_testProgressCircular() {
        scenario.onFragment { fragment ->
            fragment.progressCircleVisible()
        }
        assertion = matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
        onView(withId(R.id.progress_circular)).check(assertion)

        scenario.onFragment { fragment ->
            fragment.progressCircleGone()
        }
        assertion = matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        onView(withId(R.id.progress_circular)).check(assertion)

    }

    @Test
    fun fragment_testAllTexts() {
        onView(ViewMatchers.isRoot()).perform(delay())
        assertion = getAssertion("Roast fennel and aubergine paella")
        onView(withId(R.id.name_meal)).check(assertion)
        assertion = getAssertion("Spanish")
        onView(withId(R.id.area_meal)).check(assertion)
        onView(withId(R.id.instruction)).check(matches(not(withText(""))))
        onView(withId(R.id.ingredients)).check(matches(not(withText(""))))

    }

    private fun getAssertion(string: String) = matches(withText(string))
}
