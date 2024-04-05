package xyz.sakubami.infinitum.world.entities.player.skills.combat;

public enum EntityExperienceYield {
    WITHER( 17500 ),
    ENDER_DRAGON( 12500 );

    private final int yield;

    EntityExperienceYield( int yield )
    {
        this.yield = yield;
    }

    public int getYield() { return yield; }
}
