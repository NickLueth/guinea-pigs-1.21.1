package net.nitwit.guinea_pigs.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.nitwit.guinea_pigs.GuineaPigs;

public class ModSounds {
    public static final SoundEvent CHUTTING = registerSoundEvent("chutting");
    public static final SoundEvent RUMBLING = registerSoundEvent("rumbling");
    public static final SoundEvent SCREAM = registerSoundEvent("scream");
    public static final SoundEvent WHEEK = registerSoundEvent("wheek");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(GuineaPigs.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        GuineaPigs.LOGGER.info("Registering Sounds for " + GuineaPigs.MOD_ID);
    }
}
