package uk.tjevans.staffPunch;

import java.util.HashMap;
//import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;


public class StaffPunch extends JavaPlugin {
	
	public StaffPunch staffpunch;
	
	public static HashMap <Player, Long> timer = new HashMap<Player, Long>();
	public static HashMap <Player, Long> timer2 = new HashMap<Player, Long>();
	
	public void decrease() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
        	public void run() {
                for (Entry<Player, Long> e : timer.entrySet()) {
                    if (e.getValue() != 0) {
                        long fixed = e.getValue() - 1;
                        timer.put(e.getKey(), fixed);
                    } else {
                        timer.remove(e.getKey());
                    }
                }
            }
        }, 0L, 20L);
        }
	
	public void decrease2() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
        	public void run() {
                for (Entry<Player, Long> e : timer2.entrySet()) {
                    if (e.getValue() != 0) {
                        long fixed = e.getValue() - 1;
                        timer2.put(e.getKey(), fixed);
                    } else {
                        timer2.remove(e.getKey());
                    }
                }
            }
        }, 0L, 20L);
        }

	@Override
    public void onEnable() {
		decrease();
		decrease2();
		getLogger().info("StaffPunch has been Enabled!");
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new PlayerClickEvent(), this);
		// TODO Insert logic to be performed when the plugin is enabled
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("StaffPunch has been Disbled!");
        // TODO Insert logic to be performed when the plugin is disabled
    }
}
