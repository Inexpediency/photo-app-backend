package com.ythosa.photoapp.api.users.ui.controllers

import com.ythosa.photoapp.api.users.service.UsersService
import com.ythosa.photoapp.api.users.shared.UserDto
import com.ythosa.photoapp.api.users.ui.model.CreateUserRequestModel
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/status/check")
    fun status(): String = "working"

    @PostMapping
    fun createUser(@Valid @RequestBody userDetails: CreateUserRequestModel): UserDto {
        val userDto = UserDto()

        userDto.firstName = userDetails.firstName
        userDto.lastName = userDetails.lastName
        userDto.email = userDetails.email
        userDto.password = userDetails.password

        return usersService.createUser(userDto)
    }
}
