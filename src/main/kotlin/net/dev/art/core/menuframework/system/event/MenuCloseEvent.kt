package net.dev.art.core.menuframework.system.event

import net.dev.art.core.menuframework.system.menu.IMenu
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent

class MenuCloseEvent(
    override val menu: IMenu,
    val raw: InventoryCloseEvent,
    val player: Player = raw.player as Player
) : MenuEvent(menu) {

    init {
        if (menu.isOpenedBy(player))
            menu.close(player)
    }

}