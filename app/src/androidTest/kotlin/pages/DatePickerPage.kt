package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class DatePickerPage(device: UiDevice) : Page(device) {

    private val datePickerSelector = "container_custom_app_bar_include_on_fragment_create_edit_claim"
    private val confirmText = "OK"

    fun onPage() {
        assertEquals(true, getElementById(datePickerSelector) is UiObject2)
    }

    fun selectDay(day: String) : DatePickerPage {
        getElementByText(day)?.click()
        return this
    }

    fun confirm() : DatePickerPage {
        getElementByText(confirmText)?.click()
        return this
    }
}