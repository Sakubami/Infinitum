package xyz.sakubami.infinitum.world.entities.mob;

import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class CustomMob {

    private LivingEntity entity;
    private final UUID uuid = UUID.randomUUID();
    private int intelligence;
    private int strength;
    private int def;
    private int health;
    private int critChance;
    private int critDamage;

    public CustomMob( LivingEntity entity, int intelligence, int strength, int def, int health, int critChance, int critDamage )
    {
        this.health = health;
        this.intelligence = intelligence;
        this.strength = strength;
        this.def = def;
        this.critChance = critChance;
        this.critDamage = critDamage;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public int getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(int critDamage) {
        this.critDamage = critDamage;
    }
}
