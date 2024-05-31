package com.yerytech.superhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(private var superHeroList: List<SuperHeroItemsResponse> = emptyList()) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

        fun updateList(superHeroList: List<SuperHeroItemsResponse>){
            this.superHeroList=superHeroList
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount() = superHeroList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superHeroList[position])
    }


}


