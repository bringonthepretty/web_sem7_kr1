package com.wah.kr1.dao.model

/**
 * This class represents user
 * @param login login
 * @param password password
 * @param role 0 for user, 1 for admin. Any other int will be treated as user
 */
data class User(val login: String, val password: String, val role: Int)