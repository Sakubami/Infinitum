package xyz.sakubami.infinitum.player.level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.sakubami.infinitum.Infinitum;

import java.io.File;
import java.util.*;

public class PlayerConfig {

    private static class PlayerRecord {

        private final UUID uuid;
        private int level;
        private HashMap<String, Integer> skillTree;

        public PlayerRecord( UUID uuid, int level, HashMap<String, Integer> skillTree ) {
            this.uuid = uuid;
            this.level = level;
            this.skillTree = skillTree;
        }

        public UUID getUUID() { return uuid; }
        public int getLevel() { return level; }
        public HashMap<String, Integer> getSkillTree() { return skillTree; }

        public void setLevel( int level ) { this.level = level; }
        public void setSkillTree( HashMap<String, Integer> skillTree ) { this.skillTree = skillTree; }
    }

    private final String path = "plugins/Infinitum/Player/Players.yml";

    private ArrayList<PlayerRecord> playerRecords;

    public PlayerConfig()
    {
        playerRecords = new ArrayList<>();
        loadPlayers();
    }

    public void deletePlayer ( UUID uuid )
    {
        playerRecords.removeIf ( recipe -> recipe.uuid.equals( uuid ) );
        savePlayers();
    }

    public void addNewPlayer( UUID uuid )
    {
        for ( PlayerRecord playerRecord : playerRecords ) {
            if ( playerRecord.uuid.equals( uuid ) )
            {
                playerRecords.add( new PlayerRecord( uuid, 0 , new HashMap<>() ) );
                savePlayers();
            }
        }
    }

    public void loadPlayers()
    {
        playerRecords = new ArrayList<>();

        FileConfiguration config = YamlConfiguration.loadConfiguration( new File( path ) );

        for (int i = 0; true; i++)
        {
            if ( !config.contains( "players." + i ) )  break;

            HashMap<String, Integer> skillTree = new HashMap<>();
            UUID uuid = UUID.fromString( config.getString( "players." + i + ".uuid" ) );
            int level = Integer.parseInt( config.getString("players." +i+ ".primer" ) );
            List<String> skills = config.getStringList("players." +i+ ".skills" );

            for ( String line : skills )
            {
                String[] split = line.split( "/" );
                skillTree.put( split[0], Integer.parseInt( split[1] ) );
            }

            playerRecords.add( new PlayerRecord( uuid, level, skillTree ) );
        }
    }

    public void savePlayers()
    {

        FileConfiguration config = new YamlConfiguration();

        for ( int i = 0; playerRecords.size() > i; i++)
        {
            PlayerRecord playerRecord = playerRecords.get( i );
            List<String> list = new ArrayList<>();

            for ( String skill : playerRecord.skillTree.keySet() )
            {
                int value = playerRecord.skillTree.get( skill );
                list.add( skill + value );
            }

            config.set( "players." + i + ".uuid", playerRecord.uuid.toString() );
            config.set( "players." + i + ".level", String.valueOf( playerRecord.level  ) );
            config.set( "players." + i + ".skills", list );
        } try
    {
        config.save( new File( path ) );
    } catch ( Exception ignored ) { }
    }

    public static PlayerConfig get() {
        return Infinitum.getInstance().getPlayerConfig();
    }
}
