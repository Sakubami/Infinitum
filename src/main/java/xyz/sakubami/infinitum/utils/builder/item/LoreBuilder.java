package xyz.sakubami.infinitum.utils.builder.item;

import xyz.sakubami.infinitum.utils.builder.item.nbt.ItemNBTUtils;
import xyz.sakubami.infinitum.world.functionality.Attribute;
import xyz.sakubami.infinitum.world.functionality.items.components.ItemTier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoreBuilder {

    ItemNBTUtils nbt = new ItemNBTUtils();

    private final List<String> lore = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private final ItemTemplates item;
    private boolean addAttributes = true;
    private final ItemTier itemTier;

    public LoreBuilder( ItemTemplates item )
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

        this.lore.add( "§0" );

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

    public List<String> build()
    {

        if ( addAttributes )
        {
            int temp = 0;

            HashMap<Attribute, Integer> attributes = item.getAttributes();

            String color = "§c";

            for ( Attribute attribute : attributes.keySet() )
            {
                int v = attributes.get( attribute );
                String component = "";

                if ( v > 0 )
                    component = "+" + v;

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
        else
            this.lore.add( "§0" );

        // enchants idk

        if ( addAttributes )
            lore.add( "§4" + item.getItemClass().getTranslation() );

        // show how well it was crafted


        lore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + item.getItemCategory().getTranslation() );

        return this.lore;
    }
}
