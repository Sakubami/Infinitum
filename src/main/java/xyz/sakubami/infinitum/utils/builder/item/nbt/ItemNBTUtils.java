package xyz.sakubami.infinitum.utils.builder.item.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.utils.builder.item.nbt.ItemNBTApi;

import java.util.ArrayList;
import java.util.List;

public class ItemNBTUtils {

    ItemNBTApi NBT = new ItemNBTApi();

    public boolean isProtected( ItemStack item )
    {
        if ( NBT.getNBTValue( item, "protected" ) != null )
            return Boolean.parseBoolean( NBT.getNBTValue( item, "protected" ) );
        return false;
    }

    public List<String> getEnchantments( ItemStack item )
    {
        List<String> list = new ArrayList<>();

        if ( NBT.getNBTValue( item, "enchanted" ) != null ) {

        }

        return list;
    }

    public boolean hasID( ItemStack item ) { return NBT.getNBTValue( item, "id") != null; }

    public String getID( ItemStack item ) {
        return NBT.getNBTValue( item, "id" );
    }

    public String getAbility( ItemStack item ) {
        return NBT.getNBTValue( item, "ability" );
    }

    public String getRarity( ItemStack item ) {
        return NBT.getNBTValue( item, "rarity" );
    }


    // vv ENTITIES vv

    private NamespacedKey key( String key ) { return new NamespacedKey( Infinitum.getInstance(), key ); }

    public String getNBTValue( Entity entity, String key )
    {
        return entity.getPersistentDataContainer().get( key( key ), PersistentDataType.STRING );
    }

    private boolean hasData(Entity entity) {
        return !entity.getPersistentDataContainer().isEmpty();
    }

    public int getHealth( Entity entity )
    {
        if ( hasData( entity ) )
            return Integer.parseInt( getNBTValue( entity, "health" ) );
        return -999 ;
    }
}
