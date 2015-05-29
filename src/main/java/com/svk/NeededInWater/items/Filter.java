package com.svk.NeededInWater.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Filter extends Item
{
	private String name;

	public Filter(String name, int useDamage) 
	{
		super();
		this.setUnlocalizedName(name);
		this.setMaxDamage(useDamage-1);
		this.setNoRepair();
		this.setContainerItem(this);
		this.name = name;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) 
	{
		super.itemIcon = ir.registerIcon("NeededInWater:" + name);
	}

	@Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        ItemStack copiedStack = itemStack.copy();

        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);

        return copiedStack;
    }
}
