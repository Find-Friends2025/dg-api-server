package com.oneplace.dgapiserver.domain.user.persistance.repository

import com.oneplace.dgapiserver.domain.user.persistance.User
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

class UserSpecification {
    companion object {
        fun search(
            age: Int?,
            residence: String?,
            height: Int?,
            bodyType: String?,
            hasIntroduce: Boolean?,
            isNewUser: Boolean?
        ): Specification<User> {
            return Specification { root, _, cb ->
                val predicates = mutableListOf<Predicate>()

                age?.let { predicates += cb.equal(root.get<Int>("age"), it) }
                residence?.let { predicates += cb.equal(root.get<String>("location"), it) }
                height?.let { predicates += cb.equal(root.get<Int>("height"), it) }
                bodyType?.let { predicates += cb.equal(root.get<String>("bodyType"), it) }
                hasIntroduce?.let { predicates += cb.isNotNull(root.get<String>("introduce")) }

                isNewUser?.let {
                    if (it) {
                        val threeDaysAgo = LocalDateTime.now().minusDays(3)
                        predicates += cb.greaterThanOrEqualTo(root.get("createdAt"), threeDaysAgo)
                    }
                }

                cb.and(*predicates.toTypedArray())
            }
        }
    }
}
