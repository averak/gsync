package net.averak.gsync.testkit

import groovy.sql.Sql
import jakarta.annotation.PostConstruct
import net.averak.gsync.infrastructure.redis.RedisClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.transaction.annotation.Transactional

// @Transactional を付けるとテストケースがトランザクション内で実行され、テストケース実行後にロールバックされる
// https://spring.pleiades.io/spring-framework/reference/testing/testcontext-framework/tx.html#testcontext-tx-enabling-transactions
@Transactional
@EnableAutoConfiguration
abstract class AbstractDatabaseSpec extends AbstractSpec {

    @Autowired
    Sql sql

    @Autowired
    RedisClient redis

    @PostConstruct
    private void init() {
        Fixture.init(sql)
    }

    void cleanup() {
        redis.flushdb()

        // なぜか @Transactional でロールバックされないので、仕方なく DELETE クエリを実行している
        sql.execute("DELETE FROM gsync_echo WHERE echo_id IS NOT NULL")
    }
}
