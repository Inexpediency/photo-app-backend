package com.ythosa.photoapp.api.users.data

import com.ythosa.photoapp.api.users.shared.UserDto
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    @GeneratedValue
    val id: Long = 0

    @Column(nullable = false, unique = true)
    var userId: String = ""

    @Column(nullable = false, length = 50)
    var firstName: String = ""

    @Column(nullable = false, length = 50)
    var lastName: String = ""

    @Column(nullable = false, length = 120, unique = true)
    var email: String = ""

    @Column(nullable = false)
    var encryptedPassword: String = ""

    object ModelMapper {
        fun from(data: UserDto): UserEntity {
            val userEntity = UserEntity()

            userEntity.userId = data.userId
            userEntity.firstName = data.firstName
            userEntity.lastName = data.lastName
            userEntity.email = data.email
            userEntity.encryptedPassword = data.encryptedPassword

            return userEntity
        }
    }
}
