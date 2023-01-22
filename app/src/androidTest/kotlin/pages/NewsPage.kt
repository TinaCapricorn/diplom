package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class NewsPage(device: UiDevice) : Page(device) {

    private val newsPageSelector = "news_list_swipe_refresh";
    private val newsSortButton = "sort_news_material_button";
    private val newsFilterButton = "filter_news_material_button";
    private val newsEditButton = "edit_news_material_button";
    private val newsItemDateSelector = "news_item_date_text_view";
    private val newsItemTitleSelector = "news_item_title_text_view";

    fun onPage() {
        assertEquals(true, getElementById(newsPageSelector) is UiObject2)
    }

    fun clickNewsSort() : NewsPage {
        getElementById(newsSortButton)?.click()
        return this
    }

    fun clickNewsFilter() : NewsFilterPage {
        getElementById(newsFilterButton)?.click()
        return NewsFilterPage(device)
    }

    fun clickNewsEdit() : NewsControlPage {
        getElementById(newsEditButton)?.click()
        return NewsControlPage(device)
    }

    fun getNewsItemDate() : Date {
        val date = getElementById(newsItemDateSelector)?.text!!
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        return sdf.parse(date)!!
    }

    fun getNewsItemName() : String {
        return getElementById(newsItemTitleSelector)?.text.toString()
    }
}