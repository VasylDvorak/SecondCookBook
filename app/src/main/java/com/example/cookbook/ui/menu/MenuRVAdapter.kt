package com.example.cookbook.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.databinding.ItemMenuBinding
import com.example.cookbook.domain.image.IImageLoader
import com.example.cookbook.domain.repository.MenItemView
import com.example.cookbook.domain.presenters.menu_fragment_presenters.MenListPresenter
import javax.inject.Inject

class MenuRVAdapter(val presenter: MenListPresenter) :
    RecyclerView.Adapter<MenuRVAdapter.ViewHolder>() {


    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemMenuBinding.inflate(
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


    inner class ViewHolder(val vb: ItemMenuBinding) :
        RecyclerView.ViewHolder(vb.root), MenItemView {
        override var pos = -1
        override fun setName(text: String) = with(vb) {
            courseName.text = text
        }


        override fun loadPicture(url: String) {
            imageLoader.loadInto(url, vb.coursePicture)
        }

    }
}
