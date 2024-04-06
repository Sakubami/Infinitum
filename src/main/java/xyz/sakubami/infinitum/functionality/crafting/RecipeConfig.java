package xyz.sakubami.infinitum.functionality.crafting;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.functionality.crafting.stations.Primer;
import xyz.sakubami.infinitum.functionality.items.ItemTemplates;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecipeConfig {

    private record Recipe( List<String> items, Primer primer, ItemTemplates result ) { }

    private final String path = "plugins/Infinitum/Crafting/Recipes.yml";

    private ArrayList<Recipe> recipes;

    public RecipeConfig()
    {
        recipes = new ArrayList<>();
        loadRecipes();
    }

    public ItemTemplates craftRecipe(List<String> list, Primer primer )
    {
        for ( Recipe recipe : recipes )
        {
            if ( recipe.items.equals( list ) && recipe.primer.equals( primer ) )
            {
                return recipe.result();
            }
        }
        return null;
    }

    public void deleteRecipe ( List<String> items )
    {
        recipes.removeIf ( recipe -> recipe.items.equals( items ) );
        saveRecipes();
    }

    public void addRecipe( List<String> items, Primer primer, ItemTemplates result )
    {
        if ( !recipes.contains( new Recipe( items, primer, result ) ) )
        {
            recipes.add( new Recipe( items, primer, result ) );
            saveRecipes();
        }
    }

    public void loadRecipes()
    {
        recipes = new ArrayList<>();

        FileConfiguration config = YamlConfiguration.loadConfiguration( new File( path ) );

        for (int i = 0; true; i++)
        {
            if ( !config.contains( "recipes." + i ) ) break;

            List<String> list = config.getStringList( "recipes." + i + ".items");
            Primer primer = Primer.valueOf( config.getString("recipes." +i+ ".primer" ) );
            ItemTemplates result = ItemTemplates.valueOf( config.getString("recipes." +i+ ".result" ) );

            recipes.add( new Recipe( list, primer, result ) );
        }
    }

    public void saveRecipes()
    {

        FileConfiguration config = new YamlConfiguration();

        for ( int i = 0; recipes.size() > i; i++)
        {
            config.set( "recipes." + i + ".primer", recipes.get( i ).primer().toString() );
            config.set( "recipes." + i + ".result", recipes.get( i ).result().toString() );
            config.set( "recipes." + i + ".items", recipes.get( i ).items );
        } try
    {
        config.save( new File( path ) );
    } catch ( Exception ignored ) { }
    }

    public static RecipeConfig get() {
        return Infinitum.getInstance().getRecipeConfig();
    }
}
