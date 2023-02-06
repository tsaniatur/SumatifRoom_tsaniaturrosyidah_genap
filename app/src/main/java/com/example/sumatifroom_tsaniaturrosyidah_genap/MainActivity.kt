package com.example.sumatifroom_tsaniaturrosyidah_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.Constant
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.codepelita
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.tbbarang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { codepelita(this) }
    lateinit var barangAdapter: BarangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpListener()
        setUpRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val stok = db.tokoDAO().muncul()
            Log.d("MainActivity", "dbresponse:$stok")
            withContext(Dispatchers.Main) {
                barangAdapter.setData(stok)
            }
        }
    }
    fun setUpListener(){
        btnInput.setOnClickListener {
           intentEdit(0,Constant.TYPE_CREATE)
        }
    }
    fun intentEdit(unik: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", unik)
                .putExtra("intent_type", intentType)
        )
    }
    private fun setUpRecyclerView(){
        barangAdapter = BarangAdapter(arrayListOf(), object : BarangAdapter.OnAdapterListener {
            override fun onClick(brg: tbbarang) {
                intentEdit(brg.id, Constant.TYPE_READ)
            }

            override fun onUpdate(brg: tbbarang) {
                intentEdit(brg.id, Constant.TYPE_UPDATE)
            }

            override fun onDelete(brg: tbbarang) {
                hapusBuku(brg)
            }
        })
                rvlistbrg.apply {
                    layoutManager = LinearLayoutManager(applicationContext)
                    adapter = barangAdapter
                }
            }
        fun hapusBuku(brg: tbbarang){
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.apply {
                setTitle("Konfirmasi")
                setMessage("Yakin Hapus ${brg.nama}")
                setNegativeButton("Cancel") { dialogInterface, i -> dialogInterface.dismiss() }
                setPositiveButton("Ya") { dialogInterface, i ->
                    dialogInterface.dismiss()
                    CoroutineScope(Dispatchers.IO).launch {
                        db.tokoDAO().deletetbbarang(brg)
                        dialogInterface.dismiss()
                        loadData()
                    }
                }
            }
            alertDialog.show()
        }
    }