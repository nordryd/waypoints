package com.nordryd.spigot.waypoints;

import java.util.logging.Logger;

import com.nordryd.spigot.waypoints.event.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>
 * Main class for the Waypoints plugin.
 * </p>
 *
 * @author Nordryd
 */
public class Main extends JavaPlugin
{
    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        logger.info("[Waypoints] Waypoints plugin is now enabled.");
        EventListener.registerEventListeners(this);
    }

    @Override
    public void onDisable() {
        logger.info("[Waypoints] Waypoints plugin is now disabled.");
    }
}
