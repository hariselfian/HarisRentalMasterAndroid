package com.hariselfian.harisrental.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.hariselfian.harisrental.R
import com.hariselfian.harisrental.adapter.AdapterProduk
import com.hariselfian.harisrental.adapter.AdapterSlider
import com.hariselfian.harisrental.model.Produk

/**
 * Kode dibuat dengan cinta oleh Haris Elfian.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvProduk: RecyclerView
    lateinit var rvJasa: RecyclerView
    lateinit var rvElektronik: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvJasa = view.findViewById(R.id.rv_jasa)
        rvElektronik = view.findViewById(R.id.rv_elektronik)

        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider1)
        arrSlider.add(R.drawable.slider2)
        arrSlider.add(R.drawable.slider3)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3 = LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(arrayProduk)
        rvProduk.layoutManager = layoutManager

        rvJasa.adapter = AdapterProduk(arrayJasa)
        rvJasa.layoutManager = layoutManager2

        rvElektronik.adapter = AdapterProduk(arrayElektronik)
        rvElektronik.layoutManager = layoutManager3

        return view
    }

    val arrayProduk: ArrayList<Produk>get(){
        val arr = ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "Kebaya Buat Wisuda"
        p1.harga = "Rp.70.000/Jam"
        p1.gambar = R.drawable.rental_kebaya

        val p2 = Produk()
        p2.nama = "Rental Play Station 4"
        p2.harga = "Rp.5.000/Jam"
        p2.gambar = R.drawable.rental_ps

        val p3 = Produk()
        p3.nama = "Kamera Buat Hunting"
        p3.harga = "Rp.50.000/Jam"
        p3.gambar = R.drawable.rental_kamera

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrayJasa: ArrayList<Produk>get(){
        val arr = ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "Kebaya Buat Wisuda"
        p1.harga = "Rp.70.000/Jam"
        p1.gambar = R.drawable.rental_kebaya

        val p2 = Produk()
        p2.nama = "Rental Play Station 4"
        p2.harga = "Rp.5.000/Jam"
        p2.gambar = R.drawable.rental_ps

        val p3 = Produk()
        p3.nama = "Kamera Buat Hunting"
        p3.harga = "Rp.50.000/Jam"
        p3.gambar = R.drawable.rental_kamera

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }

    val arrayElektronik: ArrayList<Produk>get(){
        val arr = ArrayList<Produk>()
        val p1 = Produk()
        p1.nama = "Kebaya Buat Wisuda"
        p1.harga = "Rp.70.000/Jam"
        p1.gambar = R.drawable.rental_kebaya

        val p2 = Produk()
        p2.nama = "Rental Play Station 4"
        p2.harga = "Rp.5.000/Jam"
        p2.gambar = R.drawable.rental_ps

        val p3 = Produk()
        p3.nama = "Kamera Buat Hunting"
        p3.harga = "Rp.50.000/Jam"
        p3.gambar = R.drawable.rental_kamera

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }
}