package com.oneplace.dgapiserver.domain.like.presentation

import com.oneplace.dgapiserver.domain.like.application.UserLikeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/like")
class LikeController (
    private val userLikeService: UserLikeService
) {

    @PostMapping("/{toUserId}/like")
    fun like(@PathVariable toUserId: Long) {
        userLikeService.like(toUserId)
    }

    @DeleteMapping("/{toUserId}/like")
    fun unlike(@PathVariable toUserId: Long) {
        userLikeService.unlike(toUserId)
    }

    @GetMapping("/{toUserId}/like/count")
    fun countLikes(@PathVariable toUserId: Long): Long {
        return userLikeService.countLikes(toUserId)
    }

    @GetMapping("/{toUserId}/like/status")
    fun isLiked(@PathVariable toUserId: Long): Boolean {
        return userLikeService.isLiked(toUserId)
    }
}
