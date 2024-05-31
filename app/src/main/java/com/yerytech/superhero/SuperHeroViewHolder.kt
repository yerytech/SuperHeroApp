package com.yerytech.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yerytech.superhero.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
  private val binding=ItemSuperheroBinding.bind(view)
  fun bind(superHeroItemResponse:SuperHeroItemsResponse){
    binding.tvsuperHeroName.text=superHeroItemResponse.superHeroName
  }
}