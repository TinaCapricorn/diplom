package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class NewsFilterPage(device: UiDevice) : Page(device) {

    private val newsFilterTitleSelector = "filter_news_title_text_view"
    private val categoryFilterSelector = "news_item_category_text_auto_complete_text_view"
    private val dateStartSelector = "news_item_publish_date_start_text_input_edit_text"
    private val dateEndSelector = "news_item_publish_date_end_text_input_edit_text"
    private val filterApplyButton = "filter_button"
    private val filterCancelButton = "cancel_button"
    private val wrongPeriodErrorText = "Wrong period"


    fun onPage() {
        step("Проверить открытие фильтра новостей")
        assertEquals(true, getElementById(newsFilterTitleSelector) is UiObject2)
    }

    fun openFilterCategory() : NewsFilterPage {
        step("Нажать кнопку открытия выбора категории")
        getElementById(categoryFilterSelector)?.click()
        return this
    }

    fun clickFilterCategory(categoryName: String) : NewsFilterPage {
        step("Выбрать из выпадающего списка категорию '".plus(categoryName).plus("'"))
        getElementByText(categoryName)?.click()
        return this
    }

    fun getFilterCategory() : String? {
        step("Получить название выбранной категории")
        return getElementById(categoryFilterSelector)?.text
    }

    fun openDateStartFilter() : DatePickerPage {
        step("Нажать кнопку выбора даты 'ОТ'")
        getElementById(dateStartSelector)?.click()
        return DatePickerPage(device)
    }

    fun openDateEndFilter() : DatePickerPage {
        step("Нажать кнопку выбора даты 'ДО'")
        getElementById(dateEndSelector)?.click()
        return DatePickerPage(device)
    }

    fun getDateStart() : String? {
        step("Получить дату 'ОТ'")
        return getElementById(dateStartSelector)?.text
    }

    fun getDateEnd() : String? {
        step("Получить дату 'ДО'")
        return getElementById(dateEndSelector)?.text
    }

    fun applyFilter() : NewsPage {
        step("Нажать кнопку фильтрации")
        getElementById(filterApplyButton)?.click()
        return NewsPage(device)
    }

    fun cancelFilter() : NewsPage {
        step("Нажать кнопку отмены фильтрации")
        getElementById(filterCancelButton)?.click()
        return NewsPage(device)
    }

    fun hasWrongPeriodError() : Boolean {
        step("Проверить наличие ошибки из-за неправильно выбранного периода")
        return getElementByText(wrongPeriodErrorText) is UiObject2
    }
}