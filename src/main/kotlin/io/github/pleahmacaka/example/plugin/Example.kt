package io.github.pleahmacaka.example.plugin

import org.bukkit.plugin.java.JavaPlugin

class Example : JavaPlugin() {
    override fun onEnable() {
        logger.info("Example Plugin Enabled!")
    }
}