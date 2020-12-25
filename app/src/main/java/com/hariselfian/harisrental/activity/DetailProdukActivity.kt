package com.hariselfian.harisrental.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.hariselfian.harisrental.R
import com.hariselfian.harisrental.helper.Helper
import com.hariselfian.harisrental.model.Produk
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_produk.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailProdukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        getInfo()
    }

    fun getInfo() {
        val data = intent.getStringExtra("extra")
        val produk = Gson().fromJson<Produk>(data, Produk::class.java)

        // set value
        tv_nama.text = produk.name
        tv_harga.text = Helper().gantiRupiah(produk.harga)
        tv_deskripsi.text = produk.deskripsi

        val img = "http://192.168.43.121/haris_rental/public/storage/produk/" + produk.image
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.product)
            .error(R.drawable.product)
            .resize(400, 400)
            .into(image)

        // setToolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = produk.name
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}