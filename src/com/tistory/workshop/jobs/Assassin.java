package com.tistory.workshop.jobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class Assassin implements Listener {

    private static HashMap<UUID, Location> saveLocation = new HashMap<UUID,Location>();
    private static HashMap<UUID,Location> previousLocation = new HashMap<UUID,Location>();


    public static int stack = 0;
    ItemStack killingstack = new ItemStack(Material.BONE_MEAL);
    ItemMeta killingstackmeta = (ItemMeta)killingstack.getItemMeta();

    /*@EventHandler
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
    }*/

    @EventHandler
    public void assassinate(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            Entity victim = e.getEntity();
            ((Damageable) victim).damage((2+stack) * 1.5);
            player.sendMessage("현재 피해량: " + (2+stack) * 1.5);
            Location victimLocation = victim.getLocation();
            double nx;
            double nz;
            float angle = victimLocation.getYaw() + 90;
            if (angle < 0) angle += 360;
            nx = Math.cos(Math.toRadians(angle));
            nz = Math.sin(Math.toRadians(angle));
            Location location = new Location(victimLocation.getWorld(), victimLocation.getX() - nx,
                    victimLocation.getY(), victimLocation.getZ() - nz, victimLocation.getYaw(), victimLocation.getPitch());
            player.teleport(location);
        }
    }

    @EventHandler
    public void killedstack(EntityDeathEvent e) {

        LivingEntity entity = e.getEntity();
        Player player = (Player) e.getEntity().getKiller();
        if (entity.getType() == EntityType.PLAYER) {
            stack = 0;
        }
        if (killingstackmeta != null && player != null) {
            killingstackmeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
            killingstackmeta.setDisplayName(ChatColor.RED + "살인청부");
            killingstackmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            killingstack.setItemMeta(killingstackmeta);
            player.getInventory().addItem(killingstack);
            for (ItemStack itemStack : player.getInventory().all(killingstack).values()) {
                stack++;
            }
        }
    }

    @EventHandler
    public void cancelDropItem(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().containsEnchantment(Enchantment.BINDING_CURSE) &&
            e.getItemDrop().getItemStack().getType() == Material.BONE_MEAL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void cancelDropItem(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() == Material.BONE_MEAL &&
            player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.BINDING_CURSE)) {
            e.setCancelled(true);
        }
    }
}
