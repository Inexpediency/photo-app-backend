package com.ythosa.photoapp.api.users.ui.controllers

import com.ythosa.photoapp.api.users.service.UsersService
import com.ythosa.photoapp.api.users.shared.UserDto
import com.ythosa.photoapp.api.users.ui.model.CreateUserRequestModel
import com.ythosa.photoapp.api.users.ui.model.CreateUserResponseModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UsersController(private val usersService: UsersService) {

    @GetMapping("/status/check")
    fun status(): String = "working"

    @PostMapping
    fun createUser(@Valid @RequestBody userDetails: CreateUserRequestModel): ResponseEntity<CreateUserResponseModel> {
        val userDto = UserDto()
        userDto.firstName = userDetails.firstName
        userDto.lastName = userDetails.lastName
        userDto.email = userDetails.email
        userDto.password = userDetails.password

        val createdUser = usersService.createUser(userDto)
        val response = CreateUserResponseModel(
            userId = createdUser.userId,
            firstName = createdUser.firstName,
            lastName = createdUser.lastName,
            email = createdUser.email,
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
