package com.oneplace.dgapiserver.domain.like.presentation

import com.oneplace.dgapiserver.domain.like.application.UserLikeService
import com.oneplace.dgapiserver.domain.like.application.dto.response.WhoLikedMeResponse
import com.oneplace.dgapiserver.global.response.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/like")
class LikeController (
    private val userLikeService: UserLikeService
) {

    @PostMapping("/{toUserId}")
    fun like(@PathVariable toUserId: Long) {
        userLikeService.like(toUserId)
    }

    @DeleteMapping("/{toUserId}")
    fun unlike(@PathVariable toUserId: Long) {
        userLikeService.unlike(toUserId)
    }

    @GetMapping("/{toUserId}/count")
    fun countLikes(@PathVariable toUserId: Long): Long {
        return userLikeService.countLikes(toUserId)
    }

    @GetMapping("/{toUserId}/status")
    fun isLiked(@PathVariable toUserId: Long): Boolean {
        return userLikeService.isLiked(toUserId)
    }

    @GetMapping("/me/likes")
    fun getUsersWhoLikedMe(): BaseResponse<List<WhoLikedMeResponse>> =
        BaseResponse(
            message = "Users who liked me retrieved successfully",
            data = userLikeService.getUsersWhoLikedMe()
        )

}
