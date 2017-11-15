package uk.tjevans.staffPunch;

import uk.tjevans.staffPunch.StaffPunch;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;


public class PlayerClickEvent implements Listener {
	
	//public static HashMap<Player, Integer> timer = new HashMap();

	@EventHandler
	public void onPlayerClickPlayer(EntityDamageByEntityEvent e) {
		
		if (e.getEntity() instanceof Player){
			
			Player attacker = (Player)e.getDamager();
			Player defender = (Player)e.getEntity();
			//String player1 = defender.getUniqueId().toString();
			//String player2 = attacker.getUniqueId().toString();
			Long cooldown = 29l;
			
			if (StaffPunch.timer2.containsKey(attacker)) {
				//attacker.sendMessage("yolo");
				
				long secondsLeft = StaffPunch.timer2.get(attacker) +1l;
	            if(secondsLeft>0) {
	                // Still cooling down
	                attacker.sendMessage(ChatColor.GOLD + "You cant punch for another "+ secondsLeft +" seconds!");
	            }
	            e.setDamage(0.0);
				e.setCancelled(true);
	           
			}
			
			else if (StaffPunch.timer.containsKey(defender)) {
				//attacker.sendMessage("yolo");
				
				long secondsLeft = StaffPunch.timer.get(defender) +1l;
	            if(secondsLeft>0) {
	                // Still cooling down
	                attacker.sendMessage(ChatColor.GOLD + "You cant punch that user for another "+ secondsLeft +" seconds!");
	            }
	            e.setDamage(0.0);
				e.setCancelled(true);
	           
			}
			
			else {
				//attacker.sendMessage("test");
				
				if (attacker instanceof Player){
				
				if (defender.hasPermission("punch.bepunched")){
					
					
						
					if (attacker.hasPermission("punch.staff")){
						StaffPunch.timer.put(defender, cooldown);
						StaffPunch.timer2.put(attacker, cooldown);
						e.setDamage(0.0);
						e.setCancelled(true);
						defender.setNoDamageTicks(150);
						defender.setVelocity(new Vector (0,2,0));
						defender.setFallDistance(-500);
					}
					else {
						attacker.sendMessage(ChatColor.RED + "You do not have permission to punch this player into the sky");
						e.setDamage(0.0);
						e.setCancelled(true);
					}
				}
			}
				else {
					e.setDamage(0.0);
					e.setCancelled(true);
				}
								
			}
	
		}
	}
}