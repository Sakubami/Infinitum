package xyz.sakubami.infinitum.world.entities.mob;

import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.functionality.Attribute;

import java.util.HashMap;
import java.util.UUID;

public class MobMask {

    private final LivingEntity entity;
    private final UUID uuid = UUID.randomUUID();
    private final HashMap<Attribute, Integer> mask;

    public MobMask( LivingEntity entity, HashMap<Attribute, Integer> attributes )
    {
        this.entity = entity;
        this.mask = attributes;
    }

    public LivingEntity getEntity() { return entity; }
    public UUID getUuid() { return uuid; }
    public HashMap<Attribute, Integer> getMask() { return mask; }
}
