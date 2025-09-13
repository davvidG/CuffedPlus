package git.david.cuffedplus.events;

import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.JumpsuitLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CuffedPlusMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {


    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skin : event.getSkins()) {
            var renderer = event.getSkin(skin);

            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new JumpsuitLayer(playerRenderer));
            }
        }
    }

    // ClientModEventSubscriber.java
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
