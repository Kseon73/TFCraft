package TFC.Items.Tools;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import TFC.TFCBlocks;
import TFC.API.Enums.EnumDamageType;
import TFC.API.Enums.EnumSize;
import TFC.Core.TFC_Core;
import TFC.Core.Util.StringUtil;
import TFC.TileEntities.TileEntityFoodPrep;

public class ItemCustomKnife extends ItemWeapon
{
	public ItemCustomKnife(int i, EnumToolMaterial e)
	{
		super(i, e);
		this.setMaxDamage(e.getMaxUses());
		this.weaponDamage = 75 + e.getDamageVsEntity();
		this.damageType = EnumDamageType.PIERCING;
	}

	@Override
	public EnumSize getSize()
	{
		return EnumSize.SMALL;
	}

	@Override
	public boolean canStack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float HitX, float HitY, float HitZ) 
	{
		int id = world.getBlockId(x, y, z);
		if(!world.isRemote && id != TFCBlocks.ToolRack.blockID)
		{
			int hasBowl = -1;

			for(int i = 0; i < 36 && hasBowl == -1;i++)
			{
				if(entityplayer.inventory.mainInventory[i] != null && entityplayer.inventory.mainInventory[i].getItem().itemID == Item.bowlEmpty.itemID)
					hasBowl = i;
			}
			
			if(side == 1 && !TFC_Core.isSoil(id) && !TFC_Core.isWater(id) && world.getBlockId(x, y+1, z) == 0 && hasBowl != -1)
			{
				world.setBlock(x, y+1, z, TFCBlocks.FoodPrep.blockID);
				TileEntityFoodPrep te = (TileEntityFoodPrep) world.getBlockTileEntity(x, y+1, z);
				if(te != null)
				{
					te.setInventorySlotContents(5, entityplayer.inventory.mainInventory[hasBowl]);
					entityplayer.inventory.mainInventory[hasBowl] = null;
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addExtraInformation(ItemStack is, EntityPlayer player, List arraylist)
	{
		if (TFC_Core.showExtraInformation()) 
		{
			arraylist.add(EnumChatFormatting.DARK_GRAY + StringUtil.localize("gui.Help") + ":");
			arraylist.add(EnumChatFormatting.AQUA + StringUtil.localize("gui.RightClick") + " " + 
					EnumChatFormatting.WHITE + StringUtil.localize("gui.Knife.Inst0"));
			arraylist.add(EnumChatFormatting.WHITE + StringUtil.localize("gui.Knife.Inst1"));
			arraylist.add(EnumChatFormatting.WHITE + StringUtil.localize("gui.Knife.Inst2"));
		}
		else
		{
			arraylist.add(
					EnumChatFormatting.DARK_GRAY + StringUtil.localize("gui.Help") + ": (" + StringUtil.localize("gui.Armor.Hold") + " " + 
							EnumChatFormatting.GRAY + StringUtil.localize("gui.Armor.Shift") + 
							EnumChatFormatting.DARK_GRAY + ")");
		}
	}

}