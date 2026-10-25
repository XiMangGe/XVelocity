package com.xmangge.xvelocity;

import com.xmangge.xvelocity.commands.VelocityCommand;
import com.xmangge.xvelocity.config.ConfigManager;
import com.xmangge.xvelocity.language.LanguageManager;
import com.xmangge.xvelocity.listeners.InteractListener;
import com.xmangge.xvelocity.listeners.SpeedDisplayListener;
import com.xmangge.xvelocity.speed.MinecartSpeedManager;
import org.bukkit.plugin.java.JavaPlugin;

public class XVelocity extends JavaPlugin {

    private static XVelocity instance;
    private ConfigManager configManager;
    private LanguageManager languageManager;
    private MinecartSpeedManager speedManager;

    @Override
    public void onEnable() {
        instance = this;

        // 初始化配置管理器
        configManager = new ConfigManager(this);
        configManager.loadConfigs();

        // 初始化语言管理器
        languageManager = new LanguageManager(this);
        languageManager.loadLanguage();

        // 初始化速度管理器
        speedManager = new MinecartSpeedManager(this);
        speedManager.onEnable();

        // 注册监听器
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new SpeedDisplayListener(this), this);

        // 注册命令
        getCommand("xvelocity").setExecutor(new VelocityCommand(this));

        getLogger().info("XVelocity v" + getDescription().getVersion() + " enabled!");
    }

    @Override
    public void onDisable() {
        if (speedManager != null) {
            speedManager.onDisable();
        }
        getLogger().info("XVelocity v" + getDescription().getVersion() + " disabled!");
    }

    public static XVelocity getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public MinecartSpeedManager getSpeedManager() {
        return speedManager;
    }
}
