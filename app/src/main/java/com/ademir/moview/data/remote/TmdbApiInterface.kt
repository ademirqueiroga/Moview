package com.ademir.moview.data.remote

import com.ademir.moview.BuildConfig
import com.ademir.moview.commons.Constants
import com.ademir.moview.data.models.Movie
import com.ademir.moview.data.models.Video
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbApiInterface {

    @GET(Constants.Urls.POPULAR_MOVIES)
    fun getPopular(@Query("page") page: Int = 1,
                   @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<Movie.Payload>

    @GET(Constants.Urls.MOVIE_DETAILS)
    fun getMovie(@Path("pk") id: String,
                 @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Single<Movie>

    @GET(Constants.Urls.MOVIE_VIDEOS)
    fun getVideos(@Path("pk") id: String,
                  @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Single<Video.Payload>

}
