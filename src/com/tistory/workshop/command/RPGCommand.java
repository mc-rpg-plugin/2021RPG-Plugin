package com.tistory.workshop.command;

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
                            sender.sendMessage("파이터 클래스로 설정하였습니다.");
                        }
                    }
                    else {
                        sender.sendMessage("틀린 명령어");   // TODO 추후 빨간색 텍스트로 에러 문자열 전송
                    }
                }
            }

            return true;
    }
}
