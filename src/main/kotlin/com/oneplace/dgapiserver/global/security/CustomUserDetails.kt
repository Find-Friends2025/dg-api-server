package com.oneplace.dgapiserver.global.security

import com.oneplace.dgapiserver.domain.account.domain.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val account: Account
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(SimpleGrantedAuthority(account.authority.name))

    override fun getPassword(): String? = null

    override fun getUsername(): String = account.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
