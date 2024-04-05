package xyz.sakubami.infinitum.world.entities.player.skills;

public enum SkillLevel
{
    I( 50, 1 ), // - 50
    II( 125, 2 ), // - 175
    III( 200, 3 ), // - 375
    IV( 300, 4 ), // - 675
    V( 500, 5 ), // - 1175
    VI( 750, 6 ), // - 1925
    VII( 1000, 7 ), // - 2925
    VIII( 1500, 8 ), // - 4425
    IX( 2000, 9 ), // - 6425
    X( 3500, 10 ), // - 9925
    XI( 5000, 11 ), // - 14925
    XII( 7500, 12 ), // - 22425
    XIII( 10000, 13 ), // - 32425
    XIV( 15000, 14 ), // -
    XV( 20000, 15 ), // -
    XVI( 30000, 16 ), // -
    XVII( 50000, 17 ), // -
    XVIII( 75000, 18 ), // -
    XIX( 100000, 19 ), // -
    XX( 125000, 20 ), // -
    XXI( 175000, 21 ), // -
    XXII( 225000, 22 ), // -
    XXIII( 275000, 23 ), // -
    XXIV( 325000, 24 ), // -
    XXV( 400000, 25 ), // -
    XXVI( 475000, 26 ), // -
    XXVII( 550000, 27 ), // -
    XXVIII( 625000, 28 ), // -
    XXIX( 700000, 29 ), // -
    XXX( 775000, 30 ), // -
    XXXI( 850000, 31 ), // -
    XXXII( 925000, 32 ), // -
    XXXIII( 1000000, 33 ), // - 7747425
    XXXIV( 1100000, 34 ), // -
    XXXV( 1200000, 35 ), // -
    XXXVI( 1300000, 36 ), // -
    XXXVII( 1400000, 37 ), // -
    XXXVIII( 1500000, 38 ), // -
    XXXIX( 1600000, 39 ), // -
    XL( 1700000, 40 ), // -
    XLI( 1800000, 41 ), // -
    XLII( 1900000, 42 ), // -
    XLIII( 2000000, 43 ), // -
    XLIV( 2150000, 44 ), // -
    XLV( 2300000, 45 ), // -
    XLVI( 2450000, 46 ), // -
    XLVII( 2600000, 47 ), // -
    XLVIII( 2850000, 48 ), // -
    XLIX( 3000000, 49 ), // -
    L( 3200000, 50 ), // -
    LI( 3400000, 51 ), // -
    LII( 3600000, 52 ), // -
    LIII( 3800000, 53 ), // -
    LIV( 4000000, 54 ), // - 56597425
    LV( 4250000, 55 ), // -
    LVI( 4500000, 56 ), // -
    LVII( 4750000, 57 ), // -
    LVIII( 5000000, 58 ), // -
    LIX( 5250000, 59 ), // -
    LX( 5500000, 60 ), // -
    LXI( 5750000, 61 ), // -
    LXII( 6000000, 62 ), // -
    LXIII( 6250000, 63 ), // -
    LXIV( 6500000, 64 ), // -
    LXV( 6750000, 65 ), // -
    LXVI( 7000000, 66 ), // -
    LXVII( 7250000, 67 ), // -
    LXVIII( 7500000, 68 ), // -
    LXIX( 7750000, 69 ), // -
    LXX( 10000000, 70 ); // - 156597425 156,597,425

    private final int experience;
    private final int normal;

    SkillLevel( int experience, int normal)
    {
        this.experience = experience;
        this.normal = normal;
    }

    public int getExperience() { return experience; }
    public int getNormal() { return normal; }
}
