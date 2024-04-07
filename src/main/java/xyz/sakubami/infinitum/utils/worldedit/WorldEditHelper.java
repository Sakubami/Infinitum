package xyz.sakubami.infinitum.utils.worldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.Direction;
import com.sk89q.worldedit.util.SideEffectSet;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import xyz.sakubami.infinitum.Infinitum;

import java.io.File;
import java.io.FileInputStream;

public class WorldEditHelper {

    public static void Paste( Location location, File schematicFile, Player player )
    {
        try ( EditSession session = createEditSession( location.getWorld() ) )
        {
            ClipboardFormat format = ClipboardFormats.findByFile( schematicFile );
            ClipboardReader reader = format.getReader(new FileInputStream( schematicFile ) );

            Clipboard schematic = reader.read();

            ClipboardHolder holder = new ClipboardHolder( schematic );
            holder.setTransform( new AffineTransform().rotateY( rotationHelper ( player.getFacing() ) ) );

            Operation operation = holder

                    .createPaste( session )
                    .to( BukkitAdapter.asBlockVector( location ) )
                    .ignoreAirBlocks( true )
                    .build();
            Operations.complete( operation );

        } catch ( final  Throwable t )
        {
            t.printStackTrace();
        }
    }

    public static void pasteSchematic( Location location, File schematicFile )
    {
        try ( EditSession session = createEditSession( location.getWorld() ) )
        {
            ClipboardFormat format = ClipboardFormats.findByFile( schematicFile );
            ClipboardReader reader = format.getReader(new FileInputStream( schematicFile ) );

            Clipboard schematic = reader.read();

            Operation operation = new ClipboardHolder( schematic )
                    .createPaste( session )
                    .to( BukkitAdapter.asBlockVector( location ) )
                    .build();
            Operations.complete( operation );

        } catch ( final  Throwable t )
        {
            t.printStackTrace();
        }
    }

    private static EditSession createEditSession(World bukkitWorld)
    {
        final EditSession session = WorldEdit.getInstance().newEditSession( BukkitAdapter.adapt( bukkitWorld ) );

        session.setSideEffectApplier( SideEffectSet.defaults() );
        return session;
    }

    private static int rotationHelper( BlockFace blockFace )
    {
        int rotation = 0;
        if ( blockFace.equals( BlockFace.NORTH ) )
            rotation = 90;
        if ( blockFace.equals( BlockFace.WEST ) )
            rotation = 180;
        if ( blockFace.equals( BlockFace.SOUTH ) )
            rotation = 270;
        return rotation;
    }

    public static Location fixTableLocation( Location location, BlockFace blockFace )
    {
        int x = location.getBlockX();
        int z = location.getBlockZ();

        if ( blockFace.equals( BlockFace.NORTH ) )
            x -= 1;
        if ( blockFace.equals( BlockFace.WEST ) )
            z += 1;
        if ( blockFace.equals( BlockFace.SOUTH ) )
            x += 1;
        if ( blockFace.equals( BlockFace.EAST ) )
            z -= 1;
        return new Location ( location.getWorld(), x, location.getBlockY() +1, z );
    }
}


