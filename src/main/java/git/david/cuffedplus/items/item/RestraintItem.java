package git.david.cuffedplus.items.item;

import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;


public class RestraintItem extends AbstractRestraintItem {

    public RestraintItem(Properties p) {
        super(p);
    }

    // --- SETTERS ---
    public static void setAllowBreakingBlocks(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("AllowBreakingBlocks", value);
    }

    public static void setAllowItemUse(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("AllowItemUse", value);
    }

    public static void setAllowMovement(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("AllowMovement", value);
    }

    public static void setAllowJumping(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("AllowJumping", value);
    }

    public static void setCanBeBrokenOutOf(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("CanBeBrokenOutOf", value);
    }

    public static void setLockpickable(ItemStack stack, boolean value) {
        stack.getOrCreateTag().putBoolean("Lockpickable", value);
    }

    // --- GETTERS ---
    public static boolean allowBreakingBlocks(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("AllowBreakingBlocks");
    }

    public static boolean allowItemUse(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("AllowItemUse");
    }

    public static boolean allowMovement(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("AllowMovement");
    }

    public static boolean allowJumping(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("AllowJumping");
    }

    public static boolean canBeBrokenOutOf(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("CanBeBrokenOutOf");
    }

    public static boolean isLockpickable(ItemStack stack) {

        return stack.getOrCreateTag().getBoolean("isLockpickable");
    }

    // Optional: Show properties in tooltip for debugging
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (allowBreakingBlocks(stack)) {
            tooltip.add(Component.literal("Breaking Blocks Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Breaking Blocks Disabled").withStyle(ChatFormatting.GRAY));
        }

        if (allowItemUse(stack)) {
            tooltip.add(Component.literal("Using Items Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Using Items Disabled").withStyle(ChatFormatting.GRAY));
        }

        if (allowMovement(stack)) {
            tooltip.add(Component.literal("Movement Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Movement Disabled").withStyle(ChatFormatting.GRAY));
        }

        if (allowJumping(stack)) {
            tooltip.add(Component.literal("Jumping Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Jumping Disabled").withStyle(ChatFormatting.GRAY));
        }

        if (canBeBrokenOutOf(stack)) {
            tooltip.add(Component.literal("Unbreakable").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Unbreakable").withStyle(ChatFormatting.GRAY));
        }

        if (isLockpickable(stack)) {
            tooltip.add(Component.literal("Lockpicking Disabled").withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.remove(Component.literal("Lockpicking Disabled").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }


}
