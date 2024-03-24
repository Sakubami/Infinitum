package xyz.sakubami.infinitum.utils.worldedit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.xml.validation.Schema;
import java.io.File;

public enum SchematicType {

    TABLE ( "table" ),
    ALTAR ( "altar" );

    final String path = "plugins/Infinitum/Structures/";
    final String name;

    SchematicType( String name )
    {
        this.name = name;
    }

    public File getPath() { return new File(path + name + ".schem" ); }
}
