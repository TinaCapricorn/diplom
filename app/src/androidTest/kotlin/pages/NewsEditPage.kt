package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class NewsEditPage(device: UiDevice) : Page(device) {

    private val newsEditPageSelector = "container_custom_app_bar_include_on_fragment_create_edit_news"
    private val categorySelector = "news_item_category_text_auto_complete_text_view"
    private val titleSelector = "news_item_title_text_input_edit_text"
    private val dateSelector = "news_item_publish_date_text_input_edit_text"
    private val timeSelector = "news_item_publish_time_text_input_edit_text"
    private val descriptionSelector = "news_item_description_text_input_edit_text"
    private val saveButton = "save_button"
    private val cancelButton = "cancel_button"
    private val confirmText = "OK"

    fun onPage() {
        assertEquals(true, getElementById(newsEditPageSelector) is UiObject2)
    }

    private fun getCategoryInput() : UiObject2? {
        return getElementById(categorySelector)
    }

    private fun getTitleInput() : UiObject2? {
        return getElementById(titleSelector)
    }

    private fun getDescriptionInput() : UiObject2? {
        return getElementById(descriptionSelector)
    }

    fun setCategory(category: String) : NewsEditPage {
        getCategoryInput()?.click()
        getElementByText(category)?.click()
        return this
    }

    fun getCategory() : String {
        return getCategoryInput()?.text.toString()

    }

    fun setTitle(title: String) : NewsEditPage {
        getTitleInput()?.text = title
        return this
    }

    fun setDate() : NewsEditPage {
        getElementById(dateSelector)?.click()
        DatePickerPage(device).confirm()
        return this
    }

    fun setTime() : NewsEditPage {
        getElementById(timeSelector)?.click()
        TimePickerPage(device).confirm()
        return this
    }

    fun setDescription(description: String) : NewsEditPage {
        getDescriptionInput()?.text = description
        return this
    }

    fun saveNews() : NewsControlPage {
        getElementById(saveButton)?.click()
        return NewsControlPage(device)
    }

    fun cancel() : NewsEditPage {
        getElementById(cancelButton)?.click()
        return this
    }

    fun confirmCancel() : NewsControlPage {
        getElementByText(confirmText)?.click()
        return NewsControlPage(device)
    }

}