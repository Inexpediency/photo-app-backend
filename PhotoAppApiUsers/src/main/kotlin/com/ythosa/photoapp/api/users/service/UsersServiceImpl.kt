package com.ythosa.photoapp.api.users.service

import com.ythosa.photoapp.api.users.data.UserEntity
import com.ythosa.photoapp.api.users.data.UsersRepository
import com.ythosa.photoapp.api.users.shared.UserDto
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
}
