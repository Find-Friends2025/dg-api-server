package com.oneplace.dgapiserver.domain.common.util

import java.time.LocalDate

class DateUtil {
    companion object {
        fun birthToAge(birth: LocalDate?) =
            birth?.let {
                val now = LocalDate.now()
                now.year - it.year - if (now.dayOfYear < it.dayOfYear) 1 else 0
            }
    }
}