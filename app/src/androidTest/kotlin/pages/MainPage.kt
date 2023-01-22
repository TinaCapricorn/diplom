package pages

import androidx.test.uiautomator.*
import org.junit.Assert.assertEquals

class MainPage(device: UiDevice) : Page(device) {

    private val mainPageSelector = "main_swipe_refresh";
    private val menuButtonSelector = "main_menu_image_button"
    private val menuItemClaimsText = "Claims"
    private val menuItemNewsText = "News"
    private val missionButtonSelector = "our_mission_image_button"
    private val toggleMaterialButton = "expand_material_button"
    private val mainPageNewsContainer = "container_list_news_include_on_fragment_main"
    private val mainPageClaimsContainer = "container_list_claim_include_on_fragment_main"
    private val allNewsButton = "all_news_text_view"
    private val allClaimsButton = "all_claims_text_view"
    private val newsItemArrowButton = "view_news_item_image_view"
    private val claimItemArrowButton = "claim_bottom_divider_image_view"
    private val newsItemTitleSelector = "news_item_title_text_view"
    private val claimItemTitleSelector = "title_material_text_view"
    private val claimItemDetailsSelector = "plan_date_material_text_view"
    private val newsItemTextSelector = "news_item_description_text_view"
    private val addNewClaimButton = "add_new_claim_material_button"


    fun onPage() {
        assertEquals(true, getElementById(mainPageSelector) is UiObject2)
    }

    fun clickMainMenu() : MainPage {
        getElementById(menuButtonSelector)?.click()
        return this
    }

    fun clickClaimsButton() : ClaimsPage {
        getElementByText(menuItemClaimsText)?.click()
        return ClaimsPage(device)
    }

    fun clickNewsButton() : NewsPage {
        getElementByText(menuItemNewsText)?.click()
        return NewsPage(device)
    }

    fun clickMissionButton() : MissionPage {
        getElementById(missionButtonSelector)?.click()
        return MissionPage(device)
    }

    fun toggleNews() : MainPage {
        val news = getElementById(mainPageNewsContainer)
        val button = getElementById(toggleMaterialButton, news)
        button?.click();
        return this
    }

    fun isNewsVisible(isVisible: Boolean) : MainPage {
        assertEquals(isVisible, getElementById(allNewsButton) is UiObject2)
        return this
    }

    fun toggleClaims() : MainPage {
        val claims = getElementById(mainPageClaimsContainer)
        val button = getElementById(toggleMaterialButton, claims)
        button?.click();
        return this
    }

    fun isClaimsVisible(isVisible: Boolean) : MainPage {
        assertEquals(isVisible, getElementById(allClaimsButton) is UiObject2)
        return this
    }

    fun clickAllNews() : NewsPage {
        getElementById(allNewsButton)?.click()
        return NewsPage(device);
    }

    fun clickAllClaims() : ClaimsPage {
        getElementById(allClaimsButton)?.click()
        return ClaimsPage(device);
    }

    fun clickNewsItemArrow() : MainPage {
        getElementById(newsItemArrowButton)?.click()
        return this
    }

    fun clickNewsItemTitle() : MainPage {
        getElementById(newsItemTitleSelector)?.click()
        return this
    }

    fun newsItemTextIsVisible(isVisible: Boolean) : MainPage {
        assertEquals(isVisible, getElementById(newsItemTextSelector) is UiObject2)
        return this
    }

    fun clickClaimItemArrow() : ClaimsItemPage {
        getElementById(claimItemArrowButton)?.click()
        return ClaimsItemPage(device)
    }

    fun clickClaimItemTitle() : ClaimsItemPage {
        getElementById(claimItemTitleSelector)?.click()
        return ClaimsItemPage(device)
    }

    fun clickClaimItemDetails() : ClaimsItemPage {
        getElementById(claimItemDetailsSelector)?.click()
        return ClaimsItemPage(device)
    }

    fun clickAddNewClaim() : ClaimsEditPage {
        getElementById(addNewClaimButton)?.click()
        return ClaimsEditPage(device)
    }
}