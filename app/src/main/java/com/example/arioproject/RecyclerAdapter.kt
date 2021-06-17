package com.example.arioproject

import android.util.Log
import android.view.*
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class RecyclerAdapter : RecyclerView.Adapter<StoryHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder {
        val v: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.story_row, parent, false)
        return StoryHolder(v)
    }

    override fun onBindViewHolder(holder: StoryHolder, position: Int) {
        val item = APIHandler.stories[position];
        holder.bind(item.title)
    }

    override fun getItemCount(): Int {
        return APIHandler.stories.size
    }

}

class StoryHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private var storyTitleView: TextView? = null


    init {
        storyTitleView = itemView.findViewById(R.id.itemTitle)
    }


    override fun onClick(v: View?) {
        //Open story in Webview or something if we want
    }

    fun bind(title: String) {
        //Safe unwrap and assign or just skip
        storyTitleView?.text = title;
    }

}