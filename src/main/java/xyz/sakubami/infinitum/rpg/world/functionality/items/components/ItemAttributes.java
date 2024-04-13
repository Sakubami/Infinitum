package xyz.sakubami.infinitum.rpg.world.functionality.items.components;

import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;

import java.util.ArrayList;
import java.util.HashMap;

public enum ItemAttributes
{
    NONE(
            ItemClass.GENERIC, 0, 0,  0, 0, 0, 0, 0 ),
    INFINITA_SCIENTIA(
            ItemClass.GENERIC, 300, -300,  4, 3, 2, 1, 2 );

    private final HashMap<Attribute, Integer> attributes = new HashMap<>();
    private final ArrayList<String> loreAttributes = new ArrayList<>();

    private final ItemClass itemClass;

    /**
     * TODO REPLACE WITH JSON FILES
     */
    ItemAttributes ( ItemClass itemClass , int damage, int strength, int criticalChance, int criticalDamage, int health, int defense, int intelligence )
    {
        loreAttributes.add( "DAMAGE" + "/" + damage );
        loreAttributes.add( "STRENGTH" + "/" + strength );
        loreAttributes.add( "CRITICAl_CHANCE" + "/" + criticalChance );
        loreAttributes.add( "CRITICAL_DAMAGE" + "/" + damage );
        loreAttributes.add( "HEALTH" + "/" + damage );
        loreAttributes.add( "DEFENSE" + "/" + damage );
        loreAttributes.add( "INTELLIGENCE" + "/" + damage );
        attributes.put( Attribute.DAMAGE, damage );
        attributes.put( Attribute.STRENGTH, strength );
        attributes.put( Attribute.CRITICAL_CHANCE, criticalChance );
        attributes.put( Attribute.CRITICAL_DAMAGE, criticalDamage );
        attributes.put( Attribute.HEALTH, health );
        attributes.put( Attribute.DEFENSE, defense );
        attributes.put( Attribute.INTELLIGENCE, intelligence );
        attributes.put( Attribute.ADDITIVE, defense );
        attributes.put( Attribute.MULTIPLICATIVE, intelligence );
        this.itemClass = itemClass;
    }

    public HashMap<Attribute, Integer> getAttributes() { return attributes; }
    public ItemClass getItemClass() { return itemClass; }
}