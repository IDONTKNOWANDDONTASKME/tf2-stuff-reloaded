package rafradek.TF2weapons.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {
	public void registerRenderInformation() {
		// unused server side. -- see ClientProxy for implementation
	}

	public void registerTicks() {}

	public void playReloadSound(EntityLivingBase player, ItemStack stack) {}

	public void preInit() {}

	public EntityPlayer getPlayerForSide(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	public void registerItemBlock(ItemBlock item) {}

	public void displayCorruptedFileError() {}

}