package net.averak.gsync.testkit.randomizer

import net.averak.gsync.adapter.dao.dto.base.EchoDto
import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.dao.dto.base.PlayerLoginDto
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileDto
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
            Faker.fake(LocalDateTime),
            Faker.fake(LocalDateTime),
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
            Faker.uuidv4().toString(),
            false,
            Faker.fake(LocalDateTime),
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
            "",
            Faker.integer(0, Integer.MAX_VALUE),
            Faker.fake(LocalDateTime),
            Faker.fake(LocalDateTime),
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
            "",
            Faker.alphanumeric(10),
            Faker.uuidv4().toString(),
            Faker.fake(LocalDateTime),
            Faker.fake(LocalDateTime),)
    }
}

