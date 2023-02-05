package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class ClaimsItemPage(device: UiDevice) : Page(device) {

    private val claimTitleSelector = "title_label_text_view"
    private val statusTextSelector = "status_label_text_view"
    private val editButton = "edit_processing_image_button"

    fun onPage() {
        step("Проверить переход на страницу звявки")
        assertEquals(true, getElementById(claimTitleSelector) is UiObject2)
    }

    fun getStatusText () : String? {
        step("Получить текст статуса заявки")
        return getElementById(statusTextSelector)?.text
    }

    fun clickEdit() : ClaimsEditPage{
        step("Нажать кнопку редактирования заявки")
        getElementById(editButton)?.click()
        return ClaimsEditPage(device)
    }
}