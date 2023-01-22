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
        assertEquals(true, getElementById(newsFilterTitleSelector) is UiObject2)
    }

    fun openFilterCategory() : NewsFilterPage {
        getElementById(categoryFilterSelector)?.click()
        return this
    }

    fun clickFilterCategory(categoryName: String) : NewsFilterPage {
        getElementByText(categoryName)?.click()
        return this
    }

    fun getFilterCategory() : String? {
        return getElementById(categoryFilterSelector)?.text
    }

    fun openDateStartFilter() : DatePickerPage {
        getElementById(dateStartSelector)?.click()
        return DatePickerPage(device)
    }

    fun openDateEndFilter() : DatePickerPage {
        getElementById(dateEndSelector)?.click()
        return DatePickerPage(device)
    }

    fun getDateStart() : String? {
        return getElementById(dateStartSelector)?.text
    }

    fun getDateEnd() : String? {
        return getElementById(dateEndSelector)?.text
    }

    fun applyFilter() : NewsPage {
        getElementById(filterApplyButton)?.click()
        return NewsPage(device)
    }

    fun cancelFilter() : NewsPage {
        getElementById(filterCancelButton)?.click()
        return NewsPage(device)
    }

    fun hasWrongPeriodError() : Boolean {
        return getElementByText(wrongPeriodErrorText) is UiObject2
    }
}