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
			int thirst = ExtendedPlayer.get(e.player).data.isThirst;
			if(thirst > 0)
			{
				float factorThirst = ExtendedPlayer.get(e.player).data.factorThirst;
							
				if(factorThirst == 0)
				{
					thirst -= BaseClassMod.decreaseThirst;
				}
				else
				{
					thirst -= (int) (BaseClassMod.decreaseThirst*factorThirst);
					ExtendedPlayer.get(e.player).data.durationFactorThirst -= 1;
					
					if(ExtendedPlayer.get(e.player).data.durationFactorThirst == 0)
					{
						ExtendedPlayer.get(e.player).data.factorThirst = 0;
					}
				}
				
				if(e.player.isSprinting())
				{
					thirst -= BaseClassMod.decreaseThirst;
				}
				
				if(e.player.posY > BaseClassMod.heightLevel)
				{
					thirst -= BaseClassMod.decreaseThirst * 0.5;
				}
				
				if(e.player.posY > BaseClassMod.heightLevel + (BaseClassMod.heightLevel - BaseClassMod.normalLevel))
				{
					thirst -= BaseClassMod.decreaseThirst * 0.5;
				}
					
				BiomeGenBase biome = e.player.worldObj.getBiomeGenForCoords((int) e.player.posX, (int)e.player.posZ);
				
				if(biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills)
				{
					thirst -= BaseClassMod.decreaseThirst;
				}
				else if(biome == BiomeGenBase.savanna || biome == BiomeGenBase.savannaPlateau)
				{
					thirst -= BaseClassMod.decreaseThirst * 0.5F;
				}
				else if(biome == BiomeGenBase.mesa || biome == BiomeGenBase.mesaPlateau || biome == BiomeGenBase.mesaPlateau_F)
				{
					thirst -= BaseClassMod.decreaseThirst * 1.5F;
				}
				else if(biome == BiomeGenBase.taiga || biome == BiomeGenBase.taigaHills || biome == BiomeGenBase.coldBeach || biome == BiomeGenBase.coldTaiga || biome == BiomeGenBase.coldTaigaHills || biome == BiomeGenBase.iceMountains || biome == BiomeGenBase.icePlains)
				{
					thirst -= BaseClassMod.decreaseThirst * 0.3F;
				}
			}
			else
			{
				thirst = 0;
				
				if(!e.player.isPotionActive(Potion.wither.id))
				{
					e.player.addPotionEffect(new PotionEffect(Potion.wither.id, 5*20, 0));
				}
			}
			ExtendedPlayer.get(e.player).data.isThirst = thirst;
		}
	}
}