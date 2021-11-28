package net.dev.art.core.menuframework.system.menu

import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import net.dev.art.core.menuframework.system.item.MenuItem
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

interface IMenu {

    var title: String
    var rows: Int

    var inventory: Inventory
    var holder: MenuHolder
    var content: MutableMap<Int, MenuItem>

    val openers: MutableList<Player>
    val command: String?

    fun open(player: Player)

    fun close(player: Player)

    fun isOpenedBy(player: Player): Boolean

    fun update()

    fun refresh()

    fun addItem(item: MenuItem)

    fun setItem(slot: Int, item: MenuItem)

    fun item(item: MenuItem)

    fun item(slot: Int, item: MenuItem)

    fun onOpen(menu: IMenu, player: Player)

    fun onClose(menu: IMenu, player: Player)

    fun onClick(event: MenuItemClickEvent)

    fun onInventoryClick(event: InventoryClickEvent)

    fun back(): IMenu?

    fun next(): IMenu?


}