package com.fundacionjala.pivotal.cucumber.stepdefinition.workspace;

import com.fundacionjala.pivotal.cucumber.stepdefinition.login.LoginStepDef;
import com.fundacionjala.pivotal.pages.Workspaces.CreateWorkspace;
import com.fundacionjala.pivotal.pages.Dashboard;
import com.fundacionjala.pivotal.pages.Workspaces.DeleteWorkspace;
import com.fundacionjala.pivotal.pages.Workspaces.SettingWorkspace;
import com.fundacionjala.pivotal.pages.Workspaces.SideBarWorkspace;
import com.fundacionjala.pivotal.pages.Workspaces.Workspace;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static com.fundacionjala.pivotal.framework.util.Constants.DASHBOARD;

/**
 * Created by Daniel Gonzales
 */
public class WorkspaceStepDef {

    private static final String DASHBOARD = "Dashboard";

    private CreateWorkspace createWorkspace;

    private Workspace workspace;

    private Dashboard dashboard;

    private SideBarWorkspace sideBarWorkspace;

    private LoginStepDef loginStepDef;

    private SettingWorkspace settingWorkspace;

    private DeleteWorkspace deleteWorkspace;

    public WorkspaceStepDef(LoginStepDef loginStepDef) {
        this.loginStepDef = loginStepDef;
    }

    @Given("^I am on Pivotal Dashboard page$")
    public void iAmOnPivotalDashboardPage() {
        dashboard = loginStepDef.getDashboard();
    }

    @When("^click on the Create Workspace button of the (Dashboard|Form)$")
    public void iClickOnTheCreateWorkspaceButton(String page) {

        if (DASHBOARD.equalsIgnoreCase(page)) {
            createWorkspace = dashboard.clickCreateWorkspaceLink();
        } else {
            workspace = createWorkspace.clickCreateWorkspaceLink();
            sideBarWorkspace = workspace.getSideWorkspace();
        }
    }

    @Given("^I am on Pivotal Create Workspace form$")
    public void iAmOnPivotalCreateWorkspaceForm() {
        createWorkspace = dashboard.clickCreateWorkspaceLink();
    }

    @When("^I fill with (.*) the name Workspace field$")
    public void iSendAPOSTRequestToMyWorkspacesWithAsNameWorkspace(String nameWorkspace) {
        createWorkspace.setUserNameTestField(nameWorkspace);
    }

    @When("^I click on Add Projects button$")
    public void iClickOnAddProjectsButton() {
        sideBarWorkspace.clickAddProjectLink();
    }

    @And("^I  click on list projects icon$")
    public void iClickOnListProjectsIcon() {
        sideBarWorkspace.clickListProjectLink ();
    }

    @When("^I select the project created previously$")
    public void iSelectTheProjectCreatedPreviously() {
        sideBarWorkspace.clickIdProjectLink ();
    }

    @And("^I click on Save Workspace button$")
    public void iClickOnSaveWorkspaceButton() {
        workspace = sideBarWorkspace.clickSaveWorkspaceLink();
    }

    @Given("^I click on (.*) created$")
    public void iClickOnWorkspace(String nameWorkspace) {
        workspace = loginStepDef.getDashboard().clickNameWorkspaceLink (nameWorkspace);
    }

    @When("^I click on Settings of SideBar$")
    public void iClickOnSettingsOfSideBar() {
        settingWorkspace = workspace.getToolBarWorkspace().clickSettingsWorkspaceLink();
    }

    @And("^I click on Delete link and confirm$")
    public void iClickOnDeleteLink() {
        deleteWorkspace = settingWorkspace.clickDeleteWorkspaceLink();
        dashboard = deleteWorkspace.clickConfirmDeleteLink();
    }

    @When("^I edit the name with: (.*)$")
    public void iEditTheNameWithWorkspace(String newName) {
        settingWorkspace.setNameWorkspaceTestField(newName);
    }

    @And("^I click on Save button$")
    public void iClickOnSaveButton() {
        settingWorkspace.clickSaveChangesWorkspaceLink();
    }

    public CreateWorkspace getCreateWorkspace() {
        return createWorkspace;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public SettingWorkspace getSettingWorkspace() {
        return settingWorkspace;
    }
}
