package xyz.sakubami.infinitum.world.entities.player;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.world.entities.player.skills.ExperienceType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class PlayerConfig {

    private static class PlayerRecord {

        private final UUID uuid;
        private int level;
        private HashMap<ExperienceType, Integer> experience;
        private HashMap<String, Integer> skillTree;

        public PlayerRecord( UUID uuid, int level, HashMap<String, Integer> skillTree, HashMap<ExperienceType, Integer> experience ) {
            this.uuid = uuid;
            this.level = level;
            this.skillTree = skillTree;
            this.experience = experience;
        }

        public UUID getUUID() { return uuid; }
        public int getLevel() { return level; }
        public HashMap<String, Integer> getSkillTree() { return skillTree; }
        public HashMap<ExperienceType, Integer> getExperience() { return experience; }

        public void setLevel( int level ) { this.level = level; }
        public void setSkillTree( HashMap<String, Integer> skillTree ) { this.skillTree = skillTree; }
        public void setExperience ( HashMap<ExperienceType, Integer> experience ) { this.experience = experience; }
    }

    private final String path = "plugins/Infinitum/Player/Players.yml";

    private ArrayList<PlayerRecord> playerRecords;

    public PlayerConfig()
    {
        playerRecords = new ArrayList<>();
        loadPlayers();
    }

    public void loadPlayers()
    {
        playerRecords = new ArrayList<>();

        FileConfiguration config = YamlConfiguration.loadConfiguration( new File( path ) );

        for (int i = 0; true; i++)
        {
            if ( !config.contains( "players." + i ) )  break;

            HashMap<String, Integer> skills = new HashMap<>();
            HashMap<ExperienceType, Integer> experience = new HashMap<>();
            UUID uuid = UUID.fromString( config.getString( "players." + i + ".uuid" ) );
            int level = Integer.parseInt( config.getString("players." +i+ ".level" ) );
            List<String> cfgSkills = config.getStringList("players." +i+ ".skills" );
            List<String> cfgExperience = config.getStringList( "players." +i+ ".experience" );

            for ( String line : cfgSkills )
            {
                String[] split = line.split( "/" );
                skills.put( split[0], Integer.parseInt( split[1] ) );
            }

            for ( String line : cfgExperience )
            {
                String[] split = line.split( "/" );
                experience.put( ExperienceType.valueOf( split[0] ), Integer.parseInt( split[1] ) );
            }

            playerRecords.add( new PlayerRecord( uuid, level, skills, experience ) );
        }
    }

    public void savePlayers()
    {

        FileConfiguration config = new YamlConfiguration();

        for ( int i = 0; playerRecords.size() > i; i++)
        {
            PlayerRecord playerRecord = playerRecords.get( i );
            List<String> skills = new ArrayList<>();
            List<String> experience = new ArrayList<>();

            for ( String skill : playerRecord.skillTree.keySet() )
            {
                int value = playerRecord.skillTree.get( skill );
                skills.add( skill + "/" + value );
            }

            for ( ExperienceType experienceType : playerRecord.experience.keySet() )
            {
                int value = playerRecord.experience.get( experienceType );
                    experience.add( experienceType.name() + "/" + value );
            }

            config.set( "players." + i + ".uuid", playerRecord.uuid.toString() );
            config.set( "players." + i + ".level", String.valueOf( playerRecord.level  ) );
            config.set( "players." + i + ".skills", skills );
            config.set( "players." + i + ".experience", experience );
        } try
    {
        config.save( new File( path ) );
    } catch ( Exception ignored ) { }
    }

    private boolean scheduled = false;

    public void scheduleSave()
    {
        scheduled = true;
    }

    // TODO replace with queue create new and check for when present etc to avoid useless tasks

    public void autoSave( long seconds )
    {
        long math = seconds * 20;
        Bukkit.getScheduler().scheduleSyncRepeatingTask( Infinitum.getInstance(), () ->
        {
            if ( scheduled )
            {
                savePlayers();
                Infinitum.getInstance().getServer().broadcastMessage( " saving.." );
                scheduled = false;
            }
        }, math, math);
    }

    public void deletePlayer ( UUID uuid )
    {
        playerRecords.removeIf ( recipe -> recipe.uuid.equals( uuid ) );
        savePlayers();
    }

    public void addNewPlayer( UUID uuid )
    {
        if ( playerRecords.stream().noneMatch( playerRecord -> playerRecord.uuid.equals( uuid ) ) )
        {
            HashMap<ExperienceType ,Integer> experience = new HashMap<>();
            experience.put( ExperienceType.BASE, 5 );
            experience.put( ExperienceType.COMBAT, 5 );
            experience.put( ExperienceType.CRAFTING, 5 );

            HashMap<String, Integer> skillTree = new HashMap<>();

            playerRecords.add( new PlayerRecord( uuid, 0 , skillTree, experience ) );
            savePlayers();
        }
    }

    private PlayerRecord findPLayer( UUID uuid ) { return playerRecords.stream().filter( record -> record.uuid.equals( uuid ) ).findFirst().get(); }

    public void levelUp( UUID uuid )
    {
        PlayerRecord record = playerRecords.stream().filter( rcd -> rcd.uuid.equals( uuid ) ).findFirst().get();
        record.setLevel( record.getLevel() +1 );
    }

    public int getPlayerLevel( UUID uuid )
    {
        return findPLayer( uuid ).level;
    }

    public HashMap<String, Integer> getPlayerSkillTree( UUID uuid )
    {
        return findPLayer( uuid ).skillTree;
    }

    public HashMap<ExperienceType, Integer> getPlayerExperience( UUID uuid )
    {
        return findPLayer( uuid ).experience;
    }

    public void updateExperience( UUID uuid, ExperienceType type, int exp )
    {
        findPLayer( uuid ).experience.put( type, exp );
    }

    public void addExperience( UUID uuid, ExperienceType type, int exp )
    {
        int experience = findPLayer( uuid ).experience.get( type );
        findPLayer( uuid ).experience.put( type, exp + experience );
    }

    public void setExperience( UUID uuid, ExperienceType type, int exp )
    {
        findPLayer( uuid ).experience.put( type, exp );
    }

    public static PlayerConfig get() {
        return Infinitum.getInstance().getPlayerConfig();
    }
}
