package xyz.sakubami.infinitum;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.sakubami.infinitum.crafting.Crafting;
import xyz.sakubami.infinitum.crafting.RecipeConfig;
import xyz.sakubami.infinitum.crafting.stations.LocationConfig;
import xyz.sakubami.infinitum.listeners.EntityKill;
import xyz.sakubami.infinitum.listeners.Interact;
import xyz.sakubami.infinitum.listeners.Combust;
import xyz.sakubami.infinitum.listeners.OpenBook;
import xyz.sakubami.infinitum.player.level.PlayerConfig;

public class Infinitum extends JavaPlugin {

    private static Infinitum instance;

    private LocationConfig locationConfig;
    private RecipeConfig recipeConfig;
    private Crafting crafting;
    private PlayerConfig playerConfig;

    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents( new EntityKill(), this );
        Bukkit.getPluginManager().registerEvents( new Interact(), this );
        Bukkit.getPluginManager().registerEvents( new Combust(), this );
        Bukkit.getPluginManager().registerEvents( new OpenBook(), this );

        this.locationConfig = new LocationConfig();
        this.recipeConfig = new RecipeConfig();
        this.crafting = new Crafting();
        this.playerConfig = new PlayerConfig();

        instance.getServer().broadcastMessage("§dInfinitum §7wurde §aeingeschaltet!");
    }

    public void onDisable() {
        instance.getServer().broadcastMessage("§dInfinitum §7wurde §causgeschaltet!");
    }

    public static Infinitum getInstance() { return instance; }

    public LocationConfig getLocationConfig() { return locationConfig; }

    public RecipeConfig getRecipeConfig() { return recipeConfig; }

    public Crafting getCrafting() { return crafting; }

    public PlayerConfig getPlayerConfig() { return playerConfig; }
}