package ui.play_movie

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
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cookbook.R
import com.example.cookbook.application.App
import com.example.cookbook.ui.play_movie.PLAY_MOVIE
import com.example.cookbook.ui.play_movie.PlayMovieFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

val playMovieFragmentArgs = bundleOf(PLAY_MOVIE to "https://www.youtube.com/watch?v=H5SmjR-fxUs")

@RunWith(AndroidJUnit4::class)
class PlayMovieFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<PlayMovieFragment>
    private lateinit var assertion: ViewAssertion

    @Before
    fun setup() {

        App.instance.initCategorySubcomponent()
        App.instance.initMenuSubcomponent()
        App.instance.initRecipeSubcomponent()

        scenario = launchFragmentInContainer(playMovieFragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }


    @Test
    fun fragment_testOnDestroy() {
        scenario.onFragment { fragment ->
            fragment.onDestroyView()
            Assert.assertNull(fragment._binding)
        }
    }

    @Test
    fun fragment_testPlay() {
        assertion = matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
        onView(withId(R.id.web_view)).check(assertion)
        assertion = matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        onView(withId(R.id.empty_link)).check(assertion)
    }
}


