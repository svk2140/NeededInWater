package com.svk.NeededInWater.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedPlayerData implements Serializable{
	
	public boolean haveBeenOnServerBefore = false;
	public int isThirst = 1000000;
	public float factorThirst = 0;
	public int durationFactorThirst = 0;

	public static byte[] serialize(ExtendedPlayerData data){
		byte[] bytes = null; 
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(data);
		  bytes = bos.toByteArray();
		  
		} catch (IOException e) {e.printStackTrace();
		} finally {
		  try {
		    if (out != null) {
		      out.close();
		    } } catch (IOException ex) {}
		  try {
		    bos.close();
		  } catch (IOException ex) {}
		}
		return bytes;
	}
	
	public static ExtendedPlayerData deserialize(byte[] bytes){
		ExtendedPlayerData data = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		try {
		  in = new ObjectInputStream(bis);
		  data = (ExtendedPlayerData) in.readObject(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {  try {
		    bis.close();
		  } catch (IOException ex) {}
		  try {
		    if (in != null) {
		      in.close();
		    }
		  } catch (IOException ex) {}
		}
		return data;
	}
}
