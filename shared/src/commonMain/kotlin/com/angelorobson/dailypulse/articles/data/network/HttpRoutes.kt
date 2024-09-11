package com.angelorobson.dailypulse.articles.data.network

data object HttpRoutes {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val COUNTRY = "us"
    private const val CATEGORY = "business"
    const val GET_TOP_HEADLINES =
        "${BASE_URL}/top-headlines?country=$COUNTRY&category=$CATEGORY&apiKey={API_KEY}"

    const val GET_SOURCES = "${BASE_URL}/top-headlines/sources?apiKey={API_KEY}"
}