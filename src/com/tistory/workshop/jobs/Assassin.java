package com.tistory.workshop.jobs;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class Assassin implements Listener {

    private static HashMap<UUID, Location> saveLocation = new HashMap<UUID,Location>();
    private static HashMap<UUID,Location> previousLocation = new HashMap<UUID,Location>();

    @EventHandler
    public void escape(PlayerToggleSneakEvent e) {

        Player p = e.getPlayer();

        if(p.isSneaking() && saveLocation.isEmpty()) {
            saveLocation.put(p.getUniqueId(), p.getLocation());
            p.sendMessage("위치저장!");
        }else if (p.isSneaking()){
            previousLocation.put(p.getUniqueId(),p.getLocation());
            p.teleport(saveLocation.get(p.getUniqueId()));
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,40,9999));
            p.getWorld().createExplosion(previousLocation.get(p.getUniqueId()),3,false,false);
            p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.sendMessage("위치이동!");
            saveLocation.remove(p.getUniqueId());
            previousLocation.remove(p.getUniqueId());
        }
    }
}
