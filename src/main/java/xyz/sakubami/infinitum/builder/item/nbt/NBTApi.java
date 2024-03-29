package xyz.sakubami.infinitum.builder.item.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.sakubami.infinitum.Infinitum;

import java.util.HashMap;

public class NBTApi {

    private final HashMap<NamespacedKey, String> list = new HashMap<>();
    private NamespacedKey key(String key) { return new NamespacedKey( Infinitum.getInstance(), key); }

    // WARNING dont use outside itembuilder

    public void extractNBTData(ItemMeta meta) {
        for (NamespacedKey key: meta.getPersistentDataContainer().getKeys()) {
            list.put(key, meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        }
    }

    public void addAllNBTTagList(HashMap<NamespacedKey, String> list) {
        this.list.putAll(list);
    }

    public void addNBTTag(NamespacedKey key, String value) {
        this.list.put(key, value);
    }

    public ItemMeta parseAllNBTTags(ItemMeta meta) {
        for (NamespacedKey key : this.list.keySet()) {
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, this.list.get(key));
        }
        return meta;
    }

    //

    public String getNBTValue(ItemStack item, String key) {
        return item.getItemMeta().getPersistentDataContainer().get(key(key), PersistentDataType.STRING);
    }

    public HashMap<NamespacedKey, String> getNBTTags(ItemStack item) {
        HashMap<NamespacedKey, String> list = new HashMap<>();
        for ( NamespacedKey keySet: item.getItemMeta().getPersistentDataContainer().getKeys() )
        {
            list.put( keySet, item.getItemMeta().getPersistentDataContainer().get( keySet, PersistentDataType.STRING ) );
        }
        return list;
    }
}
