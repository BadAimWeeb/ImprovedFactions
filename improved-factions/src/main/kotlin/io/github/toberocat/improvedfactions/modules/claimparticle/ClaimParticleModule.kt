package io.github.toberocat.improvedfactions.modules.claimparticle

import io.github.toberocat.improvedfactions.ImprovedFactionsPlugin
import io.github.toberocat.improvedfactions.modules.base.BaseModule
import io.github.toberocat.improvedfactions.modules.claimparticle.config.ClaimParticleModuleConfig
import io.github.toberocat.improvedfactions.modules.claimparticle.handles.RenderParticlesTask
import io.github.toberocat.improvedfactions.modules.power.commands.ClaimParticleCommand
import io.github.toberocat.toberocore.command.CommandExecutor
import java.util.UUID

class ClaimParticleModule : BaseModule {
    override val moduleName = MODULE_NAME
    override var isEnabled = false

    val config = ClaimParticleModuleConfig()
    val particleToggle = mutableMapOf<UUID, Boolean>()

    override fun onEnable(plugin: ImprovedFactionsPlugin) {
        RenderParticlesTask(config, particleToggle).runTaskTimer(plugin, config.particleSpawnInterval, config.particleSpawnInterval)
    }

    override fun reloadConfig(plugin: ImprovedFactionsPlugin) {
        config.reload(plugin.config)
    }

    override fun addCommands(plugin: ImprovedFactionsPlugin, executor: CommandExecutor) {
        executor.addChild(ClaimParticleCommand(plugin, particleToggle))
    }

    companion object {
        const val MODULE_NAME = "claim-particles"
        fun claimParticlesModule() =
            ImprovedFactionsPlugin.instance.moduleManager.getModule<ClaimParticleModule>(MODULE_NAME)

        fun claimParticlesPair() = MODULE_NAME to ClaimParticleModule()
    }
}