package com.tistory.workshop.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class JobVariable {

    private static JobVariable instance = new JobVariable();

    public static JobVariable Instance() {
        return instance;
    }

    /*
    - 직업을 저장하는 해시맵
    - 현재 능력을 사용 중인지를 확인하는 해시맵
    - 쿨타임을 저장하는 해시맵
     */

    public static HashMap<UUID, String> playerJob = new HashMap<UUID, String>();            // 플레이어 직업
    public static HashMap<UUID, Long> coolTimes = new HashMap<UUID, Long>();                // 쿨타임 저장
    public static HashMap<UUID, Boolean> useAbilityNow = new HashMap<UUID, Boolean>();      // 현재 능력이 발동 중인지를 확인
    public static HashMap<String, Integer> abilityCoolTime = new HashMap<String, Integer>();// 해당 능력에 부여되는 쿨타임

    // 해당 능력에게 부여되는 쿨타임 (onEnable 에 초기에 넣어질 메소드)
    public static void setAbilityCoolTimes() {
        abilityCoolTime.clear();
        abilityCoolTime.put("quick_hook", 15);
        abilityCoolTime.put("the_king", 8);
        abilityCoolTime.put("grenade", 20);
        abilityCoolTime.put("head_shot", 0);
        abilityCoolTime.put("rush", 15);
        abilityCoolTime.put("berserker_Active", 0);
        abilityCoolTime.put("lighting", 15);
        abilityCoolTime.put("teleport", 20);
        abilityCoolTime.put("assassinate", 0);
        abilityCoolTime.put("escape", 30);
        abilityCoolTime.put("killing_Stack", 0);
    }

    // 플레이어에게 능력을 지정하는 메소드
    public static void setPlayerJob(Player player, String job) {
        UUID playerUUID = player.getUniqueId();
        playerJob.put(playerUUID, job);
    }

    public static boolean hasJob(Player player) {
        if (playerJob.containsKey(player.getUniqueId())) {
            return true;
        }
        return false;
    }


    public static String getPlayerJob(Player player) {
        if (!hasJob(player)) {
            player.sendMessage("아직 직업을 선택하지 않으셨습니다!");
            return null;
        }
        return playerJob.get(player.getUniqueId());
    }

    public static boolean getPlayerJob(Player player, String job) {
        if (!hasJob(player))
            return false;

        return playerJob.get(player.getUniqueId()).equals(job);
    }

    // 쿨타임 조절하는 메소드 영역

    public static void setCoolTime(UUID player, long time) {
        if (time < 1) {
            coolTimes.remove(player);
        }
        else {
            coolTimes.put(player, time);
        }
    }

    public static long getCoolTime(UUID player) {
        return coolTimes.getOrDefault(player, (long) 0);
    }

}
