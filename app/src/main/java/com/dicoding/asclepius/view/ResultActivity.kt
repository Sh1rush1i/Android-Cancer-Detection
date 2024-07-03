package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.data.History
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private var binding: ActivityResultBinding? = null

    private val history = History()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val factory = ResultVMFactory.getInstance(application)
        val viewModel: ResultVM = ViewModelProvider(this,factory)[ResultVM::class.java]

        binding?.saveButton?.setOnClickListener {
            viewModel.insert(history)
            Toast.makeText(this, "Berhasil menyimpan klasifikasi", Toast.LENGTH_SHORT).show()
        }

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val hasil = intent.getStringExtra(EXTRA_RESULT)
        val prediksi = intent.getStringExtra(EXTRA_PREDICT)
        val nilai = intent.getStringExtra(EXTRA_SCORE)
        val data = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(data)

        history.image = data
        history.prediction = prediksi
        history.score = nilai

        runOnUiThread {
            imageUri?.let {
                binding?.resultImage?.setImageURI(it)
                binding?.resultText?.text = hasil
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_PREDICT = "extra_predict"
        const val EXTRA_SCORE = "extra_score"
    }

}