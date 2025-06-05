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
    fun like(
        @PathVariable toUserId: Long
    ): BaseResponse<Unit> {
        return BaseResponse(
            message = "Like Success",
            data = userLikeService.like(toUserId)
        )
    }

    @DeleteMapping("/{toUserId}")
    fun unlike(
        @PathVariable toUserId: Long
    ): BaseResponse<Unit> {
        return BaseResponse(
            message = "Like Cancel Success",
            data = userLikeService.unlike(toUserId)
        )
    }

    @GetMapping("/{toUserId}/count")
    fun countLikes(
        @PathVariable toUserId: Long
    ): BaseResponse<Long> {
        return BaseResponse(
            message = "Successfully checked the number of likes received by the user",
            data = userLikeService.countLikes(toUserId)
        )
    }

    @GetMapping("/{toUserId}/status")
    fun isLiked(
        @PathVariable toUserId: Long
    ): BaseResponse<Boolean> {
        return BaseResponse(
            message = "Successful confirmation of likes",
            data = userLikeService.isLiked(toUserId)
        )
    }

    @GetMapping("/me/likes")
    fun getUsersWhoLikedMe()
    : BaseResponse<List<WhoLikedMeResponse>> {
        return BaseResponse(
            message = "Users who liked me retrieved successfully",
            data = userLikeService.getUsersWhoLikedMe()
        )
    }
}
