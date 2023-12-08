package com.globalsqa.step;

import com.globalsqa.common.element.PageElement;
import com.globalsqa.page.AddPlayerPage;
import com.globalsqa.page.BaseDataTable;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class AddPlayerStep extends AbstractStepDefinition {
    @Autowired
    AddPlayerPage addPlayerPage;

/////////////////////// Action methods //////////////////////////////

    /**
     * Clicks Sign In button on the Login page
     */
    @When("^I (?:navigate to|open) 'Add' page$")
    public void navigateToAddPage() {
        addPlayerPage.getAddLink().click();
    }

    /**
     * Navigates to 'My Players' page
     */
    @When("^I (?:navigate to|open) 'My Players' page$")
    public void navigateToMyPlayersPage() {
        addPlayerPage.getMyPlayersLink().click();
    }

    /**
     * Clicks 'Add Player' link
     */
    @When("^I initiate adding new Player$")
    public void initiateAddingNewPlayer() {
        addPlayerPage.getAddPlayerButton().click();
    }

    /**
     * Enters new Player information according to data table
     * <p>
     * <b>Date Table Headers:</b> <ul> <li>Tag <li>ID <li>Account Name </ul>
     */
    @When("^I add new Player with the following values:$")
    public void addNewPlayerStep(DataTable table)
    {
        navigateToMyPlayersPage();
        initiateAddingNewPlayer();
        enterNewPlayerInformation(table);
        saveNewPlayer();
    }

    /**
     * Enters new Player information according to data table
     * <p>
     * <b>Date Table Headers:</b> <ul> <li>Tag <li>ID <li>Account Name </ul>
     */
    @When("^I enter new Player information with the following values:$")
    public void enterNewPlayerInformation(DataTable table) {
        BaseDataTable t = new BaseDataTable(table);
        String playerTag = t.getValue("Tag");
        String playerID = t.getValue("ID");
        String playerAccountName = t.getValue("Account Name");

        addPlayerPage.getTagTextField().enterText(playerTag);
        addPlayerPage.getIDTextField().enterText(playerID);
        addPlayerPage.getAccountNameTextField().enterText(playerAccountName);

        globals.put("playerTag", playerTag);
        globals.put("playerID", playerID);
        globals.put("playerAccountName", playerAccountName);
    }

    /**
     * Clicks 'Save'/Confirm button on the Add Player popup
     */
    @When("^I save new Player$")
    public void saveNewPlayer() {
        addPlayerPage.getSaveButton().click();
        if (addPlayerPage.getConfirmButton().isVisible()) {
            addPlayerPage.getConfirmButton().click();
        }
    }

    /////////////////////// Verification methods //////////////////////////////

    /**
     * Verifies 'My Players' table according to data table
     * <p>
     * <b>Date Table Headers:</b> <ul>Tag <li>Account <li>ID </ul>
     *
     * @param table cucumber data table
     */
    @Then("^I should see 'My Players' table with the following values:$")
    public void verifyMyPlayersTable(DataTable table) {
        PageElement myPlayersTable = addPlayerPage.getMyPlayersTable();
        String myPlayersTableText = myPlayersTable.getText();

        BaseDataTable t = new BaseDataTable(table);
        String expectedPlayerTag = t.getValue("Tag");
        String expectedPlayerID = t.getValue("ID");
        String expectedPlayerAccountName = t.getValue("Account Name");

        softAssert.assertTrue("There is no Player with Tag:" + expectedPlayerTag, myPlayersTableText.contains(expectedPlayerTag));
        softAssert.assertTrue("There is no Player with ID:" + expectedPlayerID, myPlayersTableText.contains(expectedPlayerID));
        softAssert.assertTrue("There is no Player with Account Name:" + expectedPlayerAccountName, myPlayersTableText.contains(expectedPlayerAccountName));
        softAssert.process();
    }
}
