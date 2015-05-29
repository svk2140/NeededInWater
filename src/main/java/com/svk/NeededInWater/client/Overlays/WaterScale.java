package com.svk.NeededInWater.client.Overlays;

import org.lwjgl.opengl.GL11;

import com.svk.NeededInWater.base.BaseClassMod;
import com.svk.NeededInWater.base.ModInfo;
import com.svk.NeededInWater.data.ExtendedPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class WaterScale extends Gui
{
	public void renderOverlay(RenderGameOverlayEvent e)
	{
		ResourceLocation icon = new ResourceLocation(ModInfo.MODID, "textures/gui/waterScale.png");
		
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player= Minecraft.getMinecraft().thePlayer;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		int x = 200;
		int y = 36;
		
		mc.renderEngine.bindTexture(icon);
		
		GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
    	if(!player.capabilities.isCreativeMode)
    	{
    		if(ExtendedPlayer.get(player).data.isThirst < BaseClassMod.maxThirst)
    		{
		        int thirst = ExtendedPlayer.get(player).data.isThirst;
		        int step = BaseClassMod.maxThirst / 10;
		        
		    	int stop = 0;
		    	int max = ExtendedPlayer.get(player).data.isThirst;
		    	int residue = 0;
		    	
		    	for(int s = 0; s < 10; s++)
		    	{
		    		max -= step;
		    		if(max < 0)
		    		{
		    			residue = max + step;
		    			stop = s;
		    			break;
		    		}
		    	}
		    	
		        for(int i = 0; i < stop; i++)
		        {
					this.drawTexturedModalRect(e.resolution.getScaledWidth()/2 + 83 - 8 * i, e.resolution.getScaledHeight() - 50, 9, 1, 8, 9);
		        }
		        
		        for(int i = 10; i > stop; i--)
		        {
					this.drawTexturedModalRect(e.resolution.getScaledWidth()/2 + 91 - 8 * i, e.resolution.getScaledHeight() - 50, 1, 1, 8, 9);
		        }
		                
		        if(residue > step/2)
		        {
					this.drawTexturedModalRect(e.resolution.getScaledWidth()/2 + 91 - 8 * (stop+1), e.resolution.getScaledHeight() - 50, 17, 1, 8, 9);
		        }
	        }
	        else
	        {
		        for(int i = 0; i < 10; i++)
		        {
					this.drawTexturedModalRect(e.resolution.getScaledWidth()/2 + 83 - 8 * i, e.resolution.getScaledHeight() - 50, 9, 1, 8, 9);
		        }
        	}
        }
		GL11.glPopMatrix();
	}
}
