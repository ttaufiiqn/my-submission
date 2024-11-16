package com.dicoding.mysubmission.ui.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.mysubmission.R
import com.dicoding.mysubmission.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        playAnimation()
        binding.btnStart.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {


        val title = ObjectAnimator.ofFloat(binding.txtWelcome, View.ALPHA, 1f).setDuration(1500)
        val desc = ObjectAnimator.ofFloat(binding.txtDescWelcome, View.ALPHA, 1f).setDuration(1500)
        val btn = ObjectAnimator.ofFloat(binding.btnStart, View.ALPHA, 1f).setDuration(1500)
        val img = ObjectAnimator.ofFloat(binding.imgWelcome, View.ALPHA, 1f).setDuration(1500)


        AnimatorSet().apply {
            playSequentially(title, img, desc, btn)
            start()
        }
    }


}