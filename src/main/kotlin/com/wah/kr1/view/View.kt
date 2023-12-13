package com.wah.kr1.view

import com.wah.kr1.dao.impl.*
import com.wah.kr1.dao.model.Book
import com.wah.kr1.dao.model.User
import java.security.MessageDigest
import java.util.Objects

/**
 * This class represents view
 */
class View {
    private lateinit var user: User

    /**
     * logins user
     */
    fun login(): Boolean {
        println("enter login")
        val login = readln()
        val user = getByLogin(login)
        println("enter password")

        val passwordHash = MessageDigest.getInstance("MD5").digest(readln().toByteArray()).joinToString("") { byte -> "%02x".format(byte) }
        return if (Objects.nonNull(user) && passwordHash == user!!.password) {
            this.user = user
            true
        } else {
            println("wrong login or password")
            false
        }
    }

    /**
     * shows menu
     */
    fun mainPage() {

        println("""Menu:
            |1. Find by title
            |2. Find by description
            |3. Show all
            |4. Exit
        """.trimMargin())
        if (user.role == 1) {
            println("""5. Add book
                |6. Delete book
            """.trimMargin())
        }

        var chosen = false
        while (!chosen) {
            when (readln().toInt()) {
                1 -> {
                    findByTitle()
                    chosen = true
                    mainPage()
                }
                2 -> {
                    findByDescription()
                    chosen = true
                    mainPage()
                }
                3 -> {
                    showAll()
                    chosen = true
                    mainPage()
                }
                4 -> {
                    chosen = true
                }
                5 -> {
                    chosen = if (user.role == 1) {
                        addBook()
                        mainPage()
                        true
                    } else {
                        println("Wrong input")
                        false
                    }
                }
                6 -> {
                    chosen = if (user.role == 1) {
                        deleteBook()
                        mainPage()
                        true
                    } else {
                        println("Wrong input")
                        false
                    }
                }
                else -> {
                    chosen = false
                }
            }
        }
    }

    private fun findByTitle() {
        println("Find by title:")
        println("Enter title")
        val book = getByTitle(readln())
        book?.let {
            println("Title Description Type")
            val type = if (book.type == 1) "Paperback" else "Digital"
            println("${book.title} ${book.description} $type")
        }
    }

    private fun findByDescription() {
        println("Find by description:")
        println("Enter description")
        val books = getByDescription(readln())
        println("Title Description Type")
        books.forEach {
            val type = if (it.type == 1) "Paperback" else "Digital"
            println("${it.title} ${it.description} $type")
        }
    }

    private fun showAll() {
        println("Show all:")
        val book = getAll()
        println("Title Description Type")
        book.forEach {
            val type = if (it.type == 1) "Paperback" else "Digital"
            println("${it.title} ${it.description} $type")
        }
    }

    private fun addBook() {
        println("Add book:")
        println("Enter title, description and type (1 for paperback. 0 for digital)")
        val line = readln().split(" ")
        addBook(Book(line[0], line[1], line[2].toInt()))
    }

    private fun deleteBook() {
        println("Delete book:")
        println("Enter title")
        deleteBookByTitle(readln())
    }
}