package com.kabouzeid.gramophone.ui.jobInterview.pages

import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.allure.step.step
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.isDisplayed
import com.atiurin.ultron.extensions.replaceText
import com.kabouzeid.gramophone.R
import org.hamcrest.Matchers.allOf

object NewPlayListPage: BasePage<NewPlayListPage>() {
    val title = allOf(
        withId(R.id.md_title),
        withText("Новый плейлист")
    )
    val input = withId(android.R.id.input)
    val createBtn = withId(R.id.md_buttonDefaultPositive)

    override fun isDisplayed() {
        step("Проверить отображение попапа 'Новый плейлист'") {
            title.isDisplayed()
        }
    }

    fun typePlaylistName(name: String) {
        step("Ввести в поле ввода значение $name") {
            input.replaceText(name)
        }
    }

    fun clickOnCreateBtn() {
        step("Тапнуть по кнопке 'СОЗДАТЬ'") {
            createBtn.click()
        }
    }
}