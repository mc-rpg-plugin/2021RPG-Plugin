package com.tistory.workshop.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JobVariable {

    private static JobVariable instance = new JobVariable();

    public static JobVariable Instance() {
        return instance;
    }

    public static HashMap<UUID, Boolean> playerAbility = new HashMap<UUID, Boolean>();  // 능력을 사용 중인지를 확인
    public static HashMap<UUID, Long> cooldowns = new HashMap<UUID, Long>();
    public static HashMap<UUID, Long> playerCooldown = new HashMap<UUID, Long>();

    public boolean hasPlayerActivateAbility(Player player) {
        if (!playerAbility.containsKey(player.getUniqueId())) {
            return false;
        }

        return playerAbility.get(player.getUniqueId());
    }

    public void setPlayer(Player player) {
        if (playerAbility.containsKey(player.getUniqueId())) {
            return;
        }

        playerAbility.put(player.getUniqueId(), false);
    }

    public void setCooldowns(Player player, long time) {
        if (time < 1)
            cooldowns.remove(player.getUniqueId());
        else
            cooldowns.put(player.getUniqueId(), time);
    }

    public long getCooldowns(Player player) {
        return cooldowns.getOrDefault(player.getUniqueId(), (long)0);
    }

    public void setPlayerCooldown(Player player, long time) {
        if (time < 0) return;
        if (playerCooldown.containsKey(player.getUniqueId())) return;
        playerCooldown.put(player.getUniqueId(), time);
    }

    public long getPlayerCooldown(Player player) {
        if (playerCooldown.containsKey(player.getUniqueId()))
            return playerCooldown.get(player.getUniqueId());
        return 0;
    }


}
