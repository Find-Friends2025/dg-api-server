package com.oneplace.dgapiserver.domain.account.api.dto.request

data class SearchUserRequest(
    val age: Int?,
    val residence: String?,
    val height: Int?,
    val bodyType: String?,
    val hasIntroduce: Boolean?,
    val isNewUser: Boolean?
) {

}