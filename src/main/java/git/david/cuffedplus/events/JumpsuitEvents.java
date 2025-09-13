package git.david.cuffedplus.events;


import git.david.cuffedplus.init.ModItems;
import git.david.cuffedplus.items.item.JumpsuitItem;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class JumpsuitEvents {

    // Keep the jumpsuit on after death if forced on
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        Player oldP = event.getOriginal();
        Player newP = event.getEntity();

        ItemStack chest = oldP.getItemBySlot(EquipmentSlot.CHEST);

        if (!chest.isEmpty() && chest.hasTag()) {
            assert chest.getTag() != null;
            if (chest.getTag().hasUUID("equippedBy")) {
                if (!chest.getTag().getUUID("equippedBy").equals(oldP.getUUID())) {
                    newP.setItemSlot(EquipmentSlot.CHEST, chest.copy());
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!(chest.getItem() instanceof JumpsuitItem)) {
         //    JumpsuitEvents.getFormattedName(player, event.player.getDisplayName());
        }

        if (chest.getItem() instanceof JumpsuitItem) {
            //JumpsuitEvents.getFormattedName(player, event.player.getDisplayName());

            if (!JumpsuitItem.canUnequip(chest, player)) {
                player.setItemSlot(EquipmentSlot.CHEST, chest);

            }

        }
       // JumpsuitEvents.getFormattedName(player, event.player.getDisplayName());
    }



/* DISABLED FOR NOW BECUASE
    RIGHT NOW THE PREFIX IS APPLIED WHEN PUTTING
    ON A JUMPSUIT BUT NOT REMOVED WHEN TAKEN OFF
    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event) {
        Player player = event.getEntity();
        Component originalName = event.getDisplayname(); // Fixed casing
        Component newName = getFormattedName(player, originalName);
        event.setDisplayname(newName); // Fixed casing
    }

    public static Component getFormattedName(Player player, Component originalName) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!chest.isEmpty() && chest.getItem() instanceof JumpsuitItem) {
            MutableComponent prefix;

            if (chest.is(ModItems.PRISON_JUMPSUIT_1.get())
                    || chest.is(ModItems.PRISON_JUMPSUIT_2.get())
                    || chest.is(ModItems.PRISON_JUMPSUIT_3.get())) {

                prefix = Component.literal("[INMATE] ")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff8800)).withBold(true)); // Safer with fromRgb

            } else if (chest.is(ModItems.DCLASS_JUMPSUIT.get())) {

                prefix = Component.literal("[D-CLASS] ")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xff6a00)).withBold(true)); // Safer with fromRgb
            } else {
                return originalName;
            }

            return prefix.append(originalName);
        }

        return originalName;
    } */
}
