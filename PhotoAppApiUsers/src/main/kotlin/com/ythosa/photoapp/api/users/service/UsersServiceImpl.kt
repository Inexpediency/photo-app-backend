package com.ythosa.photoapp.api.users.service

import com.ythosa.photoapp.api.users.data.UserEntity
import com.ythosa.photoapp.api.users.data.UsersRepository
import com.ythosa.photoapp.api.users.shared.UserDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersServiceImpl(private val usersRepository: UsersRepository) : UsersService {
    override fun createUser(userDetails: UserDto): UserDto {
        userDetails.userId = UUID.randomUUID().toString()

        val userEntity = UserEntity.ModelMapper.from(userDetails)
        userEntity.encryptedPassword = "12345"
        usersRepository.save(userEntity)

        return userDetails
    }
}
