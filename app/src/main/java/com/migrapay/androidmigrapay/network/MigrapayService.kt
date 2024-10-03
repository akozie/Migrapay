package com.migrapay.androidmigrapay.network

import com.migrapay.androidmigrapay.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface MigrapayService {
    @GET("all")
    fun getAllCountries(): Response<List<Country>>
}
