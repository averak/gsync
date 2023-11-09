package net.averak.gsync.randomizer.domain.primitive.common

import net.averak.gsync.domain.primitive.common.ID
import net.averak.gsync.testkit.IRandomizer

@Singleton
class IDRandomizer implements IRandomizer {

    final Class typeToGenerate = ID.class

    @Override
    Object getRandomValue() {
        return new ID()
    }

}
