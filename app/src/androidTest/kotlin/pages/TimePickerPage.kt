package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class TimePickerPage(device: UiDevice) : Page(device) {

    private val timePickerSelector = "timePicker"
    private val confirmText = "OK"

    fun onPage() {
        step("Проверить открытие окна выбора времени")
        assertEquals(true, getElementById(timePickerSelector) is UiObject2)
    }

    fun confirm() : TimePickerPage {
        step("Подтвердить выбор времени")
        getElementByText(confirmText)?.click()
        return this
    }
}