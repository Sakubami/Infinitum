package xyz.sakubami.infinitum.utils.worldedit;

import java.io.File;

public enum InfinitumSchematic {

    TABLE ( "table" ),
    TOWER ( "tower" ),
    ALTAR ( "altar" );

    final String path = "plugins/Infinitum/Structures/";
    final String name;

    InfinitumSchematic(String name )
    {
        this.name = name;
    }

    public File getPath() { return new File(path + name + ".schem" ); }
}
