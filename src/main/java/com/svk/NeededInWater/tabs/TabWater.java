package com.svk.NeededInWater.tabs;

import com.svk.NeededInWater.base.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TabWater extends CreativeTabs 
{
	private Item texture;
	public TabWater(String tabID)
	{
		super(tabID);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return CommonProxy.bottleSpringWater;
	}
}
