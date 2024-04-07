package com.kabouzeid.gramophone.ui.jobInterview.pages

import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerView
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerViewItem
import com.atiurin.ultron.extensions.click
import org.hamcrest.Matchers.allOf
import com.kabouzeid.gramophone.R

object SongsPage: BasePageWithTab<SongsPage>() {
    private val songsList = UltronRecyclerView(
        allOf(
            withId(R.id.recycler_view),
            hasSibling( withText("Нет песен для отображения") )
        )
    )
    override val pageTabName: String = "ТРЕКИ"

    fun clickOnSongItem(position: Int) {
        // нулевой индекс это контрол перемешать, так что... позиция в списке
        step("Тапнуть по $position-му треку") {
            songsList.getItem<SongsItem>(position).click()
        }
    }

    fun clickOnSongMenu(position: Int) {
        step("Тапнуть по иконке меню $position-го трека") {
            songsList.getItem<SongsItem>(position).menu.click()
        }
    }

    fun clickOnMenuItem(text: String) {
        step("Тапнуть по пункту меню с текстом '$text'") {
            withText(text).click()
        }
    }

    class SongsItem: UltronRecyclerViewItem() {
        val menu by lazy { getChild(withId(R.id.menu)) }
    }
}