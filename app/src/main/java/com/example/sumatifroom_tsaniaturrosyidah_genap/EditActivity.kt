package com.example.sumatifroom_tsaniaturrosyidah_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.Constant
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.codepelita
import com.example.sumatifroom_tsaniaturrosyidah_genap.room.tbbarang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy {codepelita(this)}
    private var unik: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
        setupView()
        unik = intent.getIntExtra("intent_id", unik)

    }
    fun setupView() {
        val intentType = intent.getIntExtra("intent_type", 0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnUpdate.visibility = View.GONE
                btnSimpan.visibility = View.GONE
                munculkan()
            }
            Constant.TYPE_UPDATE -> {
                btnSimpan.visibility = View.GONE
                munculkan()
            }
        }
    }

    private fun setupListener() {
        btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tokoDAO().addtbbarang(
                    tbbarang(
                        et_idBarang.text.toString().toInt(),
                        et_nmBarang.text.toString(),
                        et_hrg_brg.text.toString().toInt(),
                        et_qtyBrg.text.toString().toInt()
                    )
                )
                finish()
            }
        }

        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tokoDAO().updatetbbarang(
                    tbbarang(
                        et_idBarang.text.toString().toInt(),
                        et_nmBarang.text.toString(),
                        et_hrg_brg.text.toString().toInt(),
                        et_qtyBrg.text.toString().toInt()
                    )
                )
                finish()
            }
        }
    }
    fun munculkan() {
        unik = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val mybenda = db.tokoDAO().munculkan(unik)[0]
            val dataId: String = mybenda.id.toString()
            val qtybarang: String = mybenda.quantity.toString()
            val price: String = mybenda.harga.toString()
            et_idBarang.setText(dataId)
            et_nmBarang.setText(mybenda.nama)
            et_hrg_brg.setText(price)
            et_qtyBrg.setText(qtybarang)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}