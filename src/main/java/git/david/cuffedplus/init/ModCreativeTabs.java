package git.david.cuffedplus.init;

import git.david.cuffedplus.CuffedPlusMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/*
    Very basic creative tabs registry, nothing special to note here :)
 */

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, CuffedPlusMain.MODID);

    public static final RegistryObject<CreativeModeTab> CUFFED_TAB = CREATIVE_MODE_TABS.register("cuffed",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.cuffedplus"))
                    .icon(() -> ModItems.NETHERITE_CUFFS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.WOOD_CUFFS.get());
                        output.accept(ModItems.WOOD_CUFFS_KEY.get());

                        output.accept(ModItems.GOLD_CUFFS.get());
                        output.accept(ModItems.GOLD_CUFFS_KEY.get());

                        output.accept(ModItems.EMERALD_CUFFS.get());
                        output.accept(ModItems.EMERALD_CUFFS_KEY.get());

                        output.accept(ModItems.DIAMOND_CUFFS.get());
                        output.accept(ModItems.DIAMOND_CUFFS_KEY.get());

                        output.accept(ModItems.NETHERITE_CUFFS.get());
                        output.accept(ModItems.NETHERITE_CUFFS_KEY.get());

                        output.accept(ModItems.BEDROCK_CUFFS.get());
                        output.accept(ModItems.BEDROCK_CUFFS_KEY.get());
                        output.accept(ModItems.HAZARD_TAPE.get());


                        output.accept(ModItems.DCLASS_JUMPSUIT.get());
                        output.accept(ModItems.PRISON_JUMPSUIT_1.get());
                        output.accept(ModItems.PRISON_JUMPSUIT_2.get());
                        output.accept(ModItems.PRISON_JUMPSUIT_3.get());


                        output.accept(ModItems.ALLOW_BREAKING_BLOCKS_MODIFIER.get());
                        output.accept(ModItems.ALLOW_ITEM_USE_MODIFIER.get());
                        output.accept(ModItems.ALLOW_MOVEMENT_MODIFIER.get());
                        output.accept(ModItems.ALLOW_JUMPING_MODIFIER.get());
                        output.accept(ModItems.CAN_BE_BROKEN_OUT_OF_MODIFIER.get());
                        output.accept(ModItems.IS_LOCKPICKABLE_MODIFIER.get());


                        //  output.accept(ModItems.ANKLE_MONITOR_CLEAN.get());
                        // output.accept(ModItems.ANKLE_MONITOR_DIRTY.get());

                        //   output.accept(ModItems.ANKLE_MONITOR.get());


                    }).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
