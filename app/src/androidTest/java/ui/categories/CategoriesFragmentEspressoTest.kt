package ui.categories

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cookbook.R
import com.example.cookbook.ui.categories.CategoriesFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CategoriesFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<CategoriesFragment>
    private lateinit var assertion: ViewAssertion

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun fragment_testPresenter() {
        scenario.onFragment { fragment ->
            Assert.assertNotNull(fragment.presenter)
        }

    }

    @Test
    fun fragment_testOnDestroy() {
        scenario.onFragment { fragment ->
            fragment.onDestroyView()
            Assert.assertNull(fragment.vb)
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
}
