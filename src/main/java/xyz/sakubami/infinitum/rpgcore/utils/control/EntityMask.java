package xyz.sakubami.infinitum.rpgcore.utils.control;

import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.rpg.world.entities.loot.CustomLootTable;
import xyz.sakubami.infinitum.rpg.world.entities.player.skills.ExperienceType;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;

import java.util.HashMap;
import java.util.UUID;

public class EntityMask {

    private final LivingEntity entity;
    private final UUID uuid;
    private final String name;
    private final CustomType customType;
    private final HashMap<Attribute, Integer> mask;
    private int level;
    private final HashMap<ExperienceType, Integer> experience;
    private final HashMap<String, Integer> skillTree;
    private final CustomLootTable lootTable;

    public EntityMask( String name, LivingEntity entity, CustomType customType, int level, HashMap<String, Integer> skillTree, HashMap<ExperienceType, Integer> experience, HashMap<Attribute, Integer> attributes, CustomLootTable lootTable )
    {
        this.entity = entity;
        this.mask = attributes;
        this.customType = customType;
        this.level = level;
        this.name = name;
        this.uuid = entity.getUniqueId();
        this.skillTree = skillTree;
        this.experience = experience;
        this.lootTable = lootTable;
    }
    public LivingEntity getEntity() { return entity; }
    public CustomType getCustomType() { return customType; }
    public UUID getUuid() { return uuid; }
    public int getLevel() { return level; }
    public HashMap<String, Integer> getSkillTree() { return skillTree; }
    public HashMap<ExperienceType, Integer> getExperience() { return experience; }
    public HashMap<Attribute, Integer> getMasked() { return mask; }
    public String getName() { return name; }
    public CustomLootTable getLootTable() { return lootTable; }

    public void setLevel( int v ) { this.level = v; }
}