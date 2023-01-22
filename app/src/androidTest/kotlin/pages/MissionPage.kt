package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class MissionPage(device: UiDevice) : Page(device) {

    private val missionPageTitleText = "Love is all";

    fun onPage() {
        assertEquals(true, getElementByText(missionPageTitleText) is UiObject2)
    }
}