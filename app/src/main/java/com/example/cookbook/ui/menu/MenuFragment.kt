package com.example.cookbook.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.application.App
import com.example.cookbook.databinding.FragmentMenuBinding
import com.example.cookbook.di.di_menu.MenuSubcomponent
import com.example.cookbook.domain.entity.entity_categories.Category
import com.example.cookbook.domain.presenters.menu_fragment_presenters.MenuPresenter
import com.example.cookbook.domain.utils.Extensions
import com.example.cookbook.domain.view.CategoriesView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val CURRENT_CATEGORY = "current_category"

class MenuFragment : MvpAppCompatFragment(), CategoriesView {

    lateinit var currentCategory: Category

    companion object {
        fun newInstance(bundle: Bundle): MenuFragment {
            val fragment = MenuFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    var _vb: FragmentMenuBinding? = null
    val vb
        get() = _vb!!

    private var menuSubcomponent: MenuSubcomponent? = null
    val presenter: MenuPresenter by moxyPresenter {

        menuSubcomponent = App.instance.initMenuSubcomponent()
        MenuPresenter().apply {
            menuSubcomponent?.inject(this)
        }
    }

    var adapter: MenuRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentCategory = (arguments?.getParcelable(CURRENT_CATEGORY) as Category?)!!
        _vb = FragmentMenuBinding.inflate(inflater, container, false)

        return vb.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }


    override fun init() {
        vb.apply {
            menu.layoutManager = LinearLayoutManager(context)
            currentCategory.let { presenter.loadMenu(it) }
            adapter = MenuRVAdapter(presenter.menuListPresenter).apply {
                menuSubcomponent?.inject(this)
            }
            vb.menu.adapter = adapter
        }
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        menuSubcomponent = null
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
