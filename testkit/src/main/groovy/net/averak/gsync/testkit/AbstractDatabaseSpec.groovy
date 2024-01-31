package net.averak.gsync.testkit

import groovy.sql.Sql
import jakarta.annotation.PostConstruct
import net.averak.gsync.infrastructure.redis.RedisClient
import net.averak.gsync.testkit.fixture.Fixture
import net.averak.gsync.testkit.fixture.setupper.MasterUp
import net.averak.gsync.testkit.fixture.setupper.PlayerUp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import spock.lang.Shared

@EnableAutoConfiguration
abstract class AbstractDatabaseSpec extends AbstractSpec {

    @Autowired
    @Shared
    Sql sql

    @Autowired
    @Shared
    RedisClient redis

    @Autowired
    @Shared
    MasterUp masterUp

    @Autowired
    @Shared
    PlayerUp playerUp

    @PostConstruct
    private void init() {
        Fixture.init(sql)
    }

    void cleanup() {
        redis.flushdb()

        // テストクラスに @Transactional を付けても何故かテストケース実行後にロールバックされないので、手動でクリーンアップする
        // https://spring.pleiades.io/spring-framework/reference/testing/testcontext-framework/tx.html#testcontext-tx-enabling-transactions
        sql.execute("DELETE FROM gsync_echo WHERE TRUE")
        sql.execute("DELETE FROM gsync_friend_setting_master WHERE TRUE")
        sql.execute("DELETE FROM gsync_game WHERE TRUE")
        sql.execute("DELETE FROM gsync_master_version WHERE TRUE")
        sql.execute("DELETE FROM gsync_operator WHERE TRUE")
        sql.execute("DELETE FROM gsync_player WHERE TRUE")
        sql.execute("DELETE FROM gsync_required_client_version WHERE TRUE")
    }
}
