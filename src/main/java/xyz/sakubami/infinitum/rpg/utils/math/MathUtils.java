package xyz.sakubami.infinitum.rpg.utils.math;

public class MathUtils {

    public int percentageOf( int v, int percentage )
    {
        return ( int ) Math.round( v * ( ( double ) percentage / 100 ) );
    }

    public int getPercentage( int v, int v2 )
    {
        int math = v / v2;
        return Math.round( math * 100 );
    }
}
