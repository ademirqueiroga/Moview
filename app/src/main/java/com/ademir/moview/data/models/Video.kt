package com.ademir.moview.data.models

data class Video(var id: String,
                 var key: String,
                 var name: String) {

    class Payload(val results: List<Video>)

}