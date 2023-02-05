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
        step("Проверить переход на страницу списка новостей")
        assertEquals(true, getElementById(newsPageSelector) is UiObject2)
    }

    fun clickNewsSort() : NewsPage {
        step("Нажать на кнопку сортировки новостей")
        getElementById(newsSortButton)?.click()
        return this
    }

    fun clickNewsFilter() : NewsFilterPage {
        step("Нажать на кнопку открытия фильтра новостей")
        getElementById(newsFilterButton)?.click()
        return NewsFilterPage(device)
    }

    fun clickNewsEdit() : NewsControlPage {
        step("Нажать на кнопку редактирования новости")
        getElementById(newsEditButton)?.click()
        return NewsControlPage(device)
    }

    fun getNewsItemDate() : Date {
        step("Нажать на кнопку удаления новости")
        val date = getElementById(newsItemDateSelector)?.text!!
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        return sdf.parse(date)!!
    }

    fun getNewsItemName() : String {
        step("Получить название первой новости в списке")
        return getElementById(newsItemTitleSelector)?.text.toString()
    }
}