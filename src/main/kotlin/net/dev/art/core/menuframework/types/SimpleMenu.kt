package net.dev.art.core.menuframework.types

import net.dev.art.core.menuframework.MenuFramework
import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.IMenu
import net.dev.art.core.menuframework.system.menu.MenuHolder
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

open class SimpleMenu(final override var title: String, final override var rows: Int, override val command: String? = null) : IMenu {

    init {
        MenuFramework.menus.add(this)
    }

    final override var holder: MenuHolder = MenuHolder(this)
    final override var inventory: Inventory = Bukkit.createInventory(this.holder, this.rows, this.title)
    override var content: MutableMap<Int, MenuItem> = mutableMapOf()

    override val openers: MutableList<Player> = mutableListOf()

    private lateinit var player: Player

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

    override fun setItem(slot: Int, item: MenuItem) {
        item.complete(this)
        content[slot] = item;
    }

    override fun item(item: MenuItem) {
        addItem(item)
    }

    override fun item(slot: Int, item: MenuItem) {
        setItem(slot, item)
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
            if (content[slot]?.consumer != null)
                content[slot]?.consumer?.accept(ev)
            event.isCancelled = ev.isCancelled
            onClick(ev)
            MenuFramework.instance.pluginManager.callEvent(ev)
        }
    }

    /**
     * It will be never used
     */
    override fun back(): IMenu? = null

    /**
     * It will be never used
     */
    override fun next(): IMenu? = null
}