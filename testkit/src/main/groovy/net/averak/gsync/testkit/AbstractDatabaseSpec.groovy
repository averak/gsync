package net.averak.gsync.testkit

import groovy.sql.Sql
import jakarta.annotation.PostConstruct
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

    @PostConstruct
    private void init() {
        Fixture.init(sql)
    }

}
