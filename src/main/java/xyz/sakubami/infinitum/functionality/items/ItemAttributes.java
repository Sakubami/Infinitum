package xyz.sakubami.infinitum.functionality.items;

public enum ItemAttributes
{
    NONE(
            ItemClass.GENERIC, 0, 0,  0, 0, 0, 0, 0 ),
    INFINITA_SCIENTIA(
            ItemClass.GENERIC, 300, -300,  4, 3, 2, 1, 2 );

    private final int damage;
    private final int strength;
    private final int critChance;
    private final int critDamage;

    private final int health;
    private final int def;
    private final int intelligence;

    private final ItemClass itemClass;

    ItemAttributes ( ItemClass itemClass , int damage, int critChance, int critDamage, int intelligence, int strength, int def, int health )
    {
        this.damage = damage;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.intelligence = intelligence;
        this.strength = strength;
        this.def = def;
        this.health = health;
        this.itemClass = itemClass;
    }

    public int getDamage() { return damage; }
    public int getCritDamage()
    {
        return critDamage;
    }
    public int getCritChance()
    {
        return critChance;
    }
    public int getIntelligence() { return intelligence; }
    public int getStrength() { return strength; }
    public int getDefense() { return def; }
    public int getHealth() { return health; }
    public ItemClass getItemClass() { return itemClass; }
}