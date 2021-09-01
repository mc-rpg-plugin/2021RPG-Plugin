package com.tistory.workshop;

import com.tistory.workshop.Assasin.escape;
import com.tistory.workshop.Berserker.Berserker;
import com.tistory.workshop.Musketeer.Grenade;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    ConsoleCommandSender consol = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        // 시작시, 작동할 코드
        consol.sendMessage("[플러그인 활성화 중 입니다.]");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Berserker(), this);
        getServer().getPluginManager().registerEvents(new escape(), this);
        getServer().getPluginManager().registerEvents(new Grenade(), this);
    }

    @Override
    public void onDisable() {
        // 종료시, 작동할 코드
        consol.sendMessage( "[플러그인 비활성화 중 입니다.]");
    }
}
