package com.lynas.app.controller

import com.lynas.app.Book
import com.lynas.app.BookRepository
import com.lynas.app.BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.get
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class BookControllerTest : BaseTest() {

    @Autowired
    lateinit var bookRepository: BookRepository

    @Test
    fun `should return all books`() {
        // given
        val books = listOf(
            Book().apply {
                id = UUID.randomUUID()
                title = "Book 1"
                author = "Author 1"
            },
            Book().apply {
                id = UUID.randomUUID()
                title = "Book 2"
                author = "Author 2"
            },
        )
        bookRepository.saveAll(books)

        // when

        val responseContent = mockMvc.get("/books/")
            .andExpect { status { isOk() } }
            .andReturn()
            .response.contentAsString.trim()

        // then
        val objectMapper = ObjectMapper()
        val response: List<Book> = objectMapper.readValue(responseContent, object : TypeReference<List<Book>>() {})
        assertThat(response.size).isEqualTo(books.size)

    }

}