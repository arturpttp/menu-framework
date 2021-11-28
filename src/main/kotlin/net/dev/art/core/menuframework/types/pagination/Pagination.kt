package net.dev.art.core.menuframework.types.pagination

import net.dev.art.core.libs.API
import net.dev.art.core.menuframework.system.item.MenuItem
import net.dev.art.core.menuframework.system.menu.MenuRows
import net.dev.art.core.menuframework.system.menu.pagination.IPage
import net.dev.art.core.menuframework.system.menu.pagination.IPagination
import org.bukkit.entity.Player

abstract class Pagination<T>(
    override var title: String,
    override val autoFillBorders: Boolean = false,
    override val suffix: String = "",
) : IPagination<T> {

    override val pages: MutableList<IPage> = mutableListOf()
    override val opened: MutableMap<Player, Int> = mutableMapOf()
    override val content: MutableList<MenuItem> = mutableListOf()
    override var amount: Int = 1

    init {
        create()
    }

    override fun create() {
        title += suffix
        getObjects().forEach {
            content.add(
                MenuItem(
                    getItem(it),
                    { event -> onClickItem(event) }
                )
            )
        } //Adding all items in inventory
        var page = Page(title, 54, 1, autoFillBorders).blank(this)
        var slot = 10
        content.forEach { item ->
            run {
                while (API.isColumn(slot, 1) || API.isColumn(slot, 9)) slot++
                if (slot >= 44) {
                    pages.add(page)
                    val nextPage = pages.size + 1
                    page = Page(title, 54, 1, autoFillBorders).blank(this)
                    slot = 10
                }
                item.slot = slot
                page.addItem(item)
                slot++
            }
        }
        pages.add(page)
        amount = pages.size
        pages.forEach { page ->
            getButtons().forEach {
                page.addItem(it)
                page.update(this, amount)
            }
        }
    }

    override fun open(player: Player) {
        getPage(0).open(player)
    }

    override fun open(player: Player, page: Int) {
        getPage(page).open(player)
    }

    override fun getOpened(player: Player): Int {
        if (isOpened(player))
            return opened[player]!!
        return -1
    }

    override fun getOpenedPage(player: Player): IPage {
        return getPage(getOpened(player))
    }

    override fun isOpened(player: Player): Boolean {
        return opened.containsKey(player)
    }

    override fun getPage(page: Int): IPage {
        return pages[page]
    }
}