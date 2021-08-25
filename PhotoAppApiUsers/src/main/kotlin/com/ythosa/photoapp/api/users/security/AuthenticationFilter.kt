package com.ythosa.photoapp.api.users.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.ythosa.photoapp.api.users.ui.model.LoginRequestModel
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credentials = ObjectMapper().readValue(request?.inputStream, LoginRequestModel::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credentials.email, credentials.password
            )
        )
    }

    protected override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
    }
}
