package net.averak.gsync.usecase

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.domain.repository.exception.AlreadyDoneException
import net.averak.gsync.testkit.Faker
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class PlayerStorageUsecase_search_UT extends AbstractUsecase_UT {

    @Autowired
    PlayerStorageUsecase sut

    @Shared
    UUID playerID = Faker.uuidv4()

    @Shared
    UUID gameID = Faker.uuidv4()

    def "正常系: プレイヤーストレージを検索する"() {
        given:
        final gctx = Faker.fake(GameContext)
        final criteria = Faker.fake(IPlayerStorageRepository.PlayerStorageCriteria)

        final playerStorage = Faker.fake(PlayerStorage)
        1 * playerStorageRepository.get(gctx, playerID, gameID, criteria) >> playerStorage

        when:
        final result = this.sut.search(gctx, playerID, gameID, criteria)

        then:
        result == playerStorage
    }
}

class PlayerStorageUsecase_set_UT extends AbstractUsecase_UT {

    @Autowired
    PlayerStorageUsecase sut

    @Shared
    UUID playerID = Faker.uuidv4()

    @Shared
    UUID gameID = Faker.uuidv4()

    @Shared
    UUID revision = Faker.uuidv4()

    def "正常系: エントリをセットする"() {
        given:
        final gctx = Faker.fake(GameContext)
        final entry = Faker.fake(PlayerStorageEntry)

        final playerStorage = Faker.fake(PlayerStorage, [revision: revision])
        1 * playerStorageRepository.get(gctx, playerID, gameID, _) >> playerStorage

        when:
        final result = this.sut.set(gctx, playerID, gameID, entry, revision)

        then:
        result == new PlayerStorageUsecase.SetResult(entry, playerStorage.revision)
    }

    def "正常系: リトライされた場合は更新を行わない"() {
        given:
        final gctx = Faker.fake(GameContext)
        final entry = Faker.fake(PlayerStorageEntry)

        final playerStorage = Faker.fake(PlayerStorage, [
            revision: revision,
        ])
        1 * playerStorageRepository.get(gctx, playerID, gameID, _) >> playerStorage

        when:
        final result = this.sut.set(gctx, playerID, gameID, entry, revision)

        then:
        1 * playerStorageRepository.save(gctx, playerStorage) >> { throw new AlreadyDoneException() }
        result == new PlayerStorageUsecase.SetResult(entry, revision)
    }
}

class PlayerStorageUsecase_clear_UT extends AbstractUsecase_UT {

    @Autowired
    PlayerStorageUsecase sut

    @Shared
    UUID playerID = Faker.uuidv4()

    @Shared
    UUID gameID = Faker.uuidv4()

    @Shared
    UUID revision = Faker.uuidv4()

    def "正常系: エントリを消去する"() {
        given:
        final gctx = Faker.fake(GameContext)
        final criteria = Faker.fake(IPlayerStorageRepository.PlayerStorageCriteria)

        final playerStorage = Faker.fake(PlayerStorage, [
            revision: revision,
            entries : Faker.fakes(PlayerStorageEntry, 2),
        ])
        1 * playerStorageRepository.get(gctx, playerID, gameID, criteria) >> playerStorage

        when:
        final result = this.sut.clear(gctx, playerID, gameID, revision, criteria)

        then:
        1 * playerStorageRepository.save(gctx, playerStorage)

        result == playerStorage.revision
        playerStorage.entries*.value == [new byte[0], new byte[0]]
    }

    def "正常系: リトライされた場合は更新を行わない"() {
        given:
        final gctx = Faker.fake(GameContext)
        final criteria = Faker.fake(IPlayerStorageRepository.PlayerStorageCriteria)

        final playerStorage = Faker.fake(PlayerStorage, [
            revision: revision,
        ])
        1 * playerStorageRepository.get(gctx, playerID, gameID, criteria) >> playerStorage

        when:
        final result = this.sut.clear(gctx, playerID, gameID, revision, criteria)

        then:
        1 * playerStorageRepository.save(gctx, playerStorage) >> { throw new AlreadyDoneException() }
        result == revision
    }
}
