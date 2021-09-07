package com.tistory.workshop.command;

import com.tistory.workshop.jobs.Fighter;
import com.tistory.workshop.jobs.JobVariable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPGCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) return false;

            Player player = (Player) sender;

            if (strings.length == 0) {
                // TODO help 명령어 사용하게 추후 개발
            }
            else {
                if (strings[0].equals("setCharacter")) {
                    if (strings.length >= 2) {
                        if (strings[1].equals("Fighter")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("파이터 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Fighter");
                                Fighter.hasSneak.put(player.getUniqueId(), false);
                            }
                        }
                        else if (strings[1].equals("Musketeer")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("머스킷티어 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Musketeer");
                            }
                        }
                        else if (strings[1].equals("Berserker")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("버서커 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Berserker");
                            }
                        }
                        else if (strings[1].equals("Wizard")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("마법사 클래스로 설정하였습니다.");
                                JobVariable.setPlayerJob(player, "Wizard");
                            }
                        }
                        else if (strings[1].equals("Assassin")) {
                            if (JobVariable.hasJob(player)) {
                                sender.sendMessage("이미 직업을 선택하셨습니다!");
                                sender.sendMessage("현재 직업: " + JobVariable.getPlayerJob(player));
                            }
                            else {
                                sender.sendMessage("암살자 클래스로 설장하였습니다.");
                                JobVariable.setPlayerJob(player, "Assassin");
                            }
                        }
                    }
                    else {
                        sender.sendMessage("틀린 명령어");   // TODO 추후 빨간색 텍스트로 에러 문자열 전송
                    }
                }
                else if (strings[0].equals("showDescription")) {
                     if (strings.length >= 2) {
                         switch (strings[1]) {
                             case "quick_Hook" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[퀵 훅]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage("플레이어를 가격시, 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("해당 플레이어에게 2배 가량의 피해를 주며");
                                 sender.sendMessage("해당 플레이어가 능력을 발동 중인 상태라면");
                                 sender.sendMessage("3초간 스턴을 겁니다.");
                             }
                             case "ducking" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[더킹]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 8초");
                                 sender.sendMessage("웅크리기를 통하여 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage("바라보는 방향으로 빠르게 이동합니다.");
                                 sender.sendMessage("이동되는 시간동안은 무적이 됩니다.");
                             }
                             case "grenade" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[수류탄]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 20초");
                                 sender.sendMessage(ChatColor.BOLD + "수류탄" + ChatColor.RESET + "을 들고 우 클릭하여 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("20초마다" + ChatColor.BOLD + "  수류탄" + ChatColor.RESET + "을 지급합니다.");
                                 sender.sendMessage(ChatColor.BOLD + "수류탄을 든 채로 우 클릭하여 투척을 하여 해당 수류탄이 땅에 닿으면 즉시 폭파합니다.");
                             }
                             case "head_Shot" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[헤드샷]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("플레이어 공격 4회째마다 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage("플레이어를 상대로 공격 4회째 공격 때 마다 기존 피해량의 2배를 입힙니다.");
                             }
                             case "rush" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[무모한 공격]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage("우 클릭시, 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("바라보는 방향으로 빠르게 이동하여 해당 경로 근처에 있는 생명체들에게 8의 피해량을 줍니다.");
                             }
                             case "berserker_Active" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[광전사]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("체력이 일정량 이하일때 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "* 체력이 50%이하일때 - 힘 1증가");
                                 sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "* 체력이 35%이하일때 - 힘 2증가");
                                 sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "* 체력이 25%이하일때 - 힘 4증가");
                             }
                             case "lighting" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[낙뢰]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 15초");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 좌 클릭시 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 좌 클릭시");
                                 sender.sendMessage("자신이 바라보는 블럭위치에 낙뢰를 떨어뜨립니다. (최대거리: 10블럭)");
                             }
                             case "teleport" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[텔레포트]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 20초");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 우 클릭시 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage(ChatColor.BOLD + "지팡이" + ChatColor.RESET + "를 든 상태에서 우 클릭시");
                                 sender.sendMessage("자신이 바라보는 블럭위치로 순간이동합니다. (최대거리: 10블럭)");
                             }
                             case "assassinate" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[암살]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("플레이어를 가격시, 2%확률로 작동하는 패시브형 능력입니다.");
                                 sender.sendMessage("플레이어를 가격시, 2%확률로 상대 플레이어를 즉사시킵니다.");
                             }
                             case "escape" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[도주]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 30초");
                                 sender.sendMessage("웅크리기를 하여 작동하는 액티브형 능력입니다.");
                                 sender.sendMessage("웅크리기를 하면 현재 위치를 저장합니다.");
                                 sender.sendMessage("다시 웅크리기를 하면 저장하였던 위치로 이동하면서 이동 전 위치에 강력한 폭팔을 일으킵니다.");
                                 sender.sendMessage("죽더라도 저장된 위치는 저장된 채로 있습니다.");
                             }
                             case "killing_Stack" -> {
                                 sender.sendMessage(ChatColor.DARK_RED + "[살생의 경험]");
                                 sender.sendMessage(ChatColor.AQUA + "쿨타임" + ChatColor.WHITE + ": 0초");
                                 sender.sendMessage("생명체 (엔티티)를 죽일 때마다" + ChatColor.BOLD + " 살생의 경험" + ChatColor.RESET + "을 하나씩 얻습니다.");
                                 sender.sendMessage("기존 피해량에 " + ChatColor.BOLD + "살생의 경험" + ChatColor.RESET + "갯수만큼 피해량이 추가됩니다.");
                                 sender.sendMessage(ChatColor.BOLD + "살생의 경험" + ChatColor.RESET + "은 죽을 시 초기화됩니다.");
                             }
                         }
                     }
                }
                else if (strings[1].equals("currentJob") || strings[1].equals("현재직업")) {
                    sender.sendMessage("현재 직업은 " + JobVariable.getPlayerJob(player) + "입니다.");
                }
            }
        return true;
    }
}
