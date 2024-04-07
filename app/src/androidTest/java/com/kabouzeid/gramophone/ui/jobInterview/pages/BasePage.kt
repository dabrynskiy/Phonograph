package com.kabouzeid.gramophone.ui.jobInterview.pages

import com.atiurin.ultron.page.Page

abstract class BasePage<out T : BasePage<T>> : Page<@UnsafeVariance T>() {
    abstract fun isDisplayed()
}