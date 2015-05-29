package com.svk.NeededInWater.base;

import com.svk.NeededInWater.craft.CraftListStandart;
import com.svk.NeededInWater.data.DataHandler;
import com.svk.NeededInWater.data.ExtendedPlayer;

import net.minecraft.entity.player.EntityPlayer;

import com.svk.NeededInWater.events.EventSpectatorThirst;
import com.svk.NeededInWater.items.EmptyBottle;
import com.svk.NeededInWater.items.Filter;
import com.svk.NeededInWater.items.bottle;
import com.svk.NeededInWater.tabs.TabWater;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

public class CommonProxy 
{
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
	
	public static CreativeTabs tabWater = new TabWater("tabWater");

	//bottle----------------------------------------------------------
	public static Item emptyBottle = new EmptyBottle("EmptyBottle");
	public static Item bottleSpringWater = new bottle("BottleSpringWater", 120000, 0, 0);
	public static Item bottleWellClearWater = new bottle("BottleClearWater", 100000, 0, 0);
	public static Item bottleClearWater = new bottle("bottleClearWater", 90000, 0, 0);
	public static Item bottleBoiledWater = new bottle("BottleBoiledWater", 80000, 0, 0);
	public static Item bottleOfDirtyWaterDesert = new bottle("BottleOfDirtyWaterDesert", 60000, 4, 30)
	{
		public void effect(EntityPlayer player, World world)
		{
			player.addPotionEffect(new PotionEffect(19, world.rand.nextInt(200), 1));
		}
	};
	
	public static Item bottleOfDirtyWaterSwamp = new bottle("BottleOfDirtyWaterSwamp", 40000, 5, 40)
	{
		public void effect(EntityPlayer player, World world)
		{
			player.addPotionEffect(new PotionEffect(19, world.rand.nextInt(301), world.rand.nextInt(2)+1));
			if(world.rand.nextInt(101) < 35)
			{
				ExtendedPlayer.get(player).data.factorThirst = 1.5F;
				ExtendedPlayer.get(player).data.durationFactorThirst = 1200;
			}
		}
	};
	
	public static Item bottleOfDirtyWater = new bottle("bottleOfDirtyWater", 60000, 3, 30)
	{
		public void effect(EntityPlayer player, World world)
		{
			player.addPotionEffect(new PotionEffect(19, world.rand.nextInt(301), 1));
		}
	};
	
	public static Item bottleSeaWater = new bottle("BottleSeaWater", 25000, 0, 100)
	{
		public void effect(EntityPlayer player, World world)
		{
			ExtendedPlayer.get(player).data.factorThirst = 3;
			ExtendedPlayer.get(player).data.durationFactorThirst = 1800;
		}
	};
	
	public static Item bottleRiverWater = new bottle("BottleRiverWater", 70000, 1, 15)
	{
		public void effect(EntityPlayer player, World world)
		{
			player.addPotionEffect(new PotionEffect(19, world.rand.nextInt(301), 1));
		}
	};
	//---------------------------------------------------------------
	//items
	public static Item coalFilter = new Filter("CoalFilter", 10).setCreativeTab(tabWater);
	public static Item earthlingsFilter = new Filter("EarthlingsFilter", 5).setCreativeTab(tabWater);
	public static Item filterReverseOsmosis = new Filter("FilterReverseOsmosis", 100).setCreativeTab(tabWater);
	public static Item filterBase = new Items("FilterBase").setCreativeTab(tabWater);
	public static Item filterWeb = new Items("FilterWeb").setCreativeTab(tabWater);

	public static void init()
	{
		GameRegistry.registerItem(bottleOfDirtyWaterDesert, "bottleOfDirtyWaterDesert");
		GameRegistry.registerItem(bottleSpringWater, "bottleSpringWater");
		GameRegistry.registerItem(bottleClearWater, "bottleClearWater");
		GameRegistry.registerItem(bottleRiverWater, "bottleRiverWater");
		GameRegistry.registerItem(bottleSeaWater, "bottleSeaWater");
		GameRegistry.registerItem(bottleOfDirtyWater, "bottleOfDirtyWater");
		GameRegistry.registerItem(bottleOfDirtyWaterSwamp, "bottleOfDirtyWaterSwamp");
		GameRegistry.registerItem(bottleOfDirtyWaterDesert, "bottleOfDirtyWaterDesert");
		GameRegistry.registerItem(bottleBoiledWater, "bottleBoiledWater");
		GameRegistry.registerItem(coalFilter, "coalFilter");
		GameRegistry.registerItem(earthlingsFilter, "earthlingsFilter");
		GameRegistry.registerItem(filterReverseOsmosis, "filterReverseOsmosis");
		GameRegistry.registerItem(bottleWellClearWater, "bottleWellClearWater");
		GameRegistry.registerItem(filterBase, "filterBase");
		GameRegistry.registerItem(filterWeb, "filterWeb");
		GameRegistry.registerItem(emptyBottle, "emptyBottle");
		FMLCommonHandler.instance().bus().register(new EventSpectatorThirst());
		MinecraftForge.EVENT_BUS.register(new DataHandler());
		FMLCommonHandler.instance().bus().register(new DataHandler());
		
		CraftListStandart.init();
	}
	
	public static void storeEntityData(String name, NBTTagCompound compound) {
		extendedEntityData.put(name, compound);
	}

	/**
	 * Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	 */
	public static NBTTagCompound getEntityData(String name) {
		return extendedEntityData.remove(name);
	}
}
