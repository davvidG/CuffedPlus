package git.david.cuffedplus.events;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.init.ModItems;
import git.david.cuffedplus.items.item.AnkleMonitorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber(modid = CuffedPlusMain.MODID)
public class AnkleMonitorEvent {

   /* private static final int BOOTS_SLOT_INDEX = 39;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        // Target armor slot (example: HEAD)
        EquipmentSlot slot = EquipmentSlot.FEET;

        ItemStack equipped = player.getItemBySlot(slot);
        ItemStack required = new ItemStack(ModItems.ANKLE_MONITOR.get());

        // Check if the helmet is missing or not the required one
        if (!ItemStack.isSameItemSameTags(equipped, required)) {
            player.setItemSlot(slot, required.copy());
        }

        // Optional: Prevent dropping the item
        if (player.getInventory().contains(required)) {
            player.getInventory().removeItem(required);
        }
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();


        if (stack.getItem() instanceof AnkleMonitorItem) {

            event.setCanceled(true);
            player.displayClientMessage(Component.literal("You cannot equip armor."), true);
        }
    } */

}






