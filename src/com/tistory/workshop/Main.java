package com.tistory.workshop;

import com.tistory.workshop.jobs.*;
import com.tistory.workshop.book.Book;
import com.tistory.workshop.command.RPGCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin implements Listener {

    // TODO 지금 구현한 책 테스트하여야함
    ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        // 시작시, 작동할 코드
        console.sendMessage("[플러그인 활성화 중 입니다.]");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Book(), this);
        // getServer().getPluginManager().registerEvents(new Musketeer(), this);
        // getServer().getPluginManager().registerEvents(new Berserker(), this);
        getServer().getPluginManager().registerEvents(new Assassin(), this);
        getServer().getPluginManager().registerEvents(new Wizard(), this);
        // getServer().getPluginManager().registerEvents(new Fighter(), this);

        getCommand("rpg_").setExecutor(new RPGCommand());
    }

    @Override
    public void onDisable() {
        // 종료시, 작동할 코드
        console.sendMessage( "[플러그인 비활성화 중 입니다.]");
    }

}
