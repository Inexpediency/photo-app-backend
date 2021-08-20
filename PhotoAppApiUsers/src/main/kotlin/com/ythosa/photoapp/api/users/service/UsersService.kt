package com.ythosa.photoapp.api.users.service

import com.ythosa.photoapp.api.users.shared.UserDto

interface UsersService {
    fun createUser(userDetails: UserDto): UserDto
}
