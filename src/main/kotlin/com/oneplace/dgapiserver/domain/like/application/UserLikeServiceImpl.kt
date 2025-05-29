package com.oneplace.dgapiserver.domain.like.application

import com.oneplace.dgapiserver.domain.like.persistance.UserLike
import com.oneplace.dgapiserver.domain.like.persistance.repository.UserLikeRepository
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserLikeServiceImpl (
    private val userRepository: UserRepository,
    private val userLikeRepository: UserLikeRepository
) : UserLikeService {

    // 좋아요 누르기
    @Transactional
    override fun like(fromUserId: Long, toUserId: Long) {
        require(fromUserId != toUserId) { "자기 자신을 좋아요할 수 없습니다." }

        val fromUser = userRepository.getReferenceById(fromUserId)
        val toUser = userRepository.getReferenceById(toUserId)

        val alreadyLiked = userLikeRepository.existsByFromUserAndToUser(fromUser, toUser)
        if (alreadyLiked) {
            throw IllegalStateException("이미 해당 유저를 좋아요했습니다.")
        }

        val like = UserLike(fromUser = fromUser, toUser = toUser)
        userLikeRepository.save(like)
    }

    // 좋아요 취소
    @Transactional
    override fun unlike(fromUserId: Long, toUserId: Long) {
        val fromUser = userRepository.getReferenceById(fromUserId)
        val toUser = userRepository.getReferenceById(toUserId)

        val deleted = userLikeRepository.deleteByFromUserAndToUser(fromUser, toUser)
        if (deleted == 0L) throw IllegalStateException("좋아요 기록이 없습니다.")
    }

    // 좋아요 여부 확인
    override fun isLiked(fromUserId: Long, toUserId: Long): Boolean {
        val fromUser = userRepository.getReferenceById(fromUserId)
        val toUser = userRepository.getReferenceById(toUserId)
        return userLikeRepository.existsByFromUserAndToUser(fromUser, toUser)
    }

    // 특정 유저가 받은 좋아요 수
    override fun countLikes(toUserId: Long): Long {
        val toUser = userRepository.getReferenceById(toUserId)
        return userLikeRepository.countByToUser(toUser)
    }

}
