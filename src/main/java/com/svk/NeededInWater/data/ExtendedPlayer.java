package com.svk.NeededInWater.data;

import com.svk.NeededInWater.base.CommonProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties{

	public static final String PROP_NAME = "ExtendedPlayer";
	private final EntityPlayer player;
	public ExtendedPlayerData data;
	
	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
		this.data = new ExtendedPlayerData();
		
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.PROP_NAME, new ExtendedPlayer(player));
	}
	
	
	public static final ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer) player.getExtendedProperties(PROP_NAME);
	}

	
	
	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = new NBTTagCompound();

		props.setByteArray("data", ExtendedPlayerData.serialize(data));
		compound.setTag(PROP_NAME, props);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = (NBTTagCompound) compound.getTag(PROP_NAME);

		this.data = ExtendedPlayerData.deserialize(props.getByteArray("data"));
		
	}
	
	@Override
	public void init(Entity entity, World world){}
	
	private static final String getSaveKey(EntityPlayer player) {
		return player.getCommandSenderName() + ":" + PROP_NAME;
	}
	
	public static final void saveProxyData(EntityPlayer player) {
		NBTTagCompound savedData = new NBTTagCompound();
		ExtendedPlayer.get(player).saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}
	
	public static final void loadProxyData(EntityPlayer player) 
	{
		ExtendedPlayer playerData = ExtendedPlayer.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		if (savedData != null) { playerData.loadNBTData(savedData); 
		}
	}

	public void onUpdate() {
		
	}

}