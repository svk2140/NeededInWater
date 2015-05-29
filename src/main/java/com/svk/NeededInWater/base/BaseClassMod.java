package com.svk.NeededInWater.base;

import com.svk.NeededInWater.client.ClientProxy;
import com.svk.NeededInWater.packets.PacketPipeline;
import com.svk.NeededInWater.packets.packets.SyncPlayerPropsMessage;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Mod(modid=ModInfo.MODID, name=ModInfo.MODNAME, version=ModInfo.MODVERSION)

public class BaseClassMod
{
	@Mod.Instance(ModInfo.MODID)	
	
	public static BaseClassMod instance;
	public PacketPipeline pipeline;
	
	public static CommonProxy proxy;
	public static ClientProxy proxy2;
	
	public static final int maxThirst = 1000000;
	public static final int decreaseThirst = 83;
	public static final int heightLevel = 95;
	public static final int normalLevel = 64;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		System.out.println("NeededInWater has been successfully initialized");
		proxy.init();
		if(event.getSide() == Side.CLIENT)
		{
			initClient();
		}
		
		getInstance().pipeline = new PacketPipeline();
		getInstance().pipeline.initialise();
		getInstance().pipeline.registerPacket(SyncPlayerPropsMessage.class);
		getInstance().pipeline.postInitialise();
	}
	
	@SideOnly(Side.CLIENT)
	public void initClient()
	{
		proxy2.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT)
		{
			postInitClient();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void postInitClient()
	{
	}
	
	public static BaseClassMod getInstance()
	{
		return instance;
	}
}
