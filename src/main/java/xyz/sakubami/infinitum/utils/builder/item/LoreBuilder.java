package xyz.sakubami.infinitum.utils.builder.item;

import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.functionality.items.CustomItem;
import xyz.sakubami.infinitum.functionality.items.ItemCategory;
import xyz.sakubami.infinitum.functionality.items.ItemTier;

import java.util.ArrayList;
import java.util.List;

public class LoreBuilder {

    NBTUtils nbt = new NBTUtils();

    private final List<String> lore = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private final CustomItem item;
    private boolean toggle = true;
    private final ItemTier itemTier;

    public LoreBuilder( CustomItem item )
    {
        if ( item.getItemCategory().equals( ItemCategory.MATERIAL ) || item.getItemCategory().equals( ItemCategory.QUEST_ITEM ) )
            this.toggle = false;
        this.item = item;
        this.itemTier = item.getTier();
    }

    public LoreBuilder addDescription( boolean toggle )
    {
        String description = item.getLore();
        List<String> list = new ArrayList<>();
        String newDesc = description;

        for ( int i = 0; true ; i++ )
        {
            if ( newDesc.length() > 38 )
            {
                String split = newDesc.substring( 0, 38 ).strip();
                String remaining = newDesc.substring( 0, split.lastIndexOf( " " ) );
                list.add ( "§7" + remaining );
                newDesc = newDesc.substring ( split.lastIndexOf( " " ) ).strip();
            } else
            {
                list.add( "§7" + newDesc.strip() );
                break;
            }
        }

        list.add( "§0" );

        if ( toggle )
            this.description = list;

        return this;
    }

    public LoreBuilder addEnchantments()
    {

        return this;
    }

    private void initAttributes()
    {
        // TODO optimize with iteration and hashmap

        int temp = 0;

        if ( item.getDamage() != 0 )
        {
            this.lore.add( "§7Schaden: §c" + item.getDamage() );
            temp += 1;
        }

        if ( item.getStrength() != 0 )
        {
            this.lore.add( "§7Stärke: §c" + item.getStrength() );
            temp += 1;
        }

        if ( item.getCritChance() != 0 )
        {
            this.lore.add( "§7Krit. Chance: §c" + item.getCritChance() );
            temp += 1;
        }

        if ( item.getCritDamage() != 0 )
        {
            this.lore.add( "§7Krit. Schaden: §c" + item.getCritDamage() );
            temp += 1;
        }

        if ( temp > 0 )
        {
            this.lore.add( "§0" );
            temp = 0;
        }

        if ( item.getDamage() != 0 )
        {
            this.lore.add( "§7Leben: §a" + item.getHealth() );
            temp += 1;
        }

        if ( item.getDefense() != 0 )
        {
            this.lore.add( "§7Rüstung: §a" + item.getDefense() );
            temp += 1;
        }

        if ( item.getIntelligence() != 0 )
        {
            this.lore.add( "§7Intelligenz: §a" + item.getIntelligence() );
            temp += 1;
        }

        if ( temp > 0 )
        {
            this.lore.add( "§0" );
        }
    }

    public List<String> build()
    {
        boolean attributes = false;

        if ( toggle )
        {
            initAttributes();
            attributes = true;
        }

        if ( !attributes )
            lore.add( "§0" );

        if ( !description.isEmpty() )
            lore.addAll( description );

        // enchants idk

        if (toggle)
            lore.add( "§4" + item.getItemClass() );

        // show how well it was crafted


        lore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + item.getItemCategory().getTranslation() );

        return this.lore;
    }
}
