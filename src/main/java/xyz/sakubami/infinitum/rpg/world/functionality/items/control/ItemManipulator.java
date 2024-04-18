package xyz.sakubami.infinitum.rpg.world.functionality.items.control;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.sakubami.infinitum.rpg.utils.builder.item.ItemBuilder;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemTier;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;
import xyz.sakubami.infinitum.rpg.world.functionality.items.reforging.Reforge;

import java.util.HashMap;

public class ItemManipulator {

    private final ItemMask itemMask;
    private final ItemArchive archive = ItemArchive.get();
    private final HashMap< ItemMask, Boolean > queue = new HashMap<>();
    private Player player;
    private boolean destroy = false;

    public ItemManipulator( ItemMask itemMask )
    {
        this.itemMask = itemMask;
    }

    public ItemManipulator( ItemStack itemStack )
    {
        this.itemMask = archive.get( itemStack );
    }

    public ItemManipulator( ItemStack itemStack, Player player )
    {
        this.player = player;
        this.itemMask = archive.get( itemStack );
    }

    public ItemManipulator reforge( Reforge reforge )
    {
        itemMask.setReforge( reforge );
        return this;
    }

    public ItemManipulator setTier( ItemTier tier )
    {
        itemMask.setTier( tier );
        return this;
    }

    public ItemManipulator removeReforge()
    {
        itemMask.setReforge( Reforge.NONE );
        return this;
    }

    public ItemManipulator addEnchant( CustomEnchantment enchantment, int v )
    {
        if ( !itemMask.getEnchantments().containsKey( enchantment ) )
            itemMask.getEnchantments().put( enchantment, v );
        return this;
    }

    public ItemManipulator levelUpEnchant( CustomEnchantment enchantment, int v )
    {
        if ( !itemMask.getEnchantments().containsKey( enchantment ) )
            return this;
        int v1 = v + itemMask.getEnchantments().get( enchantment );
        itemMask.getEnchantments().put( enchantment, v1 );
        return this;
    }

    public ItemManipulator removeEnchant( CustomEnchantment enchantment )
    {
        itemMask.getEnchantments().remove( enchantment );
        return this;
    }

    public ItemManipulator addAttribute( Attribute attribute, int v )
    {
        itemMask.getAttributes().put( attribute, v );
        return this;
    }

    public ItemManipulator setAttribute( Attribute attribute, int v )
    {
        itemMask.getAttributes().replace( attribute, v );
        return this;
    }

    public ItemManipulator destroy()
    {
        this.destroy = true;
        this.player.getInventory().getItemInMainHand().setAmount( 0 );
        queue.put( itemMask, true );
        return this;
    }

    public void queue()
    {
        if ( !destroy )
            player.getInventory().setItemInMainHand( new ItemBuilder( itemMask ).build() );

        for ( ItemMask mask : queue.keySet() )
        {
            if ( archive.contains( mask ) )
                archive.update( mask );

            if ( queue.get( mask ) )
                archive.remove( mask );
        }
    }
}
