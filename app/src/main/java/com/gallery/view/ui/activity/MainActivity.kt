package com.gallery.view.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gallery.view.databinding.ActivityMainBinding
import com.gallery.view.domain.model.AnimeData
import com.gallery.view.ui.adapter.AnimeAdapter
import com.gallery.view.ui.viewmodel.ApiSource
import com.gallery.view.ui.viewmodel.GalleryViewModel
import com.gallery.view.ui.viewmodel.UiState
import com.gallery.view.utils.IntentConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvAnime.layoutManager = LinearLayoutManager(this)
        applyInsets(binding.root)
        setupObservers()

    }

    private fun setupObservers() {
        binding.shimmerContacts.startShimmer()
        binding.shimmerContacts.visibility = View.VISIBLE
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    handleUiState(state)
                }
            }
        }
    }

    private fun handleUiState(state: UiState) {

        // Handle API responses
        when (state.apiSource) {
            ApiSource.GET_ANIME_LIST -> {
                state.animeList.let {
                    if (it.isNotEmpty()) {
                        binding.tvError.visibility = View.GONE
                        binding.shimmerContacts.stopShimmer()
                        binding.shimmerContacts.visibility = View.GONE
                    }
                    binding.rvAnime.adapter = AnimeAdapter(state.animeList) { anime ->
                        openDetail(anime)
                    }
                }
                state.error.let {
                    binding.tvError.visibility = View.VISIBLE
                }
            }

            else -> {}
        }
    }

    private fun openDetail(anime: AnimeData) {
        val intent = Intent(this, AnimeDetailActivity::class.java)
        intent.putExtra(IntentConstant.ANIME_ID, anime.mal_id)
        startActivity(intent)
    }

    private fun applyInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }
    }
}
