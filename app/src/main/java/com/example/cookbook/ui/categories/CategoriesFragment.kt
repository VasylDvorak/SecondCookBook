package com.example.cookbook.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.application.App
import com.example.cookbook.databinding.FragmentCategoriesBinding
import com.example.cookbook.di.di_categories.CategoriesSubcomponent
import com.example.cookbook.domain.presenters.categories_presenters.CategoriesPresenter
import com.example.cookbook.domain.view.CategoriesView
import com.example.cookbook.ui.main_activity.interfaces.BackButtonListener
import com.example.cookbook.domain.utils.Extensions
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CategoriesFragment : MvpAppCompatFragment(), CategoriesView {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private var categoriesSubcomponent: CategoriesSubcomponent? = null

    private val presenter: CategoriesPresenter by moxyPresenter {
        categoriesSubcomponent = App.instance.initCategorySubcomponent()
        CategoriesPresenter().apply {
            categoriesSubcomponent?.inject(this)
        }

    }
    var adapter: CategoriesRVAdapter? = null
    private var vb: FragmentCategoriesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentCategoriesBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.apply {
            categories.layoutManager = LinearLayoutManager(context)
            adapter = CategoriesRVAdapter(presenter.categoriesListPresenter).apply {
                categoriesSubcomponent?.inject(this)
            }
            vb?.categories?.adapter = adapter
        }
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        categoriesSubcomponent = null
        App.instance.releaseMenuSubComponent()
    }

    override fun progressCircleGone() {
        vb?.progressCircular?.visibility = View.GONE
    }

    override fun progressCircleVisible() {
        vb?.progressCircular?.visibility = View.VISIBLE
    }


    override fun showToastFragment() {
        Extensions().showToast()
    }


}
