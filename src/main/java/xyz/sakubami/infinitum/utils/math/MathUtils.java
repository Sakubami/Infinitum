package xyz.sakubami.infinitum.utils.math;

public class MathUtils {

    public int percentageOf( int v, int percentage )
    {
        float a = ( float ) percentage / 100;
        float b = ( v * a );
        return Math.round( b );
    }

    public int getPercentage( int v, int v2 )
    {
        int math = v / v2;
        return Math.round( math * 100 );
    }
}
