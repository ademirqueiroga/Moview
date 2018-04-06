package com.ademir.mooview.data.remote

import com.ademir.mooview.commons.Constants
import com.ademir.mooview.data.models.Comment
import com.ademir.mooview.data.models.Movie
import com.ademir.mooview.data.remote.payloads.FollowPayload
import com.ademir.mooview.data.remote.payloads.MoviePayload
import com.ademir.mooview.data.remote.payloads.SearchPayload
import com.ademir.mooview.data.remote.payloads.UserPayload
import com.ademir.moview.model.FeedPost
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiInterface {

    @GET(Constants.Urls.SEARCH)
    fun search(@Header("Authorization") token: String, @Query("search") query: String): Observable<SearchPayload>

    @GET(Constants.Urls.FEED)
    fun getFeed(@Header("Authorization") token: String, @Query("all") all: String): Observable<List<FeedPost>>

    @GET(Constants.Urls.MOVIES)
    fun getMovies(): Observable<MoviePayload>

    @GET(Constants.Urls.MOVIE_DETAILS)
    fun getMovieDetails(@Header("Authorization") token: String,
                        @Path("id") id: Int): Observable<Movie>

    @GET(Constants.Urls.MOVIE_COMMENTS)
    fun getMovieComments(@Header("Authorization") token: String,
                         @Query("id") movieId: Int,
                         @Query("following") onlyFollowing: String): Observable<List<Comment>>

    @FormUrlEncoded
    @POST(Constants.Urls.MOVIE_COMMENTS)
    fun sendComment(@Header("Authorization") token: String,
                    @Field("id") movieId: Int,
                    @Field("content") content: String): Observable<Comment>

    @FormUrlEncoded
    @POST(Constants.Urls.AUTH)
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<UserPayload>

    @FormUrlEncoded
    @POST(Constants.Urls.SIGN_UP)
    fun signUp(@Field("username") username: String, @Field("email") email: String,
                        @Field("password") password: String, @Field("first_name") firstName: String,
                        @Field("last_name") lastName: String): Observable<UserPayload>

    @FormUrlEncoded
    @POST(Constants.Urls.FOLLOW)
    fun follow(@Header("Authorization") token: String, @Field("user_id") id: Int): Observable<FollowPayload>

    @GET(Constants.Urls.USER_COMMENTS)
    fun getMyComments(@Header("Authorization") token: String): Observable<List<Comment>>

    @GET(Constants.Urls.USER_COMMENTS)
    fun getUserComments(@Header("Authorization") token: String, @Query("id") id: Int): Observable<List<Comment>>

    @GET(Constants.Urls.FAVORIRES)
    fun getFavorites(@Header("Authorization") token: String): Observable<MoviePayload>

    @GET(Constants.Urls.WATCHLIST)
    fun getWatchlist(@Header("Authorization") token: String): Observable<MoviePayload>

    @FormUrlEncoded
    @POST(Constants.Urls.FAVORIRES)
    fun addToFavorites(@Header("Authorization") token: String, @Field("movie_id") movieId: Int): Observable<ResponseBody>

    @FormUrlEncoded
    @POST(Constants.Urls.WATCHLIST)
    fun addToWatchlist(@Header("Authorization") token: String, @Field("movie_id") movieId: Int): Observable<ResponseBody>

}
