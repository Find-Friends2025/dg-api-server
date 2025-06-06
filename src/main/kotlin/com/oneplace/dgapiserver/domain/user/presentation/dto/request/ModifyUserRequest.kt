package com.oneplace.dgapiserver.domain.user.presentation.dto.request

import java.time.LocalDate

data class ModifyUserRequest(
    val birth: LocalDate?,
    val nickname: String?,
    val residence: String?,
    val isOnline: Boolean?,
) {
}