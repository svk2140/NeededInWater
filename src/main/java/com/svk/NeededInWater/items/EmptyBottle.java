package com.svk.NeededInWater.items;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import com.svk.NeededInWater.base.BaseClassMod;
import com.svk.NeededInWater.base.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EmptyBottle extends Item
{
	private String name;
	public EmptyBottle(String name) 
	{
		super();
		this.setCreativeTab(CommonProxy.tabWater);
		this.setUnlocalizedName(name);
		this.name = name;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) 
	{
		super.itemIcon = ir.registerIcon("NeededInWater:" + name);
	}

	@Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
		if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			int x = movingobjectposition.blockX;
			int y = movingobjectposition.blockY;
			int z = movingobjectposition.blockZ;

			Material material = world.getBlock(x, y, z).getMaterial();
			int l = world.getBlockMetadata(x, y, z);

			if (material == Material.water && l == 0)
			{
				this.setBottleWater(player, is, world.getBiomeGenForCoords(x, z), y);
			}
		}

		return is;
	}

	public void setBottleWater(EntityPlayer player, ItemStack stack, BiomeGenBase biome, int y)
	{
		if(player.getCurrentEquippedItem().stackSize > 1)
		{
			--stack.stackSize;
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
				
		if(y < BaseClassMod.heightLevel)
		{
			if(biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills || biome == BiomeGenBase.mesa || biome == BiomeGenBase.mesaPlateau || biome == BiomeGenBase.mesaPlateau_F){player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleOfDirtyWaterDesert));}
			else if(biome == BiomeGenBase.swampland){player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleOfDirtyWaterSwamp));}
			else if(biome == BiomeGenBase.ocean || biome == BiomeGenBase.beach){player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleSeaWater));}
			else if(biome == BiomeGenBase.river){player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleRiverWater));}
			else if(biome == BiomeGenBase.taiga || biome == BiomeGenBase.taigaHills || biome == BiomeGenBase.coldBeach || biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.coldTaigaHills || biome == BiomeGenBase.iceMountains || biome == BiomeGenBase.icePlains){player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleClearWater));}
			else {player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleOfDirtyWater));}
		}
		else
		{
			player.inventory.addItemStackToInventory(new ItemStack(CommonProxy.bottleSpringWater));
		}
	}
}
