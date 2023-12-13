package com.wah.kr1.dao.impl

import com.wah.kr1.dao.model.Book
import java.io.File
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrNull

const val BOOK_DATA_FILE_NAME = "C:/books.txt"

/**
 * Returns book with provided title or null if there is no such book
 * @param title title to search for
 */
fun getByTitle(title: String): Book? {
    return File(BOOK_DATA_FILE_NAME).bufferedReader().lines().filter { entry -> entry.substringBefore(" ") == title}
        .limit(1)
        .map { entry ->
            val data = entry.split(" ")
            Book(data[0], data[1], data[2].toInt())
        }
        .findAny()
        .getOrNull()
}

/**
 * Returns list of books which description contains descriptionPart or empty list if there is no such books
 * @param descriptionPart descriptionPart to search for
 */
fun getByDescription(descriptionPart: String): List<Book> {
    return File(BOOK_DATA_FILE_NAME).bufferedReader().lines()
        .map { entry ->
            val data = entry.split(" ")
            Book(data[0], data[1], data[2].toInt())
        }
        .filter{ it.description.lowercase().contains(descriptionPart.lowercase())}
        .collect(Collectors.toCollection(::ArrayList))
}

/**
 * returns all books
 */
fun getAll(): List<Book> {
    return File(BOOK_DATA_FILE_NAME).bufferedReader().lines()
        .map { entry ->
            val data = entry.split(" ")
            Book(data[0], data[1], data[2].toInt())
        }
        .collect(Collectors.toCollection(::ArrayList))
}

/**
 * Adds provided book to catalog
 * @param book book to be added
 */
fun addBook(book: Book) {
    val bookData = StringBuilder()
    bookData.append("\n").append(book.title).append(" ").append(book.description).append(" ").append(book.type)
    return File(BOOK_DATA_FILE_NAME).appendBytes(bookData.toString().toByteArray())
}

/**
 * Deletes book with provided name from catalog
 * @param title title of book to be deleted
 */
fun deleteBookByTitle(title: String) {
    val books = File(BOOK_DATA_FILE_NAME).bufferedReader().lines()
        .filter { entry -> entry.substringBefore(" ") != title}
        .collect(Collectors.joining("\n"))
    File(BOOK_DATA_FILE_NAME).writeBytes(books.toByteArray())
}