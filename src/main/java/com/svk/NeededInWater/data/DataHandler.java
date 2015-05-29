package com.svk.NeededInWater.data;

import com.svk.NeededInWater.base.BaseClassMod;
import com.svk.NeededInWater.packets.packets.SyncPlayerPropsMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DataHandler {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			if (ExtendedPlayer.get((EntityPlayer) event.entity) == null){
				EntityPlayer p = (EntityPlayer) event.entity;
				ExtendedPlayer.register((EntityPlayer) event.entity);
			}
		}
	}
	
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityPlayerMP)
		{
			BaseClassMod.getInstance().pipeline.sendTo(new SyncPlayerPropsMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
//		if(!event.entity.worldObj.isRemote )
//			{
//				ExtendedPlayer.loadProxyData((EntityPlayer) event.entity);
//			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			ExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPlayer.get(player).onUpdate();
		}
	}
}