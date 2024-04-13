package xyz.sakubami.infinitum.world.entities.control;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.entities.player.skills.ExperienceType;
import xyz.sakubami.infinitum.world.functionality.Attribute;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Saving {

    private final String pathStart = "plugins/Infinitum/PlayerControl/Players/";

    public boolean loadFromConfig( UUID uuid )
    {
        EntityConnector connector = EntityConnector.get();

        FileConfiguration config = YamlConfiguration.loadConfiguration( new File( pathStart + uuid.toString() + ".yml" ) );

        if ( config.getString( "id") == null )
            return true;

        String name = config.getString( "id");
        HashMap< String, Integer > skills = new HashMap<>();
        HashMap<ExperienceType, Integer > experience = new HashMap<>();
        HashMap<Attribute, Integer > attributes = new HashMap<>();
        int level = config.getInt("level" );
        List< String > cfgSkills = config.getStringList("skills" );
        List< String > cfgExperience = config.getStringList( "experience" );
        List< String > cfgAttributes = config.getStringList( "attributes" );

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

        // save individual players to own config file each time remove the loop etc

        LivingEntity entity = ( LivingEntity ) Infinitum.getInstance().getServer().getEntity( uuid );
        connector.add( new EntityMask( name, entity, CustomType.PLAYER,  level, skills, experience, attributes ) );
        return false;
    }

    public void saveToConfig( UUID uuid )
    {
        FileConfiguration config = new YamlConfiguration();
        EntityConnector connector = EntityConnector.get();

        EntityMask entityMask = connector.get( uuid );
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
            attributes.add( attribute.name() + "/" + value );
        }

        config.set( "id", entityMask.getName() );
        config.set( "level", String.valueOf( entityMask.getLevel()  ) );
        config.set( "skills", skills );
        config.set( "experience", experience );
        config.set( "attributes", attributes );
        try
        {
            config.save( new File( pathStart + uuid.toString() + ".yml" ) );
        }
        catch ( Exception ignored ) { }
    }

    public void addNew( UUID uuid )
    {
        Infinitum.getInstance().getServer().broadcastMessage(" added new bozo ");

        EntityConnector connector = EntityConnector.get();

        if ( connector.get( uuid ) != null )
            return;

        HashMap< ExperienceType ,Integer> experience = new HashMap<>();
        experience.put( ExperienceType.BASE, 5 );
        experience.put( ExperienceType.COMBAT, 5 );
        experience.put( ExperienceType.CRAFTING, 5 );

        HashMap< String, Integer> skillTree = new HashMap<>();

        HashMap< Attribute, Integer> attributes = new HashMap<>();
        attributes.put( Attribute.HEALTH, 100 );
        attributes.put( Attribute.MAX_HEALTH, 100 );
        attributes.put( Attribute.DEFENSE, 0 );
        attributes.put( Attribute.INTELLIGENCE, 100 );

        attributes.put( Attribute.STRENGTH, 0 );
        attributes.put( Attribute.CRITICAL_CHANCE, 25 );
        attributes.put( Attribute.CRITICAL_DAMAGE, 0 );

        LivingEntity entity = ( LivingEntity ) Infinitum.getInstance().getServer().getEntity( uuid );

        connector.add( new EntityMask( entity.getName(), entity, CustomType.PLAYER, 0 , skillTree, experience, attributes ) );
        saveToConfig( uuid );
    }
}
