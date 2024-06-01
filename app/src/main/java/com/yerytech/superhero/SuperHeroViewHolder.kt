package com.yerytech.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yerytech.superhero.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
  private val binding=ItemSuperheroBinding.bind(view)
  fun bind(superHeroItemResponse:SuperHeroItemsResponse,onItemSelected:(String)->Unit){
    binding.tvSuperHeroName.text=superHeroItemResponse.superHeroName
    binding.ivSuperHero
    Picasso.get().load(superHeroItemResponse.superHeroImage.url).into(binding.ivSuperHero)
    binding.root.setOnClickListener{onItemSelected(superHeroItemResponse.superHeroId) }

  }
}