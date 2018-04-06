package com.ademir.mooview.data.models;

import com.squareup.moshi.Json;

/**
 * Created by ademir on 13/03/17.
 */

public class Comment {

    @Json(name = "user")
    public int userId;
    public String username;
    @Json(name = "movie")
    public int movieId;
    @Json(name = "movie_title")
    public String movieTitle;
    public String content;
    public int likes;
    @Json(name = "created_at")
    public long createdAt;

}
