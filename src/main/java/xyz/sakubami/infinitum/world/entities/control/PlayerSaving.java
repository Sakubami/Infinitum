package xyz.sakubami.infinitum.world.entities.control;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.entities.player.skills.ExperienceType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class PlayerSaving {

    //TODO REPLACE WITH ANYTHING MORE OPTIMIZED

    private final String path = "plugins/Infinitum/PlayerControl/Players.yml";

    private ArrayList<EntityMask> masks;

    public PlayerSaving()
    {
        masks = new ArrayList<>();
        load();
    }

    private void load()
    {
        masks = new ArrayList<>();

        FileConfiguration config = YamlConfiguration.loadConfiguration( new File( path ) );

        for (int i = 0; true; i++)
        {
            if ( !config.contains( "players." + i ) )  break;

            HashMap<String, Integer> skills = new HashMap<>();
            HashMap<ExperienceType, Integer> experience = new HashMap<>();
            HashMap<Attribute, Integer> attributes = new HashMap<>();
            UUID uuid = UUID.fromString( config.getString( "players." + i + ".uuid" ) );
            int level = Integer.parseInt( config.getString("players." +i+ ".level" ) );
            List<String> cfgSkills = config.getStringList("players." +i+ ".skills" );
            List<String> cfgExperience = config.getStringList( "players." +i+ ".experience" );
            List<String> cfgAttributes = config.getStringList( "players." +i+ ".attributes" );

            for ( String line : cfgSkills )
            {
                String[] split = line.split( "/" );
                skills.put( split[0], Integer.parseInt( split[1] ) );
            }

            for ( String line : cfgAttributes )
            {
                String[] split = line.split( "/" );
                attributes.put( Attribute.valueOf( split[0] ), Integer.parseInt( split[1] ) );
            }

            for ( String line : cfgExperience )
            {
                String[] split = line.split( "/" );
                experience.put( ExperienceType.valueOf( split[0] ), Integer.parseInt( split[1] ) );
            }

            LivingEntity entity = ( LivingEntity ) Infinitum.getInstance().getServer().getEntity( uuid );

            masks.add( new EntityMask( entity, CustomType.PLAYER,  level, skills, experience, attributes ) );
        }
    }

    private void save()
    {

        FileConfiguration config = new YamlConfiguration();

        for (int i = 0; masks.size() > i; i++)
        {
            EntityMask entityMask = masks.get( i );
            List<String> skills = new ArrayList<>();
            List<String> experience = new ArrayList<>();
            List<String> attributes = new ArrayList<>();

            for ( String skill : entityMask.getSkillTree().keySet() )
            {
                int value = entityMask.getSkillTree().get( skill );
                skills.add( skill + "/" + value );
            }

            for ( ExperienceType experienceType : entityMask.getExperience().keySet() )
            {
                int value = entityMask.getExperience().get( experienceType );
                    experience.add( experienceType.name() + "/" + value );
            }

            for ( Attribute attribute : entityMask.getMasked().keySet() )
            {
                int value = entityMask.getMasked().get( attribute );
                experience.add( attribute.name() + "/" + value );
            }

            config.set( "players." + i + ".uuid", entityMask.getUuid().toString() );
            config.set( "players." + i + ".level", String.valueOf( entityMask.getLevel()  ) );
            config.set( "players." + i + ".skills", skills );
            config.set( "players." + i + ".experience", experience );
            config.set( "players." + i + ".attributes", attributes );
        } try
    {
        config.save( new File( path ) );
    } catch ( Exception ignored ) { }
    }

    private boolean scheduled = false;

    public void scheduleSave( long seconds)
    {
        scheduled = true;
        schedule( seconds );
    }

    private void schedule( long seconds )
    {
        long math = seconds * 20;
        if ( !scheduled ) {
            Bukkit.getScheduler().scheduleSyncDelayedTask( Infinitum.getInstance(), () ->
            {
                save();
                Infinitum.getInstance().getServer().broadcastMessage( " saving.." );
                scheduled = false;
            }, math );
        }
    }

    public void addNew( UUID uuid )
    {
        if ( masks.stream().noneMatch( playerMask -> playerMask.getUuid().equals( uuid ) ) )
        {
            HashMap<ExperienceType ,Integer> experience = new HashMap<>();
            experience.put( ExperienceType.BASE, 5 );
            experience.put( ExperienceType.COMBAT, 5 );
            experience.put( ExperienceType.CRAFTING, 5 );

            HashMap<String, Integer> skillTree = new HashMap<>();

            HashMap<Attribute, Integer> attributes = new HashMap<>();
            attributes.put( Attribute.HEALTH, 100 );
            attributes.put( Attribute.MAX_HEALTH, 100 );
            attributes.put( Attribute.DEFENSE, 0 );
            attributes.put( Attribute.INTELLIGENCE, 100 );

            attributes.put( Attribute.STRENGTH, 0 );
            attributes.put( Attribute.CRITICAl_CHANCE, 25 );
            attributes.put( Attribute.CRITICAL_DAMAGE, 0 );

            LivingEntity entity = ( LivingEntity ) Infinitum.getInstance().getServer().getEntity( uuid );

            masks.add( new EntityMask( entity, CustomType.PLAYER, 0 , skillTree, experience, attributes ) );
            save();
        }
    }

    public static PlayerSaving get() {
        return Infinitum.getInstance().getPlayerConfig();
    }
}
