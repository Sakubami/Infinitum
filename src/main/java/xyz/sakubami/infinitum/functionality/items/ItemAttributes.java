package xyz.sakubami.infinitum.functionality.items;

import xyz.sakubami.infinitum.functionality.Attribute;

import java.util.HashMap;

public enum ItemAttributes
{
    NONE(
            ItemClass.GENERIC, 0, 0,  0, 0, 0, 0, 0 ),
    INFINITA_SCIENTIA(
            ItemClass.GENERIC, 300, -300,  4, 3, 2, 1, 2 );

    private final HashMap<Attribute, Integer> attributes = new HashMap<>();

    private final ItemClass itemClass;

    ItemAttributes ( ItemClass itemClass , int damage, int criticalChance, int criticalDamage, int intelligence, int strength, int defense, int health )
    {
        attributes.put( Attribute.DAMAGE, damage );
        attributes.put( Attribute.STRENGTH, strength );
        attributes.put( Attribute.CRITICAl_CHANCE, criticalChance );
        attributes.put( Attribute.CRITICAL_DAMAGE, criticalDamage );
        attributes.put( Attribute.HEALTH, health );
        attributes.put( Attribute.DEFENSE, defense );
        attributes.put( Attribute.INTELLIGENCE, intelligence );
        this.itemClass = itemClass;
    }

    public HashMap<Attribute, Integer> getAttributes() { return attributes; }
    public ItemClass getItemClass() { return itemClass; }
}