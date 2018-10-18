package com.bbonglish.bb.ui.edit

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bbonglish.bb.R
import com.bbonglish.bb.api.model.EditRepo
import com.bbonglish.bb.ui.GlideApp
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_edit.view.*

class EditAdapter : RecyclerView.Adapter<EditAdapter.RepositoryHolder>() {

    private var items: MutableList<EditRepo> = mutableListOf()

    private val placeholder = ColorDrawable(Color.GRAY)

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = RepositoryHolder(parent)

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        items[position].let { repo ->
            with(holder.itemView) {
                GlideApp.with(context)
                        .load(repo.image)
                        .placeholder(placeholder)
                        .apply(RequestOptions().circleCrop()) // glide 이미지를 원형으로...
                        .into(tutorImage)

                title.text = repo.name
                regDate.text = repo.name
                setOnClickListener { listener?.onItemClick(repo) }
            }
        }
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<EditRepo>) {
        this.items = items.toMutableList()
    }

    fun setItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    fun clearItems() {
        this.items.clear()
    }

    class RepositoryHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_edit, parent, false))

    interface ItemClickListener {

        fun onItemClick(repository: EditRepo)
    }
}
