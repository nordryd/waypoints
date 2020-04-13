package com.nordryd.spigot.waypoints.command;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nordryd.spigot.waypoints.event.EventListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>
 * Listens for and handles issued commands.
 * </p>
 *
 * @author Nordryd
 */
public class CommandListener extends EventListener implements CommandExecutor, TabCompleter
{
    /**
     * Constructor.
     *
     * @param jPlugin the plugin instance.
     */
    public CommandListener(final JavaPlugin jPlugin) {
        super(jPlugin);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final org.bukkit.command.Command issuedCommand,
            final String label, final String... vargs) {
        if (!((sender instanceof Player) && PluginCommand.MASTER_CMD_PREFIX.equalsIgnoreCase(label))) {
            return false;
        }

        final Player player = (Player) sender;
        final Optional<PluginCommand> command = PluginCommand
                .validateCommand(((vargs.length == 0) ? "" : vargs[0]), sender, vargs.length);
        if (!command.isPresent()) {
            return false;
        }

        switch (command.get()) {
            case HELP:
                final String commandList = ChatColor.BLUE + "Available Commands:" + ChatColor.RESET +
                        stream(PluginCommand.values()).map(cmd -> "\n" + ChatColor.AQUA + cmd.toString())
                                .collect(joining());
                player.sendMessage(commandList);
                return true;
            case ADD:
                final int x = parseInt(vargs[0]), y = parseInt(vargs[1]);
                player.sendMessage(format("%d + %d = %d", x, y, x + y));
                return true;
            case ECHO:
                player.sendMessage("Echoing..." + vargs[0]);
                return true;
            case HELLO_WORLD:
                player.sendMessage("Hello, World!");
                return true;
            case MULTIPLY:
                final int a = parseInt(vargs[0]), b = parseInt(vargs[1]);
                player.sendMessage(format("%d + %d = %d", a, b, a * b));
                return true;
            default:
                return false;
        }
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final org.bukkit.command.Command issuedCommand,
            final String label, final String... vargs) {
        if (sender instanceof Player) {
            if (PluginCommand.MASTER_CMD_PREFIX.equalsIgnoreCase(issuedCommand.getName())) {
                return sortTabComplete(stream(PluginCommand.values()).map(PluginCommand::toString).collect(toList()));
            }
        }

        return emptyList();
    }

    private static List<String> sortTabComplete(final List<String> unsortedTabComplete) {
        final List<String> sortedTabComplete = new ArrayList<>(unsortedTabComplete);
        sortedTabComplete.sort(comparing(commandName -> commandName));
        return sortedTabComplete;
    }
}
