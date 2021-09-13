package com.tistory.workshop.Magition;


import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Blackmagic implements Listener {

    @EventHandler
    public void leftclick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action action = e.getAction();
        if((action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD){
            ((Damageable)player).damage(4);
            double Block = 20D;

            List<LivingEntity> entity = player.getLocation().getWorld().getLivingEntities();

            for(LivingEntity near : entity){
                if(near.getLocation().distance(player.getLocation()) <= Block){
                    if(near == player)
                        continue;
                    near.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,2000,1));

                }

            }
        }
    }
}
