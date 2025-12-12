package com.gallery.view.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.gallery.view.R
import com.gallery.view.databinding.ActivityAnimeDetailBinding
import com.gallery.view.domain.model.AnimeData
import com.gallery.view.ui.viewmodel.AnimeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.gallery.view.utils.IntentConstant
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.delay

@AndroidEntryPoint
class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeDetailBinding
    private val viewModel: AnimeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeId = intent.getIntExtra(IntentConstant.ANIME_ID, -1)

        if (animeId == -1) {
            finish()
            return
        }

        viewModel.fetchAnimeList(animeId)

        observeData()
    }

    private fun extractYoutubeId(embedUrl: String?): String? {
        if (embedUrl.isNullOrEmpty()) return null
        return embedUrl.substringAfter("embed/").substringBefore("?")
    }

    @SuppressLint("SetTextI18n")
    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->

                    state.animeData?.let { anime ->
                        setUI(anime)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUI(anime: AnimeData) {

        binding.tvTitle.text = anime.title

        binding.tvEpisodes.text = getString(R.string.episodes) + " " + (anime.episodes ?: "N/A")

        binding.tvRating.text = getString(R.string.rating) + " " + (anime.score ?: "N/A")

        binding.tvGenres.text = getString(R.string.genres) + " " +
                anime.genres.joinToString { it.name }

        binding.tvSynopsis.text = anime.synopsis


        Glide.with(this@AnimeDetailActivity)
            .load(anime.images?.jpg?.large_image_url)
            .placeholder(R.drawable.placeholder)   // Show while loading
            .error(R.drawable.placeholder)         // Show if loading fails
            .into(binding.imgPoster)




        // Trailer handling
        val youtubeId = extractYoutubeId(anime.trailer?.embed_url)

        if (youtubeId != null) {
            binding.imgPoster.visibility = View.GONE
            binding.youtubePlayer.visibility = View.VISIBLE

            lifecycle.addObserver(binding.youtubePlayer)

            binding.youtubePlayer.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {

                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(youtubeId, 0f)
                }

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerError
                ) {

                    lifecycleScope.launch {
                        delay(1000)
                        // Hide YouTube player
                        binding.youtubePlayer.visibility = View.GONE

                        // Show poster
                        binding.imgPoster.visibility = View.VISIBLE
                    }

                }
            })

        } else {
            binding.youtubePlayer.visibility = View.GONE
            binding.imgPoster.visibility = View.VISIBLE
        }
    }


}
