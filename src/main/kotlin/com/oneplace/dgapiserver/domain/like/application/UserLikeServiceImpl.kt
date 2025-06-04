package com.oneplace.dgapiserver.domain.like.application

import com.oneplace.dgapiserver.domain.auth.application.UserAuthenticationHolder
import com.oneplace.dgapiserver.domain.like.application.dto.response.WhoLikedMeResponse
import com.oneplace.dgapiserver.domain.like.application.exception.AlreadyLikedUserException
import com.oneplace.dgapiserver.domain.like.application.exception.CannotLikeSelfException
import com.oneplace.dgapiserver.domain.like.application.exception.LikeNotFoundException
import com.oneplace.dgapiserver.domain.like.persistance.UserLike
import com.oneplace.dgapiserver.domain.like.persistance.repository.UserLikeRepository
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period

@Service
class UserLikeServiceImpl (
    private val userRepository: UserRepository,
    private val userLikeRepository: UserLikeRepository,
    private val userAuthenticationHolder: UserAuthenticationHolder
) : UserLikeService {

    @Transactional
    override fun like(toUserId: Long) {
        val fromUser = userAuthenticationHolder.current()
        val toUser = userRepository.getReferenceById(toUserId)

        if (fromUser.id == toUserId) {
            throw CannotLikeSelfException()
        }

        val alreadyLiked = userLikeRepository.existsByFromUserAndToUser(fromUser, toUser)
        if (alreadyLiked) {
            throw AlreadyLikedUserException()
        }

        val like = UserLike(fromUser = fromUser, toUser = toUser)
        userLikeRepository.save(like)
    }

    @Transactional
    override fun unlike(toUserId: Long) {
        val fromUser = userAuthenticationHolder.current()
        val toUser = userRepository.getReferenceById(toUserId)

        val deleted = userLikeRepository.deleteByFromUserAndToUser(fromUser, toUser)
        if (deleted == 0L) throw LikeNotFoundException()
    }

    override fun countLikes(toUserId: Long): Long {
        val toUser = userRepository.getReferenceById(toUserId)
        return userLikeRepository.countByToUser(toUser)
    }

    override fun isLiked(toUserId: Long): Boolean {
        val fromUser = userAuthenticationHolder.current()
        val toUser = userRepository.getReferenceById(toUserId)
        return userLikeRepository.existsByFromUserAndToUser(fromUser, toUser)
    }

    override fun getUsersWhoLikedMe(): List<WhoLikedMeResponse> {
        val me = userAuthenticationHolder.current()
        val likes = userLikeRepository.findAllByToUser(me)
        val fromUsers = likes.map { it.fromUser }

        val likedUserIdsByMe = userLikeRepository.findAllByFromUser(me)
            .map { it.toUser.id }
            .toSet()

        return fromUsers.map { user ->
            WhoLikedMeResponse(
                id = user.id,
                profilePicUrl = user.profilePicUrl,
                nickname = user.nickname,
                age = user.birth?.let { calculateAge(it) },
                location = user.location ?: "",
                isLiked = likedUserIdsByMe.contains(user.id)
            )
        }
    }

    private fun calculateAge(birth: LocalDate): Int {
        return Period.between(birth, LocalDate.now()).years
    }

}
