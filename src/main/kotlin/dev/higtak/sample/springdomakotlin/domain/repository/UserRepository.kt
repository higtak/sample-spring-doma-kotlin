package dev.higtak.sample.springdomakotlin.domain.repository

import dev.higtak.sample.springdomakotlin.domain.entity.User
import org.seasar.doma.Dao
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.experimental.Sql
import org.seasar.doma.jdbc.Result

@ConfigAutowireable
@Dao
interface UserRepository {
    @Sql("""
    SELECT
        *
    FROM
        users
    ORDER BY
        created_at
    """)
    @Select
    fun findAll(): List<User>

    @Insert
    fun create(user: User): Result<User>
}