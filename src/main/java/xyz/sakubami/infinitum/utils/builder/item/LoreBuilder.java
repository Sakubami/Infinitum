package xyz.sakubami.infinitum.utils.builder.item;

import org.apache.commons.lang.WordUtils;
import xyz.sakubami.infinitum.functionality.Attribute;
import xyz.sakubami.infinitum.utils.NBTUtils;
import xyz.sakubami.infinitum.functionality.items.ItemTemplates;
import xyz.sakubami.infinitum.functionality.items.ItemCategory;
import xyz.sakubami.infinitum.functionality.items.ItemTier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoreBuilder {

    NBTUtils nbt = new NBTUtils();

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



    public List<String> build()
    {
        if ( addAttributes )
        {
            int temp = 0;

            HashMap<Attribute, Integer> attributes = item.getAttributes();

            for ( Attribute attribute : attributes.keySet() )
            {
                this.lore.add( "§7" + attribute.getTranslation() + "§c" + attributes.get( attribute ) );
                temp += 1;
                if ( temp >= 4 ) {
                    this.lore.add( "§0" );
                    temp = 0;
                    break;
                }
            }
        } else
        {
            lore.add( "§0" );
        }

        if ( !description.isEmpty() )
            lore.addAll( description );

        // enchants idk

        if ( addAttributes )
            lore.add( "§4" + item.getItemClass().getTranslation() );

        // show how well it was crafted


        lore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + item.getItemCategory().getTranslation() );

        return this.lore;
    }
}
