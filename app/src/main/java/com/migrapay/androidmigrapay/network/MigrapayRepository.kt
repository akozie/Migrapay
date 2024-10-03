package com.migrapay.androidmigrapay.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MigrapayRepository
    @Inject
    constructor(
        private val migrapayService: MigrapayService,
    ) {
        private fun getAllCountries() = migrapayService.getAllCountries()
    }
