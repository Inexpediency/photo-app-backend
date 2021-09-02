package com.ythosa.photoapp.api.users.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.ythosa.photoapp.api.users.service.UsersService
import com.ythosa.photoapp.api.users.ui.model.LoginRequestModel
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val usersService: UsersService,
    private val authenticationProps: AuthenticationProps
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(request.inputStream, LoginRequestModel::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentials.email, credentials.password
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val username = (authResult.principal as User).username
        val userDetails = usersService.getUserDetailsByEmail(username)

        val accessToken = Jwts.builder()
            .setSubject(userDetails.userId)
            .setExpiration(Date(System.currentTimeMillis() + authenticationProps.accessTokenTtl))
            .signWith(SignatureAlgorithm.HS512, authenticationProps.accessTokenSecret)
            .compact()
        val refreshToken = Jwts.builder()
            .setSubject(userDetails.userId)
            .setExpiration(Date(System.currentTimeMillis() + authenticationProps.refreshTokenTtl))
            .signWith(SignatureAlgorithm.HS512, authenticationProps.refreshTokenSecret)
            .compact()

        response.addHeader("access-token", accessToken)
        response.addHeader("refresh-token", refreshToken)

        println("signing key: " + authenticationProps.accessTokenSecret)
    }
}
