package com.wah.kr1.dao.model

/**
 * This class represents book
 * @param title title
 * @param description description
 * @param type 0 for digital, 1 for paperback. Any other int will be treated as digital
 */
data class Book(val title: String, val description: String, val type: Int)