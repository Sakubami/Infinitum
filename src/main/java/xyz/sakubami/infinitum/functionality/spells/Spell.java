package xyz.sakubami.infinitum.functionality.spells;

import xyz.sakubami.infinitum.functionality.Attribute;

public enum Spell {
    NONE,
    FIRE;

    private final int additive;
    private final int multiplicative;

    Spell( int additive, int multiplicative )
    {
        this.additive = additive;
        this.multiplicative = multiplicative;
    }

    public int getAdditive() { return additive; }
    public int getMultiplicative() { return multiplicative; }
}
