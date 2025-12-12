package com.gallery.view.domain.model

import java.time.LocalDateTime

// Main response class
data class AnimeResponse(
    val pagination: Pagination,
    val data: List<AnimeData>
)

// Pagination information
data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: ItemsInfo
)

data class ItemsInfo(
    val count: Int,
    val total: Int,
    val per_page: Int
)

// Main anime data
data class AnimeData(
    val mal_id: Int,
    val url: String,
    val images: Images?=null,
    val trailer: Trailer?=null,
//    val approved: Boolean,
//    val titles: List<Title>,
    val title: String?=null,
//    val title_english: String?,
//    val title_japanese: String,
//    val title_synonyms: List<String>,
//    val type: String,
//    val source: String,
    val episodes: Int?=null,
//    val status: String,
//    val airing: Boolean,
//    val aired: AiredInfo,
//    val duration: String,
//    val rating: String?,
    val score: Double?=null,
//    val scored_by: Int,
//    val rank: Int,
//    val popularity: Int,
//    val members: Int,
//    val favorites: Int,
    val synopsis: String,
//    val background: String?,
//    val season: String?,
//    val year: Int?,
//    val broadcast: BroadcastInfo,
//    val producers: List<Producer>,
//    val licensors: List<Licensor>,
//    val studios: List<Studio>,
    val genres: List<Genre>,
//    val explicit_genres: List<Genre>,
//    val themes: List<Theme>,
//    val demographics: List<Demographic>
)

// Image data
data class Images(
    val jpg: ImageUrls,
    val webp: ImageUrls
)

data class ImageUrls(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

// Trailer data
data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?,
    val images: TrailerImages
)

data class TrailerImages(
    val image_url: String?,
    val small_image_url: String?,
    val medium_image_url: String?,
    val large_image_url: String?,
    val maximum_image_url: String?
)

// Title information
data class Title(
    val type: String,
    val title: String
)

// Aired information with dates
data class AiredInfo(
    val from: LocalDateTime?,
    val to: LocalDateTime?,
    val prop: DateProp,
    val string: String
)

data class DateProp(
    val from: DatePropDetail,
    val to: DatePropDetail
)

data class DatePropDetail(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

// Broadcast information
data class BroadcastInfo(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
)

// Producer, Licensor, Studio, Genre, Theme, and Demographic share similar structure
data class Producer(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Licensor(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Studio(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Theme(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Demographic(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)