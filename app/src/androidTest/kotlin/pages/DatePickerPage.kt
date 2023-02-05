package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class DatePickerPage(device: UiDevice) : Page(device) {

    private val datePickerSelector = "container_custom_app_bar_include_on_fragment_create_edit_claim"
    private val confirmText = "OK"

    fun onPage() {
        step("Проверить открытие окна выбора даты")
        assertEquals(true, getElementById(datePickerSelector) is UiObject2)
    }

    fun selectDay(day: String) : DatePickerPage {
        step("Выбрать день '".plus(day).plus("'"))
        getElementByText(day)?.click()
        return this
    }

    fun confirm() : DatePickerPage {
        step("Подтвердить выбор даты")
        getElementByText(confirmText)?.click()
        return this
    }
}