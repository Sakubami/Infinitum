package xyz.sakubami.infinitum.functionality.damaging;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import xyz.sakubami.infinitum.world.entities.deprecated.PlayerControl;
import xyz.sakubami.infinitum.world.entities.control.PlayerSaving;


public class DamageCalculator {

    private final int raw;
    private final boolean who;
    private final PlayerControl damager;
    private final LivingEntity receiver;

    private final PlayerSaving connector = PlayerSaving.get();

    // USAGE who = false == received, true == dealt

    public DamageCalculator( int raw, PlayerControl damager, LivingEntity receiver, boolean who )
    {
        this.raw = raw;
        this.damager = damager;
        this.receiver = receiver;
        this.who = who;
    }

    public int calculate()
    {
        if ( receiver instanceof Player )
        {
            return 0;
        }



        return 0;
    }
}
