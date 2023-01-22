package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class ClaimsItemPage(device: UiDevice) : Page(device) {

    private val claimTitleSelector = "title_label_text_view"
    private val statusTextSelector = "status_label_text_view"
    private val editButton = "edit_processing_image_button"

    fun onPage() {
        assertEquals(true, getElementById(claimTitleSelector) is UiObject2)
    }

    fun getStatusText () : String? {
        return getElementById(statusTextSelector)?.text
    }

    fun clickEdit() : ClaimsEditPage{
        getElementById(editButton)?.click()
        return ClaimsEditPage(device)
    }
}