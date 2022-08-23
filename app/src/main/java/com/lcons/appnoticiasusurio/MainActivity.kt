package com.lcons.appnoticiasusurio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.lcons.appnoticiasusurio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recuperarNoticias()
    }

    private fun recuperarNoticias() {
        db.collection("noticias").document("noticia").get()
            .addOnCompleteListener { documento ->
                if (documento.isSuccessful){
                    val titulo = documento.result.get("titulo").toString()
                    val noticia = documento.result.get("noticia").toString()
                    val data = documento.result.get("data").toString()
                    val autor = documento.result.get("autor").toString()

                    binding.txtTitulo.text = titulo
                    binding.txtNoticia.text = noticia
                    binding.txtData.text = data
                    binding.txtAutor.text = autor
                }
            }
    }
}