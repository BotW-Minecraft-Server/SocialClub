package link.botwmcs.socialclub.mixin.cadmus;

import com.mojang.datafixers.util.Pair;
import earth.terrarium.cadmus.common.claims.AdminClaimHandler;
import earth.terrarium.cadmus.common.claims.ClaimHandler;
import earth.terrarium.cadmus.common.constants.ConstantComponents;
import earth.terrarium.cadmus.common.teams.TeamHelper;
import earth.terrarium.cadmus.common.util.LastMessageHolder;
import earth.terrarium.cadmus.common.util.ModUtils;
import link.botwmcs.davinci.util.SendComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Objects;

@Mixin(value = ModUtils.class, remap = false)
public class ModUtilsMixin {
    /**
     * @author Sam_Chai
     * @reason Tweak display to using Davinci Dynamic Island
     */
    @Overwrite
    public static void displayTeamName(ServerPlayer player, ChunkPos pos) {
        if (player instanceof LastMessageHolder holder) {
            Pair claimData = ClaimHandler.getClaim(player.serverLevel(), player.chunkPosition());
            Component displayName = null;
            if (claimData != null) {
                displayName = TeamHelper.getTeamName((String)claimData.getFirst(), player.server);
            }

            Component lastMessage = holder.cadmus$getLastMessage();
            Component farewell;
            if (lastMessage == null) {
                farewell = (Component) AdminClaimHandler.getFlag((ServerLevel)player.level(), player.chunkPosition(), "greeting");
                if (!farewell.getString().isBlank()) {
//                    player.displayClientMessage(farewell, false);
                    SendComponentUtil.sendDynamicIslandMessage(player, farewell.getString(), 3);
                }
            }

            if (!Objects.equals(displayName, lastMessage)) {
                holder.cadmus$setLastMessage(displayName);
                if (displayName == null) {
//                    player.displayClientMessage(ConstantComponents.WILDERNESS, true);
                    SendComponentUtil.sendDynamicIslandMessage(player, ConstantComponents.WILDERNESS.getString(), 3);
                    farewell = (Component)AdminClaimHandler.getFlag(player.serverLevel(), pos, "farewell");
                    if (!farewell.getString().isBlank()) {
//                        player.displayClientMessage(farewell, false);
                        SendComponentUtil.sendDynamicIslandMessage(player, farewell.getString(), 3);
                    }
                } else {
                    boolean isMember = TeamHelper.isMember((String)claimData.getFirst(), player.server, player.getUUID());
                    ChatFormatting teamColor = TeamHelper.getTeamColor((String)claimData.getFirst(), player.getServer());
                    ChatFormatting color = isMember ? teamColor : ChatFormatting.DARK_RED;
//                    player.displayClientMessage(displayName.copy().withStyle(color), true);
                    SendComponentUtil.sendDynamicIslandMessage(player, displayName.copy().getString(), 3);
                }

            }
        }
    }

}
