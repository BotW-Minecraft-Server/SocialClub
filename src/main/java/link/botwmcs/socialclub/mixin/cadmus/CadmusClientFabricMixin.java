package link.botwmcs.socialclub.mixin.cadmus;

import earth.terrarium.cadmus.client.CadmusClient;
import earth.terrarium.cadmus.client.fabric.CadmusClientFabric;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = CadmusClientFabric.class, remap = false)
public class CadmusClientFabricMixin {
    /**
     * @author Sam_Chai
     * @reason Remove client command registration
     */
    @Overwrite
    private static void registerClientCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommandManager.literal("claimmap")
                        .requires(source -> source.hasPermission(4))
                        .executes(context -> {
                            Minecraft.getInstance().tell(CadmusClient::openClaimMap);
                            return 0;
                        })
                )
        );
    }
}
