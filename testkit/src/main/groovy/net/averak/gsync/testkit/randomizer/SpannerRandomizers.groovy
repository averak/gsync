package net.averak.gsync.testkit.randomizer

import net.averak.gsync.adapter.dao.dto.base.*
import net.averak.gsync.domain.model.Platform
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.IRandomizer
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class EchoDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = EchoDto.class

    @Override
    Object getRandomValue() {
        return new EchoDto(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(255),
            Faker.fake(LocalDateTime),
        )
    }
}

@Component
class FriendSettingMasterDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = FriendSettingMasterDto.class

    @Override
    Object getRandomValue() {
        return new FriendSettingMasterDto(
            Faker.uuidv4().toString(),
            Faker.integer(1, 100),
            Faker.integer(1, 100),
        )
    }
}

@Component
class GameDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = GameDto.class

    @Override
    Object getRandomValue() {
        return new GameDto(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(),
        )
    }
}

@Component
class MasterVersionDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = MasterVersionDto.class

    @Override
    Object getRandomValue() {
        return new MasterVersionDto(
            Faker.uuidv4().toString(),
            Faker.fake(Boolean),
            Faker.alphanumeric(),
        )
    }
}

@Component
class OperatorDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = OperatorDto.class

    @Override
    Object getRandomValue() {
        return new OperatorDto(
            Faker.uuidv4().toString(),
            Faker.email(),
        )
    }
}

@Component
class PlayerDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerDto.class

    @Override
    Object getRandomValue() {
        return new PlayerDto(
            Faker.uuidv4().toString(),
            false,
        )
    }
}

@Component
class PlayerFriendDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerFriendDto.class

    @Override
    Object getRandomValue() {
        return new PlayerFriendDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.fake(LocalDateTime),
        )
    }
}

@Component
class PlayerFriendRequestDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerFriendRequestDto.class

    @Override
    Object getRandomValue() {
        return new PlayerFriendRequestDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.fake(LocalDateTime),
        )
    }
}

@Component
class PlayerLoginDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerLoginDto.class

    @Override
    Object getRandomValue() {
        return new PlayerLoginDto(
            Faker.uuidv4().toString(),
            Faker.integer(0, Integer.MAX_VALUE),
            Faker.fake(LocalDateTime),
        )
    }
}

@Component
class PlayerProfileDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerProfileDto.class

    @Override
    Object getRandomValue() {
        return new PlayerProfileDto(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(10),
            Faker.uuidv4().toString(),
        )
    }
}

@Component
class PlayerStorageEntryDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerStorageEntryDto.class

    @Override
    Object getRandomValue() {
        return new PlayerStorageEntryDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.fake(byte[]),
        )
    }
}

@Component
class PlayerStorageRevisionDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerStorageRevisionDto.class

    @Override
    Object getRandomValue() {
        return new PlayerStorageRevisionDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
        )
    }
}

@Component
class RequiredClientVersionDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = RequiredClientVersionDto.class

    @Override
    Object getRandomValue() {
        return new RequiredClientVersionDto(
            Faker.uuidv4().toString(),
            Faker.semver(),
            Faker.fake(Platform).id,
        )
    }
}

@Component
class RGameOperatorDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = RGameOperatorDto.class

    @Override
    Object getRandomValue() {
        return new RGameOperatorDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.fake(Boolean),
        )
    }
}

