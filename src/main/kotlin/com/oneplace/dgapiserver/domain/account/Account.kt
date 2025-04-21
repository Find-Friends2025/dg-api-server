package com.oneplace.dgapiserver.domain.account

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(unique = true, nullable = false)
    val phoneNumber: String,

    val nickname: String,
    val age: Int,
    val address: String
)

