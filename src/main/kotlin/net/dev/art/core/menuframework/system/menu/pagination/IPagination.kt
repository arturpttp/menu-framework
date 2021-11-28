package net.dev.art.core.menuframework.system.menu.pagination

import net.dev.art.core.menuframework.system.event.MenuItemClickEvent
import net.dev.art.core.menuframework.system.item.MenuItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface IPagination<T> {

    val autoFillBorders: Boolean
    val pages: MutableList<IPage>
    val opened: MutableMap<Player, Int>
    val content: MutableList<MenuItem>
    var amount: Int
    val suffix: String
    var title: String

    fun create()

    fun open(player: Player)

    fun open(player: Player, page: Int)

    fun getOpened(player: Player): Int

    fun getOpenedPage(player: Player): IPage

    fun isOpened(player: Player): Boolean

    fun onClickItem(event: MenuItemClickEvent)

    fun getObjects(): Array<T>

    fun getItem(t: T): ItemStack

    fun getButtons(): Array<MenuItem>

    fun getPage(page: Int): IPage
}