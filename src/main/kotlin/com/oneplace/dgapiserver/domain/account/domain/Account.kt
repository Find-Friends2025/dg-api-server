package com.oneplace.dgapiserver.domain.account.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@Entity
@DynamicUpdate
@Table(name = "account")
class Account(
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

    @Column(nullable = true, columnDefinition = "TEXT")
    var profilePicUrl: String? = null,

    @Column(nullable = true)
    var isRegistered: Boolean = false
)
