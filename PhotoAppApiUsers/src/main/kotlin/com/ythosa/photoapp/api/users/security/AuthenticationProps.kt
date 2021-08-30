package com.ythosa.photoapp.api.users.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "authentication")
data class AuthenticationProps(
    val loginUrl: String,
    val accessTokenSecret: String,
    val accessTokenTtl: Long,
    val refreshTokenSecret: String,
    val refreshTokenTtl: Long
)
