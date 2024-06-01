package com.yerytech.superhero

import android.content.Intent
import android.os.Bundle
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.tracing.perfetto.handshake.protocol.Response
import com.yerytech.superhero.DetailSuperHeroActivity.Companion.HERO_ID
import com.yerytech.superhero.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.srcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                searchByName(query.orEmpty())
                return false
            }


            override fun onQueryTextChange(newText: String?) = false

        })

        adapter= SuperHeroAdapter(){id->  navigateToDetails(id)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager=LinearLayoutManager(this)
        binding.rvSuperHero.adapter=adapter
    }


    @OptIn(UnstableApi::class)
    private fun searchByName(query: String) {
        binding.circularProgress.isVisible = true


        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: retrofit2.Response<SuperHeroResponse> =
                retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful) {
                val response: SuperHeroResponse? = myResponse.body()


                if (response != null) {

                    runOnUiThread {
                        adapter.updateList(response.superHero)
                        binding.circularProgress.isVisible = false
                    }

                } else {
                    Log.i("mnm", "no funciona")

                }


            } else {
                Log.i("mnm", "nada")

            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }

    private fun navigateToDetails(id:String){
        val intent=Intent(this,DetailSuperHeroActivity::class.java)
           intent.putExtra(HERO_ID,id)
        startActivity(intent)
    }




}