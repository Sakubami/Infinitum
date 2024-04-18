package xyz.sakubami.infinitum.rpg.world.functionality.items.components;

import xyz.sakubami.infinitum.rpg.world.functionality.Attribute;

import java.util.HashMap;

public enum ItemAttributes
{
    NONE(
            ItemClass.GENERIC, 0, 0,  0, 0, 0, 0, 0 ),
    INFINITA_SCIENTIA(
            ItemClass.GENERIC, 0, -300,  100, 0, 0, 0, 0 ),
    ADMIN(
            ItemClass.GENERIC, 1500, 1500,  100, 1500, 1500, 1500, 1500 );

    private final HashMap< Attribute, Integer > attributes = new HashMap<>();

    private final ItemClass itemClass;

    /**
     * TODO REPLACE WITH JSON FILES
     */
    ItemAttributes ( ItemClass itemClass , int damage, int strength, int criticalChance, int criticalDamage, int health, int defense, int intelligence )
    {
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

    public HashMap< Attribute, Integer > getAttributes() { return attributes; }
    public ItemClass getItemClass() { return itemClass; }
}