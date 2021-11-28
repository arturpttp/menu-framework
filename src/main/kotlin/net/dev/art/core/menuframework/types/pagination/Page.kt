package net.dev.art.core.menuframework.types.pagination

import net.dev.art.core.libs.API
import net.dev.art.core.libs.interfaces.Replaces
import net.dev.art.core.libs.utils.ItemBuilder
import net.dev.art.core.menuframework.MenuFramework
import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.IMenu
import net.dev.art.core.menuframework.system.menu.MenuHolder
import net.dev.art.core.menuframework.system.menu.pagination.IPage
import net.dev.art.core.menuframework.system.menu.pagination.IPagination
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import java.util.*

class Page(
    override var title: String,
    override var rows: Int = 54,
    override var number: Int,
    override val autoFillBorders: Boolean = false
) : IPage, Replaces {

    override var content: MutableMap<Int, MenuItem> = mutableMapOf()
    override var holder: MenuHolder = MenuHolder(this)
    override var inventory: Inventory = Bukkit.createInventory(holder, rows, title)

    override val openers = mutableListOf<Player>() //Unused
    lateinit var player: Player

    override fun update(pagination: IPagination<*>, amount: Int) {
        if (number < pagination.amount) addItem(getNextPageItem(pagination.getPage(number + 1)))
        if (number > 1) addItem(getPreviousPageItem(pagination.getPage(number - 1)))
        addReplacer("\$page", number.toString() + "")
        addReplacer("\$max", amount.toString() + "")
        title = getReplaced(title)
    }

    override fun open(player: Player) {
        this.player = player
        update()
        openers.add(player)
        content.forEach {
            this.inventory.setItem(it.key, it.value)
        }

        onOpen(this, player)
    }

    override fun close(player: Player) {
        this.player = player
        if (openers.contains(player))
            openers.remove(player)
        onClose(this, player)
    }

    override fun isOpenedBy(player: Player): Boolean = openers.contains(player)

    override fun update() {
        inventory = Bukkit.createInventory(holder, rows, title)
        inventory.clear()
        inventory.contents = content.values.toList().toTypedArray()
    }

    override fun refresh() {
        //Nothing can happen here
    }

    override fun addItem(item: MenuItem) {
        item.complete(this)
        content[item.slot] = item
    }

    override fun onOpen(menu: IMenu, player: Player) {
    }

    override fun onClose(menu: IMenu, player: Player) {
    }

    override fun onClick(event: MenuItemClickEvent) {}

    override fun onInventoryClick(event: InventoryClickEvent) {
        val slot = event.rawSlot
        if (slot >= 0 && slot < inventory.size && content.containsKey(slot)) {
            val ev = MenuItemClickEvent(this, player, slot, content[slot]!!, event)
            content[slot]?.onInventoryClick(ev)
            onClick(ev)
            MenuFramework.instance.pluginManager.callEvent(ev)
        }
    }

    override fun back(): IMenu? {
        TODO("Not yet implemented")
    }

    override fun next(): IMenu? {
        TODO("Not yet implemented")
    }

    override fun blank(pagination: IPagination<*>): IPage {
        this.update(pagination, this.number)
        if (autoFillBorders) {
            var i = 0
            while (i < rows) {
                while (i in 10..43 && !API.isColumn(i, 1) && !API.isColumn(i, 9)) {
                    i++
                }
                val random = Random()
                val colorCode = API.colorCodes[random.nextInt(API.colorCodes.size) - 1]
                addItem(
                    MenuItem(
                        ItemBuilder(Material.STAINED_GLASS_PANE).name("$colorCode&k####")
                            .meta(11),
                        {},
                        i
                    )
                )
                i++
            }
        }
        return this
    }
}