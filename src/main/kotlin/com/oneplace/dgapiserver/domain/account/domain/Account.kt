package com.oneplace.dgapiserver.domain.account.domain

import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val uid: String,

    @Column(nullable = false)
    val nickname: String,

    @Column(nullable = true)
    val phoneNumber: String? = null,

)
