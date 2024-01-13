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
        final now = LocalDateTime.now()
        return new EchoDto(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(255),
            Faker.fake(LocalDateTime),
            now,
            now,
        )
    }
}

@Component
class MasterVersionDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = MasterVersionDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new MasterVersionDto(
            Faker.uuidv4().toString(),
            Faker.fake(Boolean),
            Faker.alphanumeric(),
            now,
            now,
        )
    }
}

@Component
class OperatorDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = OperatorDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new OperatorDto(
            Faker.uuidv4().toString(),
            Faker.email(),
            now,
            now,
        )
    }
}

@Component
class PlayerDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new PlayerDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            false,
            now,
            now,
        )
    }
}

@Component
class PlayerLoginDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerLoginDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new PlayerLoginDto(
            Faker.uuidv4().toString(),
            Faker.integer(0, Integer.MAX_VALUE),
            Faker.fake(LocalDateTime),
            now,
            now,
        )
    }
}

@Component
class PlayerProfileDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerProfileDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new PlayerProfileDto(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(10),
            Faker.uuidv4().toString(),
            now,
            now,
        )
    }
}

@Component
class PlayerStorageEntryDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerStorageEntryDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new PlayerStorageEntryDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            now,
            now,
            Faker.fake(byte[]),
        )
    }
}

@Component
class PlayerStorageRevisionDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = PlayerStorageRevisionDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new PlayerStorageRevisionDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            now,
            now,
        )
    }
}

@Component
class RequiredClientVersionDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = RequiredClientVersionDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new RequiredClientVersionDto(
            Faker.uuidv4().toString(),
            Faker.semver(),
            Faker.fake(Platform).id,
            now,
            now,
        )
    }
}

@Component
class RTenantOperatorDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = RTenantOperatorDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new RTenantOperatorDto(
            Faker.uuidv4().toString(),
            Faker.uuidv4().toString(),
            Faker.fake(Boolean),
            now,
            now,
        )
    }
}

@Component
class TenantDtoRandomizer implements IRandomizer {

    final Class typeToGenerate = TenantDto.class

    @Override
    Object getRandomValue() {
        final now = LocalDateTime.now()
        return new TenantDto(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(),
            now,
            now,
        )
    }
}
