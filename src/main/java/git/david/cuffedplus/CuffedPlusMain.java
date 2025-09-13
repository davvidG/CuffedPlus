package git.david.cuffedplus;

import javax.annotation.Nonnull;

import git.david.cuffedplus.events.JumpsuitEvents;
import git.david.cuffedplus.init.*;
import net.minecraftforge.event.RegisterCommandsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lazrproductions.cuffed.items.base.AbstractRestraintItem;
import com.lazrproductions.cuffed.restraints.RestraintAPI;
import com.lazrproductions.cuffed.restraints.base.AbstractRestraint;

import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

/*

    This is the main mod class that every forge mod needs to be able to run. For a Cuffed addon there must be some things implement here.


    FIRTLY, you need to register your custom restraints in the constructor for your mod, just like any other registry.

    OPTIONALLY, you can make your restraints dispensable by adding the following to the common setup fml event:

        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected ItemStack execute(@Nonnull BlockSource source, @Nonnull ItemStack stack) {
                this.setSuccess(AbstractRestraintItem.dispenseRestraint(source, stack));
                if(this.isSuccess())
                    stack.shrink(1);
                return stack;
            }
        };
        DispenserBlock.registerBehavior(MyModItems.MY_RESTRAINT_ITEM.get(), dispenseitembehavior);

    
    LASTLY, and most vitally, in version 1.3.2, Cuffed has difficulty finding addon's registries. So to ensure Cuffed finds your registries, 
    add the following snippet to a function with the RegisterEvent event:
        
        IForgeRegistry<?> r = event.getForgeRegistry();
        if(r != null && r.getValues().size() > 0 && r.getValues().toArray()[0] instanceof AbstractRestraint) {
            if(r.getRegistryName().getNamespace().equals(MODID))
                RestraintAPI.Registries.register(r);
        }
    
    This hopefully will be fixed in the future.


    Use feel free to use this example mod as a template for your addon creation needs.
    
 */

@Mod(CuffedPlusMain.MODID)
public class CuffedPlusMain {
    public static final Logger LOGGER = LogManager.getLogger(CuffedPlusMain.MODID);
    public static final String MODID = "cuffedplus";



    public CuffedPlusMain() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new JumpsuitEvents());
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRecipes.SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());


        modEventBus.addListener(this::commonSetup);

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModRestraints.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::onRegister);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Running common setup for Cuffed Example Addon");

        DispenseItemBehavior dispenseitembehavior = new OptionalDispenseItemBehavior() {
            protected @NotNull ItemStack execute(@Nonnull BlockSource source, @Nonnull ItemStack stack) {
                this.setSuccess(AbstractRestraintItem.dispenseRestraint(source, stack));
                if(this.isSuccess())
                    stack.shrink(1);
                return stack;
            }
        };

        DispenserBlock.registerBehavior(ModItems.WOOD_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.GOLD_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.EMERALD_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.DIAMOND_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.NETHERITE_CUFFS.get(), dispenseitembehavior);
        DispenserBlock.registerBehavior(ModItems.BEDROCK_CUFFS.get(), dispenseitembehavior);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Keys.SOUND_EVENTS))
            ModSounds.register(event);

        IForgeRegistry<?> r = event.getForgeRegistry();
        if(r != null && !r.getValues().isEmpty() && r.getValues().toArray()[0] instanceof AbstractRestraint) {
            if(r.getRegistryName().getNamespace().equals(MODID))
                RestraintAPI.Registries.register(r);
        }
    }
    
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public void onRegisterCommands(RegisterCommandsEvent event) {

        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            ModModelLayers.registerLayers(event);
        }
    }
}