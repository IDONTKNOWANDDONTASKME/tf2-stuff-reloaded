package rafradek.TF2weapons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.registry.GameRegistry;
import rafradek.TF2weapons.decoration.ItemWearable;

public class CommandGenerateReferences extends CommandBase {

	@Override
	public String getUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "command.generatereferences.usage";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "generatereferences";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 4;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		File dir=new File(TF2weapons.instance.weaponDir.getParentFile(),"TF2References");
		if(!dir.exists())
			dir.mkdir();
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(dir,"attributes.txt")));
			writer.write("ID - Name - Class - Default - Nice name");
			writer.newLine();
			for(TF2Attribute attr:TF2Attribute.attributes) {
				if(attr != null) {
					writer.write(attr.id+"	"+attr.name+"	"+attr.effect+"	"+attr.defaultValue+"	"+I18n.format("weaponAttribute."+attr.name, attr.defaultValue));
					writer.newLine();
					
				}
			}
			writer.close();
			writer=new BufferedWriter(new FileWriter(new File(dir,"properties.txt")));
			writer.write("ID - Name - Type");
			writer.newLine();
			for(WeaponData.PropertyType prop:WeaponData.propertyTypes) {
				if(prop != null) {
					writer.write(prop.id+"	"+prop.name+"	"+prop.type.toString());
					writer.newLine();
				}
			}
			writer.close();
			writer=new BufferedWriter(new FileWriter(new File(dir,"effects.txt")));
			writer.write("ID - Name");
			writer.newLine();
			for(Potion effect:GameRegistry.findRegistry(Potion.class)) {
				if(effect != null && effect.getRegistryName().getResourceDomain().equals(TF2weapons.MOD_ID)) {
					writer.write(effect.getRegistryName()+"	"+I18n.format(effect.getName()));
					writer.newLine();
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
