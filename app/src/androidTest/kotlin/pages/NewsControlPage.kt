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
        assertEquals(true, getElementById(newsControlPageSelector) is UiObject2)
    }

    fun getNewsItem(name: String) : UiObject2? {
        return getElementByText(name)?.parent?.parent
    }

    fun clickDeleteNewsItem(name: String) : NewsControlPage {
        val newsItem = this.getNewsItem(name)
        getElementById(newsDeleteButton, newsItem)?.click()
        return this
    }

    fun clickEditNewsItem(name: String) : NewsEditPage {
        val newsItem = this.getNewsItem(name)
        getElementById(newsEditButton, newsItem)?.click()
        return NewsEditPage(device)
    }

    fun clickAddNewsItem() : NewsEditPage {
        getElementById(newsCreateButton)?.click()
        return NewsEditPage(device)
    }

    fun confirmDelete() : NewsControlPage {
        getElementByText(confirmText)?.click()
        return this
    }

}