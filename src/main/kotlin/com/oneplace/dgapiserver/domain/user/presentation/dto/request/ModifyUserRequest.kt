package com.oneplace.dgapiserver.domain.user.presentation.dto.request

import java.time.LocalDate

data class ModifyUserRequest(
    val birth: LocalDate?,
    val nickname: String?,
    val introduce: String?,
    val residence: String?,
    val gender: String?,
    val profilePicUrl: String?,
    val isOnline: Boolean?,
) {
}
