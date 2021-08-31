package com.ythosa.photoapp.api.apigateway

import io.jsonwebtoken.Jwts
import org.apache.http.HttpHeaders
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(
    private val authorizationHeaderFilterProps: AuthorizationHeaderFilterProps
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    class Config

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request

            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return@GatewayFilter onError(exchange)
            }

            val jwtToken = request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
                ?.replace("Bearer", "")
                ?: return@GatewayFilter onError(exchange)

            if (!isJwtValid(jwtToken)) {
                return@GatewayFilter onError(exchange)
            }

            return@GatewayFilter chain.filter(exchange)
        }
    }

    private fun onError(exchange: ServerWebExchange): Mono<Void> {
        val response = exchange.response
        response.statusCode = HttpStatus.UNAUTHORIZED

        return response.setComplete()
    }

    private fun isJwtValid(jwtToken: String): Boolean {
        val subject: String?

        try {
            subject = Jwts.parser()
                .setSigningKey(authorizationHeaderFilterProps.accessTokenSecret)
                .parseClaimsJws(jwtToken)
                .body
                .subject
        } catch (e: Exception) {
            return false
        }

        if (subject == null || subject.isEmpty()) {
            return false
        }

        return true
    }
}
