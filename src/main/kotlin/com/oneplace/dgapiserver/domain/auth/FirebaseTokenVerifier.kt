package com.oneplace.dgapiserver.domain.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.springframework.stereotype.Component

@Component
class FirebaseTokenVerifier {
    fun verifyIdToken(idToken: String): FirebaseToken {
        return FirebaseAuth.getInstance().verifyIdToken(idToken)
    }
}
