package com.cyber2000.mountainous.world.gen.forge;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.function.Predicate;

public class Test {

    /**
     * Returns a biome selector that will match all biomes that would normally spawn in the Overworld,
     * assuming Vanilla's layered biome source is used.
     *
     * <p>This selector will also match modded biomes that have been added to the overworld using {@link OverworldBiomes}.
     */
    public static Predicate<String> foundInOverworld() {
        return context -> {
            System.out.println(context);
            return context.equals("LOL");
        };
    }

}
