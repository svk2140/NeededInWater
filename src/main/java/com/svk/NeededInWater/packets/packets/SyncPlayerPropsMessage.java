package com.svk.NeededInWater.packets.packets;

import com.svk.NeededInWater.data.ExtendedPlayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SyncPlayerPropsMessage extends AbstractPacket{
	
	private NBTTagCompound data;
	public SyncPlayerPropsMessage() {}

	// We need to initialize our data, so provide a suitable constructor:
	public SyncPlayerPropsMessage(EntityPlayer player) {
		data = new NBTTagCompound();
		ExtendedPlayer.get(player).saveNBTData(data);
	}

	@Override
	public void encodeInto(ChannelHandlerContext var1, ByteBuf var2) {
		ByteBufUtils.writeTag(var2, data);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext var1, ByteBuf var2) {
		data = ByteBufUtils.readTag(var2);		
	}

	
	@Override
	public void handleClientSide(EntityPlayer var1) {
		ExtendedPlayer.get(var1).loadNBTData(this.data);		
	}

	@Override
	public void handleServerSide(EntityPlayer var1) {}
}
