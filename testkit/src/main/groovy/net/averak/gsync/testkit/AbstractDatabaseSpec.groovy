package net.averak.gsync.testkit

import groovy.sql.Sql
import jakarta.annotation.PostConstruct
import net.averak.gsync.infrastructure.redis.RedisClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared

// @Transactional を付けるとテストケースがトランザクション内で実行され、テストケース実行後にロールバックされる
// https://spring.pleiades.io/spring-framework/reference/testing/testcontext-framework/tx.html#testcontext-tx-enabling-transactions
@Transactional
@EnableAutoConfiguration
abstract class AbstractDatabaseSpec extends AbstractSpec {

    @Autowired
    @Shared
    Sql sql

    @Autowired
    @Shared
    RedisClient redis

    @PostConstruct
    private void init() {
        Fixture.init(sql)
    }

    void cleanup() {
        redis.flushdb()
    }
}
