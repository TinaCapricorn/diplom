package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class NewsControlPage(device: UiDevice) : Page(device) {

    private val newsControlPageSelector = "container_custom_app_bar_include_on_fragment_news_control_panel"
    private val newsDeleteButton = "delete_news_item_image_view"
    private val newsEditButton = "edit_news_item_image_view"
    private val newsCreateButton = "add_news_image_view"
    private val confirmText = "OK"


    fun onPage() {
        step("Проверить переход на страницу управления новостями")
        assertEquals(true, getElementById(newsControlPageSelector) is UiObject2)
    }

    fun getNewsItem(name: String) : UiObject2? {
        step("Получить элемент новости с названием '".plus(name).plus("'"))
        return getElementByText(name)?.parent?.parent
    }

    fun clickDeleteNewsItem(name: String) : NewsControlPage {
        step("Нажать кнопку удаления новости с названием '".plus(name).plus("'"))
        val newsItem = this.getNewsItem(name)
        getElementById(newsDeleteButton, newsItem)?.click()
        return this
    }

    fun clickEditNewsItem(name: String) : NewsEditPage {
        step("Нажать кнопку редактирования новости с названием '".plus(name).plus("'"))
        val newsItem = this.getNewsItem(name)
        getElementById(newsEditButton, newsItem)?.click()
        return NewsEditPage(device)
    }

    fun clickAddNewsItem() : NewsEditPage {
        step("Нажать кнопку добавления новой новости")
        getElementById(newsCreateButton)?.click()
        return NewsEditPage(device)
    }

    fun confirmDelete() : NewsControlPage {
        step("Нажать кнопку ОК, для подтверждения удаления")
        getElementByText(confirmText)?.click()
        return this
    }

}