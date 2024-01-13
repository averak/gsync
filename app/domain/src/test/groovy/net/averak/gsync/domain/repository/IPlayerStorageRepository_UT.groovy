package net.averak.gsync.domain.repository

import net.averak.gsync.testkit.AbstractSpec

class PlayerStorageCriteria_UT extends AbstractSpec {

    def "isEmpty: 検索条件が空であるか判定"() {
        given:
        final criteria = new IPlayerStorageRepository.PlayerStorageCriteria(exactMatch, forwordMatch)

        when:
        final result = criteria.isEmpty()

        then:
        result == expected

        where:
        exactMatch | forwordMatch || expected
        [""]      || [""]         || false
        [""]      || []           || false
        []        || [""]         || false
        []        || []           || true
    }
}
