package io.github.toberocat.improvedfactions.modules.power.commands

import io.github.toberocat.improvedfactions.ImprovedFactionsPlugin
import io.github.toberocat.improvedfactions.database.DatabaseManager.loggedTransaction
import io.github.toberocat.improvedfactions.modules.claimparticle.ClaimParticleModule
import io.github.toberocat.improvedfactions.modules.power.impl.FactionPowerRaidModuleHandleImpl
import io.github.toberocat.improvedfactions.permissions.Permissions
import io.github.toberocat.improvedfactions.translation.sendLocalized
import io.github.toberocat.improvedfactions.user.factionUser
import io.github.toberocat.improvedfactions.utils.command.CommandCategory
import io.github.toberocat.improvedfactions.utils.command.CommandMeta
import io.github.toberocat.improvedfactions.utils.options.FactionPermissionOption
import io.github.toberocat.improvedfactions.utils.options.InFactionOption
import io.github.toberocat.toberocore.command.PlayerSubCommand
import io.github.toberocat.toberocore.command.arguments.Argument
import io.github.toberocat.toberocore.command.options.Options
import org.bukkit.entity.Player
import kotlin.math.round
import java.util.UUID

const val CLAIM_PARTICLE_COMMAND_DESCRIPTION = "base.command.border.description"
const val CLAIM_PARTICLE_COMMAND_CATEGORY = CommandCategory.CLAIM_CATEGORY
const val CLAIM_PARTICLE_COMMAND_MODULE = ClaimParticleModule.MODULE_NAME

@CommandMeta(
    description = CLAIM_PARTICLE_COMMAND_DESCRIPTION,
    category = CLAIM_PARTICLE_COMMAND_CATEGORY,
    module = CLAIM_PARTICLE_COMMAND_MODULE
)
open class ClaimParticleCommand(
    private val plugin: ImprovedFactionsPlugin,
    private val particleToggle: MutableMap<UUID, Boolean>
) : PlayerSubCommand("border") {
    override fun options() = Options.getFromConfig(plugin, label)

    override fun arguments() = arrayOf<Argument<*>>()

    override fun handle(player: Player, args: Array<out String>): Boolean {
        val uuid = player.uniqueId

        val isEnabled = !particleToggle.getOrDefault(uuid, false)
        particleToggle[uuid] = isEnabled

        if (isEnabled) {
            player.sendLocalized("base.command.border.enabled")
        } else {
            player.sendLocalized("base.command.border.disabled")
        }

        return true
    }
}
