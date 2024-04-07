package xyz.sakubami.infinitum;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.sakubami.infinitum.functionality.crafting.Crafting;
import xyz.sakubami.infinitum.functionality.crafting.RecipeConfig;
import xyz.sakubami.infinitum.functionality.crafting.stations.LocationConfig;
import xyz.sakubami.infinitum.listeners.*;
import xyz.sakubami.infinitum.world.entities.mob.MobConnector;
import xyz.sakubami.infinitum.world.entities.player.PlayerConnector;

public class Infinitum extends JavaPlugin {

    private static Infinitum instance;

    private LocationConfig locationConfig;
    private RecipeConfig recipeConfig;
    private Crafting crafting;
    private PlayerConnector playerConnector;
    private MobConnector mobConnector;

    public void onEnable() {
        instance = this;

        //register listeners
        Bukkit.getPluginManager().registerEvents( new EntityKill(), this );
        Bukkit.getPluginManager().registerEvents( new Interact(), this );
        Bukkit.getPluginManager().registerEvents( new Combust(), this );
        Bukkit.getPluginManager().registerEvents( new PlayerInteractServer(), this );
        Bukkit.getPluginManager().registerEvents( new Damage(), this );

        //load configs
        this.locationConfig = new LocationConfig();
        this.recipeConfig = new RecipeConfig();
        this.crafting = new Crafting();
        this.playerConnector = new PlayerConnector();
        this.mobConnector = new MobConnector();

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §aeingeschaltet!");
    }

    public void onDisable() {

        //saving

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §causgeschaltet!");
    }

    public static Infinitum getInstance() { return instance; }

    public LocationConfig getLocationConfig() { return locationConfig; }

    public RecipeConfig getRecipeConfig() { return recipeConfig; }

    public Crafting getCrafting() { return crafting; }

    public PlayerConnector getPlayerConfig() { return playerConnector; }

    public MobConnector getMobConnector() { return mobConnector; }
}