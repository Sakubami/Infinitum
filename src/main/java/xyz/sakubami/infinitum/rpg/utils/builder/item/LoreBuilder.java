package xyz.sakubami.infinitum.rpg.utils.builder.item;

import xyz.sakubami.infinitum.rpg.utils.NBTUtils;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItem;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemTier;

import java.util.ArrayList;
import java.util.List;

public class LoreBuilder {

    NBTUtils nbt = new NBTUtils();

    private final List<String> lore = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private final CustomItem item;
    private boolean addAttributes = true;
    private final ItemTier itemTier;

    public LoreBuilder( CustomItem item )
    {
        this.item = item;
        this.itemTier = item.getTier();
    }

    public LoreBuilder toggleAttributes( boolean toggle )
    {
        this.addAttributes = toggle;
        return this;
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

    public LoreBuilder update()
    {
        return this;
    }

    public List< String > build()
    {
        if ( item.getLoreAttributes().isEmpty() )
            this.addAttributes = false;

        if ( addAttributes )
        {
            int temp = 0;

            ArrayList< String > attributes = item.getLoreAttributes();

            String color = "§c";

            for ( String string : attributes )
            {
                String[] split = string.split( "/" );
                int v = Integer.parseInt( split[ 1 ] );
                Attribute attribute = Attribute.valueOf( split[ 0 ] );

                String component = "+" + v;

                if ( v < 0 )
                    component = String.valueOf( v );

                if ( v != 0 )
                {
                    this.lore.add( "§7" + attribute.getTranslation() + ": " + color + component );
                    temp += 1;
                    if ( temp >= 4 ) {
                        this.lore.add( "§0" );
                        color = "§a";
                        temp = 0;
                    }
                }
            }
        }
        this.lore.add( "§0" );

        if ( !description.isEmpty() )
            lore.addAll( description );

        // enchants idk

        //TODO change color if you can use it

        if ( addAttributes )
            lore.add( "§a" + item.getItemClass().getTranslation() );

        // show how well it was crafted


        lore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + item.getItemCategory().getTranslation() );

        return this.lore;
    }
}
