package xyz.sakubami.infinitum.world.entities.player.interact.damaging;

import xyz.sakubami.infinitum.world.entities.player.Player;
import xyz.sakubami.infinitum.world.entities.player.PlayerController;

import javax.swing.text.html.parser.Entity;

public class DamageCalculator {

    private final int raw;
    private final boolean who;
    private final Player damager;
    private final Entity receiver;

    private final PlayerController playerController = PlayerController.get();

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

        /*
        final int ahp = playerController;
        final int aehp = ;
        final int defense;
        final int strength;

         */

        return raw;
    }
}
