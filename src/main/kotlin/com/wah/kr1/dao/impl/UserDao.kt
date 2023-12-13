package com.wah.kr1.dao.impl

import com.wah.kr1.dao.model.User
import java.io.File
import kotlin.jvm.optionals.getOrNull

const val USER_DATA_FILE_NAME = "C:/users.txt"

/**
 * Returns user with provided login or null if there is no such user
 * @param login login to search for
 */
fun getByLogin(login: String): User? {
    return File(USER_DATA_FILE_NAME).bufferedReader().lines().filter { entry -> entry.substringBefore(" ") == login}
        .limit(1)
        .map { entry ->
            val data = entry.split(" ")
            User(data[0], data[1], data[2].toInt())
        }
        .findAny()
        .getOrNull()
}