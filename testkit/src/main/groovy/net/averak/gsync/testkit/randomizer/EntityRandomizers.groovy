package net.averak.gsync.testkit.randomizer

import net.averak.gsync.adapter.dao.entity.base.EchoEntity
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.IRandomizer
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class EchoEntityRandomizer implements IRandomizer {

    final Class typeToGenerate = EchoEntity.class

    @Override
    Object getRandomValue() {
        return new EchoEntity(
            Faker.uuidv4().toString(),
            Faker.alphanumeric(255),
            Faker.fake(LocalDateTime),
            Faker.fake(LocalDateTime),
            Faker.fake(LocalDateTime),
        )
    }
}
