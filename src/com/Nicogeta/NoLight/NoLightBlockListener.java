package com.Nicogeta.NoLight;

//import java.awt.List;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;


public class NoLightBlockListener extends BlockListener{
	
	final NoLight plugin;
	
	public NoLightBlockListener(NoLight instance) {
		plugin = instance;
	}
	
	public void onBlockPlace (BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		
		boolean ableToPlace = false;
    	if((NoLight.Permissions == null && player.isOp()))
    		ableToPlace = true;
    	else if(NoLight.Permissions != null && NoLight.Permissions.has(player, "NoLight.place"))
    		ableToPlace = true;	
	
    	
    		if(!ableToPlace){
    	
    			if(event.getBlockPlaced().getType() == Material.TORCH || event.getBlockPlaced().getType() == Material.GLOWSTONE) {
			
    				Location location = event.getBlockPlaced().getLocation();
			
    				if(radius(location, 5, Material.MOB_SPAWNER)) {
    					event.setCancelled(true);
    				}
    			}
    		}
	}
	
	public boolean radius(Location l, int radius, Material type) {
		int spawnerAround = 0;
	    ArrayList<Location> out = new ArrayList<Location>();
	    double r2 = Math.pow(radius, 2);
	    for (double x = l.getX() - radius; x <= l.getX() + radius; x++) {
	        double x2 = Math.pow(l.getX() - x, 2);
	        for (double y = l.getY() - radius; y <= l.getY() + radius; y++) {
	            double y2 = Math.pow(l.getY() - y, 2);
	            for (double z = l.getZ() - radius; z <= l.getZ() + radius; z++) {
	            	int intX = (int)x;
	            	int intY = (int)y;
	            	int intZ = (int)z;
	            	/*if (((x2 + y2 + Math.pow(l.getZ() - z, 2)) <= r2) && (l.getWorld().getBlockAt(x, y, z) == type)) {
	                    out.add(new Location(l.getWorld(), x, y, z));*/
	            	if (((x2 + y2 + Math.pow(l.getZ() - z, 2)) <= r2) && (l.getWorld().getBlockAt(intX, intY, intZ).getType() == type)) {
	                    out.add(new Location(l.getWorld(), x, y, z));
	                    spawnerAround = 1;
	                }
	            }
	        }
	    }
	    if(spawnerAround == 1) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	

}