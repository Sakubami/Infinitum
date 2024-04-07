package xyz.sakubami.infinitum.world.entities.player;

import org.bukkit.entity.Player;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.functionality.Attribute;
import xyz.sakubami.infinitum.world.entities.player.skills.ExperienceType;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMask {

    private final UUID uuid;
    private final Player player;
    private int level;
    private final HashMap<ExperienceType, Integer> experience;
    private final HashMap<String, Integer> skillTree;
    private final HashMap<Attribute, Integer> attributes;

    public PlayerMask( UUID uuid, int level, HashMap<String, Integer> skillTree, HashMap<ExperienceType, Integer> experience, HashMap<Attribute, Integer> attributes ) {
        this.uuid = uuid;
        this.player = Infinitum.getInstance().getServer().getPlayer( uuid );
        this.level = level;
        this.skillTree = skillTree;
        this.experience = experience;
        this.attributes = attributes;
    }

    public UUID getUUID() { return uuid; }
    public int getLevel() { return level; }
    public HashMap<String, Integer> getSkillTree() { return skillTree; }
    public HashMap<ExperienceType, Integer> getExperience() { return experience; }
    public HashMap<Attribute, Integer> getAttributes() { return attributes; }
    public Player getPlayer() { return player; }

    public void setLevel( int v ) { this.level = v; }
}