package com.ythosa.photoapp.api.users.ui.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class LoginRequestModel {
    @NotNull(message = "email cannot be null")
    @Email
    val email: String = ""

    @NotNull(message = "password cannot be null")
    @Size(min = 5, max = 16, message = "password length must be in 5..16 characters")
    val password: String = ""
}
