package com.hariselfian.harisrental.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.hariselfian.harisrental.MainActivity
import com.hariselfian.harisrental.R
import com.hariselfian.harisrental.activity.DetailProdukActivity
import com.hariselfian.harisrental.activity.LoginActivity
import com.hariselfian.harisrental.helper.Helper
import com.hariselfian.harisrental.model.Produk
import com.hariselfian.harisrental.room.MyDatabase
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterKeranjang(var activity: Activity, var data: ArrayList<Produk>) :
    RecyclerView.Adapter<AdapterKeranjang.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val imgProduk = view.findViewById<ImageView>(R.id.img_produk)
        val layout = view.findViewById<CardView>(R.id.layout)

        val btnTambah = view.findViewById<ImageView>(R.id.btn_tambah)
        val btnKurang = view.findViewById<ImageView>(R.id.btn_kurang)
        val btnDelete = view.findViewById<ImageView>(R.id.btn_delete)

        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        val tvJumlah = view.findViewById<TextView>(R.id.tv_jumlah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val produk = data[position]

        holder.tvNama.text = produk.name
        holder.tvHarga.text = Helper().gantiRupiah(produk.harga)

        var jumlah = data[position].jumlah


        holder.tvJumlah.text = jumlah.toString()
        val image =
            "http://192.168.43.44/haris_rental/public/storage/produk/" + data[position].image
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.product)
            .error(R.drawable.product)
            .into(holder.imgProduk)

        holder.btnTambah.setOnClickListener {
            jumlah++
            produk.jumlah = jumlah
            update(produk)
            holder.tvJumlah.text = jumlah.toString()
        }

        holder.btnKurang.setOnClickListener {
            if (jumlah <= 1) return@setOnClickListener
            jumlah--

            produk.jumlah = jumlah
            update(produk)
            holder.tvJumlah.text = jumlah.toString()
        }

        holder.btnDelete.setOnClickListener {
            notifyItemRemoved(position)
            delete(produk)
            notifyDataSetChanged()
        }
    }

    private fun update(data: Produk) {
        val myDb = MyDatabase.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.daoKeranjang().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            })
    }

    private fun delete(data: Produk) {
        val myDb = MyDatabase.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.daoKeranjang().delete(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            })
    }
}