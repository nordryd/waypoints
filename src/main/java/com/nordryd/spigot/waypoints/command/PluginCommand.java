package com.nordryd.spigot.waypoints.command;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * <p>
 * All commands for the MobArena plugin.
 * </p>
 *
 * @author Nordryd
 */
public enum PluginCommand
{
    HELP("help", false),
    HELLO_WORLD("hello_world", false),
    ECHO("echo", false, "arg"),
    ADD("add", false, "numX", "numY"),
    MULTIPLY("multiply", true, "numX", "numY");

    public static final String MASTER_CMD_PREFIX = "waypoints";
    public static final String MASTER_CMD_PREFIX_ALIAS = "wp";

    private final String command;
    private final boolean isAdminCommand;
    private final String[] parameters;

    PluginCommand(final String command, final boolean isAdminCommand, final String... autocompleteFields) {
        this.command = command;
        this.isAdminCommand = isAdminCommand;
        this.parameters = autocompleteFields;
    }

    PluginCommand(final String command, final boolean isAdminCommand) {
        this(command, isAdminCommand, "");
    }

    /**
     * @return the command itself.
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return the name for this command's parameter(s).
     */
    public String[] getParameters() {
        return parameters;
    }

    /**
     * @return {@code true} if this command may only be used by {@code marena.admin} level users. {@code false} if they may be used by anyone.
     */
    public boolean isAdminCommand() {
        return isAdminCommand;
    }

    /**
     * @return the usage string for this command.
     */
    public String getUsage() {
        return ChatColor.DARK_RED + "USAGE: " + ChatColor.RED + "/" + MASTER_CMD_PREFIX_ALIAS + " " + getCommand() +
                stream(parameters).map(param -> " [" + param + "]").collect(joining()) + ChatColor.RESET;
    }

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder("/" + MASTER_CMD_PREFIX_ALIAS + " " + this.command);

        if (!this.parameters[0].isEmpty()) {
            for (String param : this.parameters) {
                strBuilder.append(" [").append(param).append("]");
            }
        }

        return strBuilder.toString();
    }

    /**
     * Validates and returns the given issued command.
     *
     * @param command The command given after /marena
     * @param sender The command sender.
     * @param cargs The number of arguments given in the command.
     * @return the corresponding command wrapped in an {@link Optional}.
     * If the issued command does not map to any command, then the returned {@link Optional} will be empty instead.
     */
    public static Optional<PluginCommand> validateCommand(final String command, final CommandSender sender,
            final int cargs) {
        final Optional<PluginCommand> matchingCmd = stream(PluginCommand.values())
                .filter(cmd -> cmd.getCommand().equalsIgnoreCase(command)).findFirst();
        if (matchingCmd.isPresent()) {
            final PluginCommand cmd = matchingCmd.get();
            final boolean isValid = isIssuedCommandValid(matchingCmd.get(), sender, cargs);

            if (!isValid) {
                sender.sendMessage(cmd.getUsage());
                return Optional.empty();
            }

            return matchingCmd;
        }

        return Optional.empty();
    }

    private static boolean isIssuedCommandValid(final PluginCommand cmd, CommandSender sender, final int cargs) {
        return (!cmd.isAdminCommand || sender.isOp()) &&
                (isBlank(cmd.parameters[0]) || ((cmd.parameters.length + 1)) == cargs);
    }
}
