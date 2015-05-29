package com.svk.NeededInWater.base;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Items extends Item
{
	private String name;
	
	public Items(String name) 
	{
		super();
		this.setUnlocalizedName(name);
		this.name = name;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) 
	{
	   super.itemIcon = ir.registerIcon("NeededInWater:" + name);
	}
}
