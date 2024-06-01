package com.yerytech.superhero

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.yerytech.superhero.databinding.ActivityDetailSuperHeroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object{
        const val  HERO_ID="Hero_id"
    }


    private lateinit var  binding:ActivityDetailSuperHeroBinding
    private  lateinit var  retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id=intent.getStringExtra(HERO_ID).orEmpty()
        getSuperHeroInformation(id)

    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
          val superHeroDetail = getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if(superHeroDetail.body()!=null){

                runOnUiThread {
                   detailUi(superHeroDetail.body()!!)

                }

            }

        }

    }

    private fun detailUi(superHeroDetails: SuperHeroDetailResponse) {
          Picasso.get().load(superHeroDetails.image.url).into(binding.ivSuperHeroDetails)
        binding.tvSName.text=superHeroDetails.name
        heroStats(superHeroDetails.powerstats)
        binding.tvFullName.text=superHeroDetails.biography.fullName
        binding.tvAlterEgo.text=superHeroDetails.biography.alterEgos
        binding.tvAliases.text=superHeroDetails.biography.aliases[1]
    }

    private fun heroStats(powerStats: PowerStatsResponse) {
       updateParams(binding.ViewIntelligence,powerStats.intelligence)
       updateParams(binding.ViewCombat,powerStats.combat)
       updateParams(binding.ViewDurability,powerStats.durability)
       updateParams(binding.ViewPower,powerStats.power)
       updateParams(binding.ViewSpeed,powerStats.speed)
       updateParams(binding.ViewStrength,powerStats.strength)


    }
    private fun updateParams(view:View,stats:String){
         val params=view.layoutParams
        params.height=pixelUpdate(stats.toFloat())
        view.layoutParams=params
    }
    private fun pixelUpdate(px:Float):Int{
         return   TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).toInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }
}