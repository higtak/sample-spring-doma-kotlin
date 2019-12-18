package dev.higtak.sample.springdomakotlin.domain.repository

import dev.higtak.sample.springdomakotlin.domain.entity.Company
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
class CompanyRepositoryTests {
    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Transactional
    @Test
    fun testFindAll() {
        val list = companyRepository.findAll()
        assertEquals(2, list.size)
        println(list)
    }

    @Transactional
    @Test
    fun testCreate() {
        val entity = Company(
                name = "株式会社テスト",
                location = "北海道札幌市",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )
        val (company, count) = companyRepository.create(entity);
        assertEquals(1, count);
        println(company)
    }
}