package com.ythosa.photoapp.api.users.service

import com.ythosa.photoapp.api.users.data.UserEntity
import com.ythosa.photoapp.api.users.data.UsersRepository
import com.ythosa.photoapp.api.users.shared.UserDto
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsersServiceImpl(
    private val usersRepository: UsersRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : UsersService {
    override fun createUser(userDetails: UserDto): UserDto {
        userDetails.userId = UUID.randomUUID().toString()
        userDetails.encryptedPassword = bCryptPasswordEncoder.encode(userDetails.password)

        usersRepository.save(UserEntity.ModelMapper.from(userDetails))

        return userDetails
    }

    override fun getUserDetailsByEmail(email: String): UserDto {
        val userEntity = usersRepository.findByEmail(email)
        val userDto = UserDto()

        userDto.userId = userEntity.userId
        userDto.email = userEntity.email
        userDto.encryptedPassword = userEntity.encryptedPassword
        userDto.firstName = userEntity.firstName
        userDto.lastName = userEntity.lastName

        return userDto
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = usersRepository.findByEmail(username)

        return User(userEntity.email, userEntity.encryptedPassword, listOf())
    }
}
