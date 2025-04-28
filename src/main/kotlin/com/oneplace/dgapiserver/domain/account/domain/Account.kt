package com.oneplace.dgapiserver.domain.account.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val uid: String,

    @Column(nullable = true)
    val gender: String,

    @Column(nullable = true)
    val birth: LocalDate,

    @Column(nullable = true)
    val location: String,

    @Column(nullable = true)
    val nickname: String,

    @Column(nullable = true, columnDefinition = "TEXT")
    val profilePicUrl: String
)
