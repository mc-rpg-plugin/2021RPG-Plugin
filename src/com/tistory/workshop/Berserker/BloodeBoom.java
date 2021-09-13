package com.tistory.workshop.Berserker;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BloodeBoom implements Listener {

    @EventHandler
    public void blood(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action action = e.getAction();

    if((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD
    || player.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD
    || player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD
    || player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD
    || player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD){
        player.getWorld().spawnParticle(Particle.SOUL,player.getLocation(),3);

        }
    }
}
