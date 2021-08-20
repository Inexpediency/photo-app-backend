package com.ythosa.photoapp.api.users.ui.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CreateUserRequestModel {
    @NotNull(message = "first name cannot be null")
    @Size(min = 2, message = "first name must not be less than 2 characters")
    val firstName: String = ""

    @NotNull(message = "last name cannot be null")
    @Size(min = 2, message = "last name must not be less than 2 characters")
    val lastName: String = ""

    @NotNull(message = "email cannot be null")
    @Email
    val email: String = ""

    @NotNull(message = "password cannot be null")
    @Size(min = 5, max = 16, message = "password length must be in 5..16 characters")
    val password: String = ""
}
