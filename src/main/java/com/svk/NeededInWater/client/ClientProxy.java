package com.svk.NeededInWater.client;

import org.lwjgl.input.Keyboard;

import com.svk.NeededInWater.base.CommonProxy;
import com.svk.NeededInWater.client.Overlays.Overlays;
import com.svk.NeededInWater.events.EventDrink;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy
{	
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new Overlays());
		MinecraftForge.EVENT_BUS.register(new EventDrink());
	}
}
