package net.dev.art.core.menuframework.system.menu

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class MenuHolder(var menu: IMenu) : InventoryHolder {

    private val inventory: Inventory = menu.inventory

    override fun getInventory(): Inventory {
        return inventory
    }
}