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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class Assassin implements Listener {

    private static HashMap<UUID, Location> saveLocation = new HashMap<UUID,Location>();
    private static HashMap<UUID,Location> previousLocation = new HashMap<UUID,Location>();


    public static int stack = 0;
    ItemStack killingStack = new ItemStack(Material.BONE_MEAL);
    ItemMeta killingStackMeta = (ItemMeta)killingStack.getItemMeta();

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

    @EventHandler
    public void assassinate(EntityDamageByEntityEvent e) {
        double rand = Math.random(); // 0.0 ~ 1.0
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            Entity victim = e.getEntity();
            ((Damageable) victim).damage((2 + stack) * 1.5);
            player.sendMessage("현재 피해량: " + (2 + stack) * 1.5);
            player.sendMessage("현재 스텍: " + stack);
            if((rand * 100) <= 2) {
                ((Damageable) victim).damage(999999999);
                player.sendMessage("즉사");
            }
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
    public void killingStackUp(EntityDeathEvent e) {

        LivingEntity entity = e.getEntity();
        Player player = (Player) e.getEntity().getKiller();
        if (entity.getType() == EntityType.PLAYER) {
            stack = 0;
        }
        if (killingStackMeta != null && player != null) {
            killingStackMeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
            killingStackMeta.setDisplayName(ChatColor.RED + "살인청부");
            killingStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            killingStack.setItemMeta(killingStackMeta);
            player.getInventory().addItem(killingStack);
            ItemStack[] inv = player.getInventory().getContents();
            int temp = 0;
            for (ItemStack itemStack : inv) {
                if (itemStack != null) {
                    if (itemStack.getType() == Material.BONE_MEAL && itemStack.containsEnchantment(Enchantment.BINDING_CURSE)) {
                        temp = temp + itemStack.getAmount();
                    }
                }
            }
            stack = temp;
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
