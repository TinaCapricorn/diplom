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
        step("Проверить переход на страницу редактирования новости")
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
        step("Нажать на инпут категории")
        getCategoryInput()?.click()
        step("Нажать на категорию с названием '".plus(category).plus("'"))
        getElementByText(category)?.click()
        return this
    }

    fun getCategory() : String {
        step("Получить название категории")
        return getCategoryInput()?.text.toString()
    }

    fun setTitle(title: String) : NewsEditPage {
        step("Ввести в поле названия новости текст '".plus(title).plus("'"))
        getTitleInput()?.text = title
        return this
    }

    fun setDate() : NewsEditPage {
        step("Нажать на инпут даты новости")
        getElementById(dateSelector)?.click()
        DatePickerPage(device).confirm()
        return this
    }

    fun setTime() : NewsEditPage {
        step("Нажать на инпут времени новости")
        getElementById(timeSelector)?.click()
        TimePickerPage(device).confirm()
        return this
    }

    fun setDescription(description: String) : NewsEditPage {
        step("Ввести в поле описания новости текст '".plus(description).plus("'"))
        getDescriptionInput()?.text = description
        return this
    }

    fun saveNews() : NewsControlPage {
        step("Нажать на кнопку сохранения новости")
        getElementById(saveButton)?.click()
        return NewsControlPage(device)
    }

    fun cancel() : NewsEditPage {
        step("Нажать на кнопку отмены сохранения новости")
        getElementById(cancelButton)?.click()
        return this
    }

    fun confirmCancel() : NewsControlPage {
        step("Нажать на кнопку ОК, для отмены сохранения новости")
        getElementByText(confirmText)?.click()
        return NewsControlPage(device)
    }

}