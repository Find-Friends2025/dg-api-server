package com.oneplace.dgapiserver.domain.user.persistance

import com.oneplace.dgapiserver.domain.auth.application.dto.RegisterReqDto
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@DynamicUpdate
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val uid: String,

    @Column(nullable = true)
    var gender: String? = null,

    @Column(nullable = true)
    var birth: LocalDate? = null,

    @Column(nullable = true)
    var location: String? = null,

    @Column(nullable = true)
    var nickname: String? = null,

    @Column(nullable = true)
    var bodyType: String? = null,

    @Column(nullable = true)
    val height: Int? = null,

    @Column(nullable = true)
    var introduce: String? = null,

    var isOnline: Boolean = true,

    @Column(nullable = true, columnDefinition = "TEXT")
    var profilePicUrl: String? = null,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = true)
    var isRegistered: Boolean = false,

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    var authority: Authority = Authority.UNAUTHENTICATED,
) {

    fun signUp(request: RegisterReqDto) {
        gender = request.gender
        birth = request.birth
        location = request.location
        nickname = request.nickname
        profilePicUrl = request.profilePicUrl
        isRegistered = true
        authority = Authority.USER
    }

    companion object {
        fun of(uid: String) = User(uid = uid)
    }

    fun modify(
        birth: LocalDate?,
        nickname: String?,
        introduce: String?,
        gender: String?,
        profilePicUrl: String?,
        residence: String?,
        isOnline: Boolean?,
    ) {
        birth?.let { this.birth = it }
        nickname?.let { this.nickname = it }
        introduce?.let { this.introduce = it }
        gender?.let { this.gender = it }
        profilePicUrl?.let { this.profilePicUrl = it }
        residence?.let { this.location = it }
        isOnline?.let { this.isOnline = it }
    }

}

enum class Authority {
    UNAUTHENTICATED, USER
}
