package com.oneplace.dgapiserver.domain.user.presentation

import com.oneplace.dgapiserver.domain.user.application.UserService
import com.oneplace.dgapiserver.domain.user.presentation.dto.request.ModifyUserRequest
import com.oneplace.dgapiserver.domain.user.presentation.dto.request.SearchUserRequest
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.MatchedUserResponse
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.UserProfileResponse
import com.oneplace.dgapiserver.global.response.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun searchUsers(
        @ModelAttribute searchUserRequest: SearchUserRequest
    ): BaseResponse<List<MatchedUserResponse>> =
        BaseResponse(
            message = "Search User Success",
            data = userService.searchUser(searchUserRequest)
        )

    @GetMapping("/{userId}")
    fun getUserProfile(
        @PathVariable userId: Long
    ): BaseResponse<UserProfileResponse> =
        BaseResponse(
            message = "Get UserProfile Success",
            data = userService.getUserDetail(userId)
        )

    @GetMapping("/me")
    fun getMyProfile(): BaseResponse<UserProfileResponse> =
        BaseResponse(
            message = "Get My Profile Success",
            data = userService.getMyProfile()
        )

    @PatchMapping("/me")
    fun modifyUser(@RequestBody request: ModifyUserRequest): BaseResponse<Unit> {
        userService.modifyProfile(request)
        return BaseResponse(
            message = "Modify My Profile Success",
        )
    }
}