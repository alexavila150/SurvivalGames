package io.github.m0pt0pmatt.SpongeSurvivalGames.Commands;

import com.google.common.base.Optional;
import io.github.m0pt0pmatt.SpongeSurvivalGames.SpongeSurvivalGamesPlugin;
import io.github.m0pt0pmatt.SpongeSurvivalGames.SurvivalGameState;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

/**
 * Created by matthew on 9/27/15.
 */
public class DeleteSurvivalGameCommand implements CommandExecutor {

    private final SpongeSurvivalGamesPlugin plugin;

    public DeleteSurvivalGameCommand(SpongeSurvivalGamesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        Optional<String> id = args.getOne("id");
        if (!id.isPresent()) {
            plugin.getLogger().error("Survival Game ID is not present.");
            return CommandResult.empty();
        }

        if (!plugin.getSurvivalGameMap().containsKey(id.get())) {
            plugin.getLogger().error("No Survival Game has specified ID \"" + id.get() + "\".");
            return CommandResult.empty();
        }

        if (!plugin.getSurvivalGameMap().get(id.get()).getGameState().equals(SurvivalGameState.STOPPED)) {
            plugin.getLogger().error("Survival Game \"" + id.get() + "\" must be STOPPED before it can be deleted.");
            return CommandResult.empty();
        }

        plugin.getSurvivalGameMap().remove(id.get());
        plugin.getLogger().info("Survival Game \"" + id.get() + "\" deleted.");

        return CommandResult.success();
    }
}
