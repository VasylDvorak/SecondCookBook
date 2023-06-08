package com.example.cookbook.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.databinding.ItemCategoryBinding
import com.example.cookbook.domain.image.IImageLoader
import com.example.cookbook.domain.presenters.categories_presenters.ICategoryListPresenter
import com.example.cookbook.domain.view.CategoryItemView
import javax.inject.Inject

class CategoriesRVAdapter(val presenter: ICategoryListPresenter) :
    RecyclerView.Adapter<CategoriesRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })


    inner class ViewHolder(val vb: ItemCategoryBinding) :
        RecyclerView.ViewHolder(vb.root), CategoryItemView {
        override var pos = -1
        override fun setName(text: String) = with(vb) {
            courseName.text = text
        }


        override fun loadPicture(url: String) {
            imageLoader.loadInto(url, vb.coursePicture)
        }

    }
}

