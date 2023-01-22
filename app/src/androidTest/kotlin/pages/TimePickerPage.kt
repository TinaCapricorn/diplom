package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class TimePickerPage(device: UiDevice) : Page(device) {

    private val timePickerSelector = "timePicker"
    private val confirmText = "OK"

    fun onPage() {
        assertEquals(true, getElementById(timePickerSelector) is UiObject2)
    }

    fun confirm() : TimePickerPage {
        getElementByText(confirmText)?.click()
        return this
    }
}