package xyz.sakubami.infinitum.builder.item;

import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.items.CustomItem;
import xyz.sakubami.infinitum.items.ItemCategory;
import xyz.sakubami.infinitum.items.ItemTier;

import java.util.ArrayList;
import java.util.List;

public class LoreBuilder {

    NBTUtils nbt = new NBTUtils();
    private final List<String> lore = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private final CustomItem item;
    private boolean material = true;
    private final ItemTier itemTier;

    public LoreBuilder( CustomItem item )
    {
        if ( item.getItemCategory().equals( ItemCategory.MATERIAL ) )
            this.material = false;
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
        int temp = 0;

        if ( item.getDamage() != 0 )
        {
            this.lore.add( "§7Schaden: §c" + formatted( item.getDamage() ) );
            temp += 1;
        }

        if ( item.getStrength() != 0 )
        {
            this.lore.add( "§7Stärke: §c" + formatted( item.getStrength() ) );
            temp += 1;
        }

        if ( item.getCritChance() != 0 )
        {
            this.lore.add( "§7Krit. Chance: §c" + item.getCritChance() );
            temp += 1;
        }

        if ( item.getCritDamage() != 0 )
        {
            this.lore.add( "§7Krit. Schaden: §c" + formatted( item.getCritDamage() ) );
            temp += 1;
        }

        if ( temp > 0 )
        {
            this.lore.add( "§0" );
            temp = 0;
        }

        if ( item.getDamage() != 0 )
        {
            this.lore.add( "§7Leben: §a" + formatted( item.getHealth() ) );
            temp += 1;
        }

        if ( item.getDefense() != 0 )
        {
            this.lore.add( "§7Rüstung: §a" + formatted( item.getDefense() ) );
            temp += 1;
        }

        if ( item.getIntelligence() != 0 )
        {
            this.lore.add( "§7Intelligenz: §a" + formatted( item.getIntelligence() ) );
            temp += 1;
        }

        if ( temp > 0 )
        {
            this.lore.add( "§0" );
        }
    }

    private String formatted( int number)
    {
        if ( Integer.signum( number ) == 0 )
            return "-" + number;
        else return "+" + number;
    }

    public List<String> build()
    {
        boolean attributes = false;

        if ( material )
        {
            initAttributes();
            attributes = true;
        }

        if ( !attributes )
            lore.add( "§0" );

        if ( !description.isEmpty() )
            lore.addAll( description );

        // enchants idk

        if ( material )
            lore.add( "§4" + item.getItemClass() );

        // show how well it was crafted


        lore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + item.getItemCategory().getTranslation() );

        return this.lore;
    }
}