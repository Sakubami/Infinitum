package xyz.sakubami.infinitum;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.sakubami.infinitum.rpg.listeners.*;
import xyz.sakubami.infinitum.rpg.utils.builder.mob.CustomEntityBuilderUtils;
import xyz.sakubami.infinitum.rpg.world.entities.control.EntityConnector;
import xyz.sakubami.infinitum.rpg.world.functionality.crafting.Crafting;
import xyz.sakubami.infinitum.rpg.world.functionality.crafting.RecipeConfig;
import xyz.sakubami.infinitum.rpg.world.functionality.crafting.stations.LocationConfig;

import java.util.Random;

public class Infinitum extends JavaPlugin {

    private static Infinitum instance;

    private LocationConfig locationConfig;
    private RecipeConfig recipeConfig;
    private Crafting crafting;
    private EntityConnector entityConnector;
    private Random random;
    private CustomEntityBuilderUtils builderUtils;

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
        this.entityConnector = new EntityConnector();
        this.random = new Random();
        this.builderUtils = new CustomEntityBuilderUtils();

        // register stuff

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

    public EntityConnector getEntityConnector() { return entityConnector; }

    public Random getRandomGenerator() { return random; }

    public CustomEntityBuilderUtils getBuilderUtils() { return builderUtils; }
}