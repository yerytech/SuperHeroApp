package com.yerytech.superhero

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.yerytech.superhero.databinding.ActivityHomeBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var retrofit:Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit=getRetrofit()
       initUI()
    }

    private fun initUI() {
        binding.srcView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                serchByName(query.orEmpty())
                return false
            }



            override fun onQueryTextChange(newText: String?)=false


        })
    }

    private fun serchByName(query: String) {


    }
    private fun getRetrofit():Retrofit {
        return  Retrofit.Builder()
           .baseUrl("https://superheroapi.com/api/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()


    }


}