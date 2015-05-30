package com.svk.NeededInWater.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.svk.NeededInWater.base.BaseClassMod;
import com.svk.NeededInWater.base.CommonProxy;
import com.svk.NeededInWater.data.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class bottle extends Item
{
	private String name;
	int thirst = 0;
	int dirty = 0;
	int procentEffect = 0;
	
	public bottle(String name, int thirst, int dirty, int procentEffect) 
	{
		super();
		this.setCreativeTab(CommonProxy.tabWater);
		this.setUnlocalizedName(name);
		this.name = name;
		this.thirst = thirst;
		this.dirty = dirty;
		this.procentEffect = procentEffect;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) 
	{
	   super.itemIcon = ir.registerIcon("NeededInWater:" + name);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	{
		if(ExtendedPlayer.get(player).data.isThirst < BaseClassMod.maxThirst)
		{
			if(ExtendedPlayer.get(player).data.isThirst > BaseClassMod.maxThirst/10)
			{
				ExtendedPlayer.get(player).data.isThirst += thirst;
			}
			else
			{
				ExtendedPlayer.get(player).data.isThirst += thirst * 1.7F;
			}
		
			if(ExtendedPlayer.get(player).data.isThirst > BaseClassMod.maxThirst)
			{
				ExtendedPlayer.get(player).data.isThirst += thirst * 0.5F;
			}
			
			if(world.rand.nextInt(101) < procentEffect)
			{
				this.effect(player, world);
			}
			
			if(is != null)
			this.removeItem(is, player);
		}
		return is;
	}
	
	private void removeItem(ItemStack is, EntityPlayer player)
	{		
		if(player.getCurrentEquippedItem().stackSize > 1)
		{
			--is.stackSize;
		}
		else
		{
			int slot = 0;
			
			for (int j = 0; j < player.inventory.getSizeInventory(); j++)
			{
				ItemStack item2 = player.inventory.getStackInSlot( j );

				if (item2 != null && item2.getItem() == this)
				{
					slot = j;
					break;
				}
			}

			player.inventory.setInventorySlotContents(slot, null);
		}
	}
	
	public void effect(EntityPlayer player, World world){}
}
