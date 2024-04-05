package xyz.sakubami.infinitum.world.entities.player.interact.damaging;

import xyz.sakubami.infinitum.world.entities.player.Player;

import javax.swing.text.html.parser.Entity;

public class DamageCalculator {

    private final int raw;
    private final boolean who;
    private final Player damager;
    private final Entity receiver;

    // USAGE who = false == received, true == dealt

    public DamageCalculator( int raw, Player damager, Entity receiver, boolean who )
    {
        this.raw = raw;
        this.damager = damager;
        this.receiver = receiver;
        this.who = who;
    }

    public int calculate()
    {
        // make this compatible with custom entities that im gonna add later on

        final int ahp = 1;
        final int bhp = 1;
        final int effectiveHealth;
        final int defense;
        final int strength;
        
        return raw;
    }
}
