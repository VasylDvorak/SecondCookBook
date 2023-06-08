package com.example.cookbook.ui.recipe



import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.cookbook.application.App
import com.example.cookbook.di.di_recipe.RecipeSubcomponent
import com.example.cookbook.domain.image.IImageLoader
import com.example.cookbook.domain.entity.entity_menu.Menu
import com.example.cookbook.domain.entity.entity_recipe.Meal
import com.example.cookbook.ui.main_activity.interfaces.BackButtonListener
import com.example.cookbook.databinding.FragmentRecipeBinding
import com.example.cookbook.domain.presenters.RecipePresenter
import com.example.cookbook.domain.view.RecipeView
import com.example.cookbook.domain.utils.Extensions
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

const val CURRENT_RECIPE = "current_recipe"

class RecipeFragment : MvpAppCompatFragment(), RecipeView{

    private lateinit var currentItemMenu: Menu
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>


    companion object {
        fun newInstance(bundle: Bundle): RecipeFragment {
            val fragment = RecipeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _vb: FragmentRecipeBinding? = null
    private val vb
        get() = _vb!!

    private var recipeSubcomponent: RecipeSubcomponent? = null
    private val presenter: RecipePresenter by moxyPresenter {

        recipeSubcomponent = App.instance.initRecipeSubcomponent()
        RecipePresenter().apply {
            recipeSubcomponent?.inject(this)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentItemMenu = (arguments?.getParcelable(CURRENT_RECIPE) as Menu?)!!
        _vb = FragmentRecipeBinding.inflate(inflater, container, false)

        return vb.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }


    override fun init() { currentItemMenu.let { presenter.loadRecipe(it) } }


    override fun showRecipe(currentRecipe: Meal) {
            recipeSubcomponent?.inject(this)
        vb.apply {
            currentRecipe.strMealThumb?.let { imageLoader.loadInto(it, photoMeal) }
            nameMeal.text=currentRecipe.strMeal?:""
            areaMeal.text=currentRecipe.strArea?:""
            playMovie.setOnClickListener {
                presenter.playMovie()
            }
        }
    }

    override fun showToastFragment() {
        Extensions().showToast()
    }

    override fun showIngredients(text: SpannableStringBuilder) {
        vb.ingredients.text=text
    }

    override fun showInstruction(text: SpannableStringBuilder) {
        vb.instruction.text=text
    }

    override fun progressCircleGone() {
        vb?.progressCircular?.visibility = View.GONE
    }

    override fun progressCircleVisible() {
        vb?.progressCircular?.visibility = View.VISIBLE
    }


    override fun release() {
        recipeSubcomponent = null
        App.instance.releaseRecipeSubComponent()
    }



}

