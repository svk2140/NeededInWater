package com.svk.NeededInWater.events;

import org.lwjgl.input.Mouse;

import com.svk.NeededInWater.base.BaseClassMod;
import com.svk.NeededInWater.data.ExtendedPlayer;

import scala.swing.event.MouseButtonEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class EventSpectatorThirst 
{
	@SubscribeEvent
	public void TickEvent(TickEvent.PlayerTickEvent e) 
	{		
		
		if(!e.player.capabilities.isCreativeMode)
		{			
			if(ExtendedPlayer.get(e.player).data.isThirst > 0)
			{
				float factorThirst = ExtendedPlayer.get(e.player).data.factorThirst;
							
				if(factorThirst == 0)
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst;
				}
				else
				{
					ExtendedPlayer.get(e.player).data.isThirst -= (int) (BaseClassMod.decreaseThirst*factorThirst);
					ExtendedPlayer.get(e.player).data.durationFactorThirst -= 1;
					
					if(ExtendedPlayer.get(e.player).data.durationFactorThirst == 0)
					{
						ExtendedPlayer.get(e.player).data.factorThirst = 0;
					}
				}
				
				if(e.player.isSprinting())
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst;
				}
				
				if(e.player.posY > BaseClassMod.heightLevel)
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst * 0.5;
				}
				
				if(e.player.posY > BaseClassMod.heightLevel + (BaseClassMod.heightLevel - BaseClassMod.normalLevel))
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst * 0.5;
				}
					
				BiomeGenBase biome = e.player.worldObj.getBiomeGenForCoords((int) e.player.posX, (int)e.player.posZ);
				
				if(biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills)
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst;
				}
				else if(biome == BiomeGenBase.savanna || biome == BiomeGenBase.savannaPlateau)
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst * 0.5F;
				}
				else if(biome == BiomeGenBase.mesa || biome == BiomeGenBase.mesaPlateau || biome == BiomeGenBase.mesaPlateau_F)
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst * 1.5F;
				}
				else if(biome == BiomeGenBase.taiga || biome == BiomeGenBase.taigaHills || biome == BiomeGenBase.coldBeach || biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.coldTaigaHills || biome == BiomeGenBase.iceMountains || biome == BiomeGenBase.icePlains)
				{
					ExtendedPlayer.get(e.player).data.isThirst -= BaseClassMod.decreaseThirst * 0.3F;
				}
			}
			else
			{
				ExtendedPlayer.get(e.player).data.isThirst = 0;
				
				if(!e.player.isPotionActive(Potion.wither.id))
				{
					e.player.addPotionEffect(new PotionEffect(Potion.wither.id, 5*20, 0));
				}
			}
		}
	}
}