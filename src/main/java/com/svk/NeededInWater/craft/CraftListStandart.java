package com.svk.NeededInWater.craft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.svk.NeededInWater.base.CommonProxy;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftListStandart 
{
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(CommonProxy.filterWeb, 1), new Object[]{"# #", " # ", "# #", ('#'), Items.string});
		GameRegistry.addRecipe(new ItemStack(CommonProxy.filterBase, 1), new Object[]{"Y Y", "Y#Y", "Y Y", ('#'), CommonProxy.filterWeb, ('Y'), Items.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.earthlingsFilter, 1), new Object[] {CommonProxy.filterBase, Blocks.dirt});
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.coalFilter, 1), new Object[] {CommonProxy.filterBase, Items.coal});	
		
		GameRegistry.addSmelting(CommonProxy.bottleOfDirtyWater, new ItemStack(CommonProxy.bottleBoiledWater, 1), 2.5F);
		GameRegistry.addSmelting(CommonProxy.bottleRiverWater, new ItemStack(CommonProxy.bottleBoiledWater, 1), 2.5F);
		
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleClearWater, 1), new Object[] {CommonProxy.bottleOfDirtyWater, new ItemStack(CommonProxy.earthlingsFilter, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleClearWater, 1), new Object[] {CommonProxy.bottleOfDirtyWater, new ItemStack(CommonProxy.coalFilter, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleClearWater, 1), new Object[] {CommonProxy.bottleRiverWater, new ItemStack(CommonProxy.coalFilter, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleOfDirtyWater, 1), new Object[] {CommonProxy.bottleOfDirtyWaterDesert, new ItemStack(CommonProxy.coalFilter, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleOfDirtyWater, 1), new Object[] {CommonProxy.bottleOfDirtyWaterDesert, new ItemStack(CommonProxy.earthlingsFilter, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleOfDirtyWater, 1), new Object[] {CommonProxy.bottleOfDirtyWaterSwamp, new ItemStack(CommonProxy.coalFilter, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleWellClearWater, 1), new Object[] {CommonProxy.bottleSeaWater, new ItemStack(CommonProxy.filterReverseOsmosis, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleWellClearWater, 1), new Object[] {CommonProxy.bottleOfDirtyWater, new ItemStack(CommonProxy.filterReverseOsmosis, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleWellClearWater, 1), new Object[] {CommonProxy.bottleOfDirtyWaterDesert, new ItemStack(CommonProxy.filterReverseOsmosis, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleWellClearWater, 1), new Object[] {CommonProxy.bottleOfDirtyWaterSwamp, new ItemStack(CommonProxy.filterReverseOsmosis, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleWellClearWater, 1), new Object[] {CommonProxy.bottleRiverWater, new ItemStack(CommonProxy.filterReverseOsmosis, 1, OreDictionary.WILDCARD_VALUE)});	
		GameRegistry.addShapelessRecipe(new ItemStack(CommonProxy.bottleWellClearWater, 1), new Object[] {CommonProxy.bottleBoiledWater, new ItemStack(CommonProxy.filterReverseOsmosis, 1, OreDictionary.WILDCARD_VALUE)});	
	}
}
