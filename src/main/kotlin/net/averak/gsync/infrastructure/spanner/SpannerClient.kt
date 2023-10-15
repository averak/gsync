package net.averak.gsync.infrastructure.spanner

import com.google.cloud.spanner.Spanner
import jakarta.inject.Singleton

@Singleton
class SpannerClient(
    private val spanner: Spanner,
)
