package net.dev.art.core.menuframework.system.menu.pagination

import net.dev.art.core.libs.utils.ItemBuilder
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.IMenu
import org.bukkit.Material

interface IPage : IMenu {

    var number: Int
    val autoFillBorders: Boolean

    fun update(pagination: IPagination<*>, amount: Int)

    fun blank(pagination: IPagination<*>): IPage

    /**
     * Default Previous page item, you can change in your own implementation class
     */
    fun getPreviousPageItem(page: IPage): MenuItem = MenuItem(
        ItemBuilder(Material.ARROW)
            .name("§aBack to previous page")
            .lore("§7click-me to back to previous page"),
        {
            TODO("get previous page and open to player/whoClicked")
        },
        slotFromPageNumber(page.rows, true)
    )

    /**
     * Default next page item, you can change in your own implementation class
     */
    fun getNextPageItem(page: IPage): MenuItem = MenuItem(
        ItemBuilder(Material.ARROW)
            .name("§aGo to next page")
            .lore("§7click-me to go to next page"),
        {
            TODO("get next page and open to player/whoClicked")
        },
        slotFromPageNumber(page.rows, false)
    )

    private fun slotFromPageNumber(rows: Int, back: Boolean): Int {
        return when (rows/9) {
            1 -> if (back) 0 else 8
            2 -> if (back) 9 else 17
            3 -> if (back) 9 else 17
            4 -> if (back) 27 else 35
            5 -> if (back) 18 else 16
            6 -> if (back) 45 else 53
            else -> 0
        }
    }

}