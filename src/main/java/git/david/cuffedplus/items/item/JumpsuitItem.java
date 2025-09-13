package git.david.cuffedplus.items.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class JumpsuitItem extends Item {


    public JumpsuitItem(Properties Item) {
        super(Item);
    }

    // Helper to check if a player can remove it
    public static boolean canUnequip(ItemStack stack, Player player) {
        if (!stack.hasTag() || !Objects.requireNonNull(stack.getTag()).hasUUID("equippedBy")) {
                  return false; // No tag = normal item
        }

        UUID equipper = stack.getTag().getUUID("equippedBy");
        return equipper.equals(player.getUUID()); // Only remover is equipper = player
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, Player user, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
        if (!user.level().isClientSide && target instanceof Player targetPlayer) {
            if (!target.hasItemInSlot(EquipmentSlot.CHEST)) {

                ItemStack jumpsuit = stack.copy();
                jumpsuit.getOrCreateTag().putUUID("equippedBy", user.getUUID());


                target.setItemSlot(EquipmentSlot.CHEST, jumpsuit);


                if (!user.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack currentChest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!level.isClientSide) {
            // Copy the jumpsuit to be equipped
            ItemStack jumpsuit = stack.copy();
            jumpsuit.setCount(1); // Only equip one
            jumpsuit.getOrCreateTag().putUUID("equippedBy", player.getUUID());

            // Try to move the current chest item to the inventory, or drop it
            if (!currentChest.isEmpty()) {
                boolean added = player.getInventory().add(currentChest);
                if (!added) {
                    // Drop the item into the world if inventory is full
                    player.drop(currentChest, false);
                }
            }

            // Equip the new jumpsuit
            player.setItemSlot(EquipmentSlot.CHEST, jumpsuit);

            // Consume the item if not in creative mode
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.success(stack);
    }

}
