package com.ythosa.photoapp.api.users.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurity : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests().antMatchers("/users/**").permitAll()
            .and()
            .addFilter(getAuthenticationFilter())
        http.headers().frameOptions().disable()
    }

    private fun getAuthenticationFilter(): AuthenticationFilter {
        val authenticationFilter = AuthenticationFilter()
        authenticationFilter.setAuthenticationManager(authenticationManager())

        return authenticationFilter
    }
}
