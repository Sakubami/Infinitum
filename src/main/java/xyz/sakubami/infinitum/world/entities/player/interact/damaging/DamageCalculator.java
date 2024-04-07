package xyz.sakubami.infinitum.world.entities.player.interact.damaging;

import xyz.sakubami.infinitum.world.entities.player.PlayerControl;
import xyz.sakubami.infinitum.world.entities.player.PlayerConnector;

import javax.swing.text.html.parser.Entity;

public class DamageCalculator {

    private final int raw;
    private final boolean who;
    private final PlayerControl damager;
    private final Entity receiver;

    private final PlayerConnector playerConnector = PlayerConnector.get();

    // USAGE who = false == received, true == dealt

    public DamageCalculator(int raw, PlayerControl damager, Entity receiver, boolean who )
    {
        this.raw = raw;
        this.damager = damager;
        this.receiver = receiver;
        this.who = who;
    }

    public int calculate()
    {
        // make this compatible with custom entities that im gonna add later on

        /*
        final int ahp = playerConnector;
        final int aehp = ;
        final int defense;
        final int strength;

         */

        return raw;
    }
}
