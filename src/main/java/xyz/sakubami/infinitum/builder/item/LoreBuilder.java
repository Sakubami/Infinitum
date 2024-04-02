package xyz.sakubami.infinitum.builder.item;

import xyz.sakubami.infinitum.items.CustomItem;
import xyz.sakubami.infinitum.items.ItemCategory;
import xyz.sakubami.infinitum.items.ItemTier;

import java.util.ArrayList;
import java.util.List;

public class LoreBuilder {

    // components
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

    public LoreBuilder addDescription()
    {
        String description = item.getLore();
        List<String> list = new ArrayList<>();
        String newDesc = description;

        for ( int i = 38; true; i += 38 )
        if ( newDesc.length() > i )
        {
            String split = description.substring( 0, i );
            String remaining = description.substring( 0, split.lastIndexOf( " " ) );
            list.add ( remaining );
            newDesc = description.substring ( split.lastIndexOf( " " ) );

        } else
        {
            list.add( newDesc );
            break;
        }

        this.description = list;

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
            this.lore.add( "§7Krit. Chance: §c" + formatted( item.getCritChance() ) );
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
        if ( !material )
            initAttributes();
        if ( !description.isEmpty() )
            lore.addAll( description );

        // enchants idk

        if ( !material )
            lore.add( "§4" + item.getItemClass() );

        lore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + item.getItemCategory().getTranslation() );

        return this.lore;
    }
}
