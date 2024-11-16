package com.dicoding.mysubmission.ui.activity


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.mysubmission.R
import com.dicoding.mysubmission.data.remote.response.Story
import com.dicoding.mysubmission.databinding.ActivityDetailBinding
import com.dicoding.mysubmission.pref.SessionManager
import com.dicoding.mysubmission.ui.viewmodel.DetailViewModel
import com.dicoding.mysubmission.ui.viewmodelfactory.DetailFactory
import com.dicoding.mysubmission.data.Result

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var sessionManager: SessionManager
    private val detailViewModel: DetailViewModel by viewModels {
        DetailFactory.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        sessionManager = SessionManager(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val storyId = intent.getStringExtra("STORY_ID")
        val token = sessionManager.getAuthToken()

        if (token != null && storyId != null) {
            detailViewModel.getDetail(token, storyId)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
        detailViewModel.detail.observe(this) { result ->
            result?.let { setDetailData(it) }
        }
    }

    private fun setDetailData(data: Result<Story>) {
        if (data is Result.Success) {
            val story = data.data
            binding.nameDetail.text = story.name
            binding.descDetail.text = story.description

            Glide.with(this)
                .load(story.photoUrl)
                .into(binding.imgDetail)
        } else if (data is Result.Error) {
            Toast.makeText(this, data.error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}