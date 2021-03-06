package TFC.Items.ItemBlocks;

import TFC.API.Constant.Global;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;

public class ItemCustomWood extends ItemTerraBlock
{
	public ItemCustomWood(int i)
	{
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
		setFolder("wood/trees");
	}
	@Override
	public String getItemDisplayName(ItemStack itemstack) 
	{
		String s = new StringBuilder().append(super.getItemDisplayName(itemstack)).append(".").append(Global.WOOD_ALL[itemstack.getItemDamage()]).toString();
		return s;
	}

	@Override
	public int getMetadata(int i)
	{
		return i;
	}

	@Override
	public void registerIcons(IconRegister registerer)
    {

    }
}
