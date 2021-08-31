package com.ythosa.photoapp.api.apigateway

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "authentication")
data class AuthorizationHeaderFilterProps(val accessTokenSecret: String)
