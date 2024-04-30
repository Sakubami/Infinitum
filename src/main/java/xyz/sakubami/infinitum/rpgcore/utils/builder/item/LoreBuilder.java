package xyz.sakubami.infinitum.rpgcore.utils.builder.item;

import xyz.sakubami.infinitum.Infinitum;
import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.CustomItemTemplate;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemCategory;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemClass;
import xyz.sakubami.infinitum.rpg.world.functionality.items.components.ItemTier;
import xyz.sakubami.infinitum.rpg.world.functionality.items.control.ItemMask;
import xyz.sakubami.infinitum.rpg.world.functionality.items.enchanting.CustomEnchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoreBuilder {
    private final List< String > newLore = new ArrayList<>();
    private List< String > description = new ArrayList<>();
    private final String oldLore;
    private final ItemCategory itemCategory;
    private String enchantments;
    private boolean compactEnchantments = true;
    private final ItemClass itemClass;
    private final List< String > loreAttributes;
    private boolean addAttributes = true;
    private final ItemTier itemTier;
    private int enchantmentLength;

    public LoreBuilder( CustomItemTemplate item )
    {
        this.itemCategory = item.getItemCategory();
        this.oldLore = item.getLore();
        this.itemClass = item.getItemClass();
        this.itemTier = item.getTier();
        this.loreAttributes = convertAttributes( item.getAttributes() );
    }

    // rework with new item library and maybe add item masks like entity masks to get the attributes n shit for update purposes

    public LoreBuilder( ItemMask item )
    {
        this.oldLore = item.getDescription();
        this.itemTier = item.getTier();
        this.itemCategory = item.getItemCategory();
        this.itemClass = item.getItemClass();
        this.loreAttributes = convertAttributes( item.getAttributes() );

        StringBuilder stringBuilder = new StringBuilder();
        if ( !item.getEnchantments().isEmpty() )
        {
            this.enchantmentLength = item.getEnchantments().size();
            if ( item.getEnchantments().size() > 6 )
            {
                this.compactEnchantments = false;
            }
            for ( CustomEnchantment enchantment : item.getEnchantments().keySet() )
                stringBuilder.append ( enchantment.getDisplayName() ).append( "$" ).append( item.getEnchantments().get( enchantment ) ).append( ", " );

            this.enchantments = stringBuilder.toString();
        }

    }

    public LoreBuilder toggleAttributes( boolean toggle )
    {
        this.addAttributes = toggle;
        return this;
    }

    public LoreBuilder addDescription( boolean toggle )
    {
        if ( !toggle )
            return this;

        List< String > list = new ArrayList<>();
        String newDesc = oldLore;

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

        this.description = list;

        return this;
    }

    public List< String > build()
    {
        if ( this.loreAttributes.isEmpty() )
            this.addAttributes = false;

        if ( addAttributes )
        {
            int temp = 0;

            String color = "§c";

            for ( String string : loreAttributes)
            {
                String[] split = string.split( "/" );
                int v = Integer.parseInt( split[ 1 ] );
                Attribute attribute = Attribute.valueOf( split[ 0 ] );

                String component = "+" + v;

                if ( v < 0 )
                    component = String.valueOf( v );

                if ( v != 0 )
                {
                    this.newLore.add( "§7" + attribute.getTranslation() + ": " + color + component );
                    temp += 1;
                    if ( temp >= 4 ) {
                        color = "§a";
                        temp = 0;
                    }
                }
            }
        }
        this.newLore.add( "§0" );

        if ( enchantments != null )
        {
            if ( compactEnchantments )
            {
                for ( int i = 0; i < enchantmentLength; i++ )
                {
                    String single = enchantments.substring( 0, enchantments.indexOf( "," ) ).strip();
                    newLore.add( "§9" + single.replace("$", " ") );
                    enchantments = enchantments.substring( single.length() + 2 );

                    Infinitum.getInstance().getServer().broadcastMessage( "LOOP ONE -> " + i + single + enchantments );
                }
            } else
                for ( int i = 0; true ; i++ )
                {
                    if ( enchantments.length() > 38 )
                    {
                        String split = enchantments.substring( 0, 38 ).strip();
                        String remaining = enchantments.substring( 0, split.lastIndexOf( "," ) );
                        newLore.add ( "§9" + remaining.replace("$", " ") );
                        enchantments = enchantments.substring ( split.lastIndexOf( " " ) ).strip();

                        Infinitum.getInstance().getServer().broadcastMessage( "LOOP TWO -> " + i );
                    } else
                    {
                        newLore.add( "§9" + enchantments.substring( 0, enchantments.lastIndexOf( "," ) ).replace("$", " ").strip() );
                        break;
                    }
                }
            this.newLore.add( "§0" );
        }

        if ( !description.isEmpty() )
            newLore.addAll( description );

        //TODO change color if you can use it

        if ( addAttributes )
            newLore.add( "§a" + itemClass.getTranslation() );

        // show how well it was crafted

        newLore.add( "§f§lSTUFE " + itemTier.getColor() + "§l" + itemTier.name() + " §f§l" + itemCategory.getTranslation().toUpperCase() );

        return this.newLore;
    }

    private List< String > convertAttributes( Map< Attribute, Integer > attributes )
    {
        List< String > output = new ArrayList<>();
        if ( attributes.get( Attribute.DAMAGE ) != 0 )
            output.add( "DAMAGE" + "/" + attributes.get( Attribute.DAMAGE ) );
        if ( attributes.get( Attribute.STRENGTH ) != 0 )
            output.add( "STRENGTH" + "/" + attributes.get( Attribute.STRENGTH ) );
        if ( attributes.get( Attribute.CRITICAL_CHANCE ) != 0 )
            output.add( "CRITICAL_CHANCE" + "/" + attributes.get( Attribute.CRITICAL_CHANCE ) );
        if ( attributes.get( Attribute.CRITICAL_DAMAGE ) != 0 )
            output.add( "CRITICAL_DAMAGE" + "/" + attributes.get( Attribute.CRITICAL_DAMAGE ) );
        if ( attributes.get( Attribute.HEALTH ) != 0 )
            output.add( "HEALTH" + "/" + attributes.get( Attribute.HEALTH ) );
        if ( attributes.get( Attribute.DEFENSE ) != 0 )
            output.add( "DEFENSE" + "/" + attributes.get( Attribute.DEFENSE ) );
        if ( attributes.get( Attribute.INTELLIGENCE ) != 0 )
            output.add( "INTELLIGENCE" + "/" + attributes.get( Attribute.INTELLIGENCE ) );
        return output;
    }
}
