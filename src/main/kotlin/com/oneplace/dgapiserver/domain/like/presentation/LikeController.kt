package com.oneplace.dgapiserver.domain.like.presentation

import com.oneplace.dgapiserver.domain.like.application.UserLikeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/like")
class LikeController (
    private val userLikeService: UserLikeService
) {

    @PostMapping("/{toUserId}/like")
    fun like(
        @RequestParam fromUserId: Long,
        @PathVariable toUserId: Long
    ) {
        userLikeService.like(fromUserId, toUserId)
    }

    @DeleteMapping("/{toUserId}/like")
    fun unlike(
        @RequestParam fromUserId: Long,
        @PathVariable toUserId: Long
    ) {
        userLikeService.unlike(fromUserId, toUserId)
    }

    @GetMapping("/{toUserId}/like/count")
    fun countLikes(
        @PathVariable toUserId: Long
    ): Long {
        return userLikeService.countLikes(toUserId)
    }

    @GetMapping("/{toUserId}/like/status")
    fun isLiked(
        @RequestParam fromUserId: Long,
        @PathVariable toUserId: Long
    ): Boolean {
        return userLikeService.isLiked(fromUserId, toUserId)
    }

}
