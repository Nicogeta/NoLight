package com.Nicogeta.NoLight;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
//import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nicogeta.NoLight.NoLight;
import com.Nicogeta.NoLight.NoLightBlockListener;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;


public class NoLight extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions;
	private final NoLightBlockListener blockListener = new NoLightBlockListener(this);
	
	 private void setupPermissions() {
	      Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (NoLight.Permissions == null) 
	      {
	          if (test != null) {
	              NoLight.Permissions = ((Permissions)test).getHandler();
	              log.info("NoLight has detected Permissions!");
	          } else {
	             log.info("NoLight has not detected Permissions.");
	          }
	      }
	  }
	 
	 public void onDisable() {
		 log.info("NoLight DISABLE");
	 }
	 
	 @Override
	 public void onEnable() {
		 log.info("NoLight ENABLE (by Nicogeta)");
		 log.info("NoLight ver 0.1");
		 PluginManager pm = getServer().getPluginManager();
		 pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Priority.Normal, this);
		 setupPermissions();
	 }
	 
/*		public void recordEvent(PlayerLoginEvent event) {
			// TODO Auto-generated method stub
			
		}  */
	
}
