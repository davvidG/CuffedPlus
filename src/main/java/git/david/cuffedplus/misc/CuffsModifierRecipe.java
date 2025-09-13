package git.david.cuffedplus.misc;

import git.david.cuffedplus.init.ModRecipes;
import git.david.cuffedplus.items.item.ModifierItem;
import git.david.cuffedplus.items.item.RestraintItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CuffsModifierRecipe implements SmithingRecipe {
    private final ResourceLocation id;
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;

    public CuffsModifierRecipe(ResourceLocation id, Ingredient template, Ingredient base, Ingredient addition) {
        this.id = id;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    // Smithing uses a 3-slot container: 0=template, 1=base, 2=addition
    @Override
    public boolean matches(Container container, Level level) {
        return template.test(container.getItem(0)) &&
                base.test(container.getItem(1)) &&
                addition.test(container.getItem(2));
    }

    @Override
    public @NotNull ItemStack assemble(Container container, @NotNull RegistryAccess access) {
        ItemStack modifierItem = container.getItem(0);
        ResourceLocation modKey = BuiltInRegistries.ITEM.getKey(modifierItem.getItem());



        ItemStack baseItem = container.getItem(1).copy();       // copy cuffs (keeps enchants, damage, etc.)
        baseItem.setCount(1);// smithing result is 1 item

        ItemStack additionItem = container.getItem(2);


        if (additionItem.is(Items.DIAMOND)) {

            switch (modKey.toString()) {
                case "cuffedplus:allow_breaking_blocks":
                    RestraintItem.setAllowBreakingBlocks(baseItem, false);
                    break;

                case "cuffedplus:allow_item_use":
                    RestraintItem.setAllowItemUse(baseItem, false);
                    break;

                case "cuffedplus:allow_movement":
                    RestraintItem.setAllowMovement(baseItem, false);
                    break;

                case "cuffedplus:allow_jumping":
                    RestraintItem.setAllowJumping(baseItem, false);
                    break;

                case "cuffedplus:can_be_broken_out_of":
                    RestraintItem.setCanBeBrokenOutOf(baseItem, false);
                    break;

                case "cuffedplus:is_lockpickable":
                    RestraintItem.setLockpickable(baseItem, false);
                    break;

                default:

                   // throw new IllegalStateException("Oh no! Unexpected value: " + modKey);
            }


        } else if (additionItem.is(Items.NETHERITE_SCRAP)) {

            switch (modKey.toString()) {
                case "cuffedplus:allow_breaking_blocks":
                    RestraintItem.setAllowBreakingBlocks(baseItem, true);
                    break;
                case "cuffedplus:allow_item_use":
                    RestraintItem.setAllowItemUse(baseItem, true);
                    break;
                case "cuffedplus:allow_movement":
                    RestraintItem.setAllowMovement(baseItem,true);
                    break;
                case "cuffedplus:allow_jumping":
                    RestraintItem.setAllowJumping(baseItem, true);
                    break;
                case "cuffedplus:can_be_broken_out_of":
                    RestraintItem.setCanBeBrokenOutOf(baseItem, true);
                    break;
                case "cuffedplus:is_lockpickable":
                    RestraintItem.setLockpickable(baseItem, true);
                    break;
                default:
                    break;
            }

        } else {

            return ItemStack.EMPTY;

        }
        // slap your modifier info onto the cuffs

        return baseItem;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack) {
        return addition.test(stack);
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return ItemStack.EMPTY; // dynamic result
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CUFFS_MODIFIER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.SMITHING;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }
}