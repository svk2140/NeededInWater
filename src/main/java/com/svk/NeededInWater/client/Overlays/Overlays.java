package com.svk.NeededInWater.client.Overlays;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class Overlays 
{
	private WaterScale iconOverlay;
	public Overlays()
	{
		this.iconOverlay = new WaterScale();
	}
	
	@SubscribeEvent()
	public void enventHandler(RenderGameOverlayEvent e)
	{
		if(e.isCancelable() || e.type != ElementType.EXPERIENCE)
		{
			return;
		}
		else
		{
			this.iconOverlay.renderOverlay(e);
		}
	}
}
