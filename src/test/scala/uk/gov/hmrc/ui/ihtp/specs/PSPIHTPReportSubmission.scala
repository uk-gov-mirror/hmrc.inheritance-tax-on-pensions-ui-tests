/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.ihtp.specs

import uk.gov.hmrc.ui.ihtp.pages.*

class PSPIHTPReportSubmission extends BaseSpec {

  Feature("PSP IHTP Report Submission") {

    Scenario("1.PSP User Can Submit IHTP Application Individual") {

      Given("the user is logged in as an organisation user")
      AuthLoginPage.loginAsOrgUserWithPspEnrolment()

      When("the user navigates to the What You will need page")
      AuthLoginPage.navigateTo(WhatYouWillNeedPage.pageUrl)

      Then("the What You will need page details should be correct")
      WhatYouWillNeedPage.verifyPageDetails() shouldBe true

      And("the page heading should be displayed")
      WhatYouWillNeedPage.verifyPageHeading() shouldBe true

      And("User Should be able to see and Click Save and Continue Button")
      WhatYouWillNeedPage.SaveAndContinueButton()

      And("User should be able to Navigate to Enter the Inheritance Tax reference number Page ")
      WhatYouWillNeedPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.pageUrl)

      And("User is on the Enter the Inheritance Tax reference number Page")
      EnterTheInheritanceTaxReferenceNumberPage.verifyPageDetails() shouldBe true
      EnterTheInheritanceTaxReferenceNumberPage.verifyPageHeading() shouldBe true

      And("User is able to enter Tax reference number")
      EnterTheInheritanceTaxReferenceNumberPage.enterReferenceNumber("F123456/25A")

      And("User should be able to Navigate to Deceased Name Page ")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)

      And("User is on the Deceased Name Page")
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased")
      DeceasedNamePage.enterDeceasedDetails(
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User should be able to Navigate to National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)

      And("User is on the National Insurance Number Page")
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      NationalInsuranceNumberPage.verifyPageHeading() shouldBe true

      And("User selects No for Does User has National Number")
      NationalInsuranceNumberPage.clickRadioButton("No")

      Then("User will navigates to Enter reason for no NI number Page")
      NationalInsuranceNumberPage.navigateTo(NoNationalInsuranceNumberReasonPage.pageUrl)
      NoNationalInsuranceNumberReasonPage.verifyPageDetails() shouldBe true
      NoNationalInsuranceNumberReasonPage.verifyPageHeading() shouldBe true

      Then("User Enters Reason for no National Insurance Number and continues to next Page")
      NoNationalInsuranceNumberReasonPage.enterReason("the deceased was not a UK citizen")

      Then("User is navigated to the Enter the birth and death dates of the user Page")
      NoNationalInsuranceNumberReasonPage.navigateTo(EnterBirthDeathPage.pageUrl)
      EnterBirthDeathPage.verifyPageDetails() shouldBe true

      And("User should be able to enter Date of Birth and Death Date")
      EnterBirthDeathPage.enterBirthDate("01", "01", "1990")
      EnterBirthDeathPage.enterDeathDate("11", "12", "2025")

      Then(
        "User is navigated to the Is the personal representative (PR) an individual or a member of an organisation? Page"
      )
      PRTypePage.verifyPageDetails() shouldBe true

      And("User selects Individual for PR Type and then continues to next Page")
      PRTypePage.clickRadioButton("Individual")

      Then("User is navigated to the PR Name Page")
      PRTypePage.navigateTo(PRNamePage.pageUrl)
      PRNamePage.verifyPageDetails() shouldBe true
      PRNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the PR and continues to next Page")
      PRNamePage.enterPRDetails(
        "Mr",
        "PRFirstName",
        "PRMiddleName",
        "PRSurnameName"
      )

      Then("User is navigated to the Select Country Page")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("United Kingdom")

      Then("User is navigated to Look Up Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterPostcode("ZZ01 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("1")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User is navigated to Did PRFirstName PRSurnameName submit the payment notice? Page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true

      And("User Clicks on Yes Radio button and click on continue button")
      SubmitPaymentNoticePage.clickRadioButton("Yes")

      Then("User is navigated to When did the scheme receive the payment notice? Page")
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true

      And("User should be able to enter date of receiving payment notice and continues to the next page")
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

      Then("User is navigated to the Are the beneficiaries known Page")
      AreBeneficiariesKnownPage.navigateTo(AreBeneficiariesKnownPage.pageUrl)
      AreBeneficiariesKnownPage.verifyPageDetails() shouldBe true

      And("User Clicks on Yes Radio button and continues to the next Page")
      AreBeneficiariesKnownPage.clickRadioButton("Yes")

      Then("User is navigated to the Select the type of beneficiary to add Page")
      SelectTypeOfBeneficiaryToAdd.navigateTo(SelectTypeOfBeneficiaryToAdd.pageUrl)
      SelectTypeOfBeneficiaryToAdd.verifyPageDetails() shouldBe true

      And("User Clicks on An individual option and continues to the next Page")
      SelectTypeOfBeneficiaryToAdd.clickRadioButton("An individual")

      Then("User is navigated to the Enter the full name of the beneficiary Page")
      EnterNameOfBeneficiaryPage.navigateTo(EnterNameOfBeneficiaryPage.pageUrl)
      EnterNameOfBeneficiaryPage.verifyPageDetails() shouldBe true

      And("User is able to enter Details of the Beneficiary and continues to next Page")
      EnterNameOfBeneficiaryPage.enterBeneficiaryDetails(
        "Dr",
        "BeneficiaryFirstName",
        "BeneficiaryMiddleName",
        "BeneficiaryLastName"
      )

      Then("User will be on Does Beneficiary have a National Insurance Number")
      BeneficiaryNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      BeneficiaryNationalInsuranceNumberPage.clickRadioButton("Yes")

      And("User will be on CYA page")
      CheckYourAnswersPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails() shouldBe true
      CheckYourAnswersPage.verifyPageHeading() shouldBe true

      Then("User should be able to click on Change Link Button")
      CheckYourAnswersPage.ClickChangeLink()

      And("When User Clicks on Change Link Button it will navigates to enter the Inheritance Tax reference number Page")
      CheckYourAnswersPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.newUrl)
      EnterTheInheritanceTaxReferenceNumberPage.verifyNewUrl() shouldBe true

      When("user clicks on Save and continue it navigates to the Check and submit the report page")
      EnterTheInheritanceTaxReferenceNumberPage.SaveAndContinueButton()
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails()

      Then("User clicks on Save and Continue button on the Check and submit the report page ")
      CheckYourAnswersPage.SaveAndContinueButton()

      And("User should be able to Navigates to Psp-Declaration Page")
      CheckYourAnswersPage.navigateTo(PSPDeclarationPage.pageUrl)
      PSPDeclarationPage.verifyPageDetails() shouldBe true
      PSPDeclarationPage.verifyPageHeading() shouldBe true

      And("User should be enter Administrator's id on Psp-Declaration Page")
      PSPDeclarationPage.enterAdministratorId("A2100005")

      And("click on Agree and Submit Button on Psp-Declaration Page")
      PSPDeclarationPage.AgreeAndSubmitButton()

      And("User should be able to Navigates to Submission Page")
      PSPDeclarationPage.navigateTo(ReportSubmittedPage.pageUrl)
      ReportSubmittedPage.verifyPageDetails() shouldBe true
      ReportSubmittedPage.verifyPageHeading() shouldBe true

      And("the GOV.UK footer links should be present")
      ReportSubmittedPage.verifyFooterLinksArePresent() shouldBe true

      And("the Sign out link should be displayed")
      AuthLoginPage.verifySignOutLinkText() shouldBe true
    }

    Scenario("2. PSP User Can Submit IHTP Application Organisation") {

      Given("the user is logged in as an organisation user")
      AuthLoginPage.loginAsOrgUserWithPspEnrolment()

      When("the user navigates to the What You will need page")
      AuthLoginPage.navigateTo(WhatYouWillNeedPage.pageUrl)

      Then("the What You will need page details should be correct")
      WhatYouWillNeedPage.verifyPageDetails() shouldBe true

      And("the page heading should be displayed")
      WhatYouWillNeedPage.verifyPageHeading() shouldBe true

      And("User Should be able to see and Click Save and Continue Button")
      WhatYouWillNeedPage.SaveAndContinueButton()

      And("User should be able to Navigate to Enter the Inheritance Tax reference number Page ")
      WhatYouWillNeedPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.pageUrl)

      And("User is on the Enter the Inheritance Tax reference number Page")
      EnterTheInheritanceTaxReferenceNumberPage.verifyPageDetails() shouldBe true
      EnterTheInheritanceTaxReferenceNumberPage.verifyPageHeading() shouldBe true

      And("User is able to enter Tax reference number")
      EnterTheInheritanceTaxReferenceNumberPage.enterReferenceNumber("F123456/25A")

      And("User should be able to Navigate to Deceased Name Page ")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)

      And("User is on the Deceased Name Page")
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased")
      DeceasedNamePage.enterDeceasedDetails(
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User should be able to Navigate to National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does User has National Number")
      NationalInsuranceNumberPage.clickRadioButton("No")

      Then("User will navigates to Enter reason for no NI number Page")
      NationalInsuranceNumberPage.navigateTo(NoNationalInsuranceNumberReasonPage.pageUrl)
      NoNationalInsuranceNumberReasonPage.verifyPageDetails() shouldBe true
      NoNationalInsuranceNumberReasonPage.verifyPageHeading() shouldBe true

      Then("User Enters Reason for no National Insurance Number and continues to next Page")
      NoNationalInsuranceNumberReasonPage.enterReason("the deceased was not a UK citizen")

      Then("User is navigated to the Enter the birth and death dates of the user Page")
      NoNationalInsuranceNumberReasonPage.navigateTo(EnterBirthDeathPage.pageUrl)
      EnterBirthDeathPage.verifyPageDetails() shouldBe true

      And("User should be able to enter Date of Birth and Death Date")
      EnterBirthDeathPage.enterBirthDate("01", "01", "1990")
      EnterBirthDeathPage.enterDeathDate("11", "12", "2025")

      When("User click On save and Continue navigates to the PR Type page")
      EnterBirthDeathPage.navigateTo(PRTypePage.pageUrl)
      PRTypePage.verifyPageDetails() shouldBe true

      And("User selects Individual for PR Type")
      PRTypePage.clickRadioButton("Organisation")

      And("User should be able to Navigate to Organisation Page")
      PRTypePage.navigateTo(NameOfTheOrganisationPage.pageUrl)

      And("User is on the PR Name Page")
      NameOfTheOrganisationPage.verifyPageDetails() shouldBe true
      NameOfTheOrganisationPage.verifyPageHeading() shouldBe true

      And("User is able to enter Organisation name and continues to next Page")
      NameOfTheOrganisationPage.enterOrganisationName("Testdata Company Ltd.")

      Then("User is navigated to the Enter Name of the PR Organisation name Page")
      OrganisationRepresentativeNamePage.verifyPageDetails() shouldBe true

      And("User should able to enter PR details and continues to next Page")
      OrganisationRepresentativeNamePage.enterOrgRepresentativeDetails(
        "Mr",
        "PRFirstName",
        "PRMiddleName",
        "PRSurnameName"
      )

      Then("User is navigated to Select the country or territory of Testdata Company Ltd.")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("United Kingdom")

      Then("User is navigated to Look Up Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterPostcode("ZZ01 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("1")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User is navigated to Did individual submit the payment notice? Page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true

      And("User Clicks on Yes Radio button and click on continue button")
      SubmitPaymentNoticePage.clickRadioButton("Yes")

      Then("User is navigated to When did the scheme receive the payment notice? Page")
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true

      And("User should be able to enter date of receiving payment notice")
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

      Then("User is navigated to the Are the beneficiaries known Page")
      AreBeneficiariesKnownPage.navigateTo(AreBeneficiariesKnownPage.pageUrl)
      AreBeneficiariesKnownPage.verifyPageDetails() shouldBe true

      And("User Clicks on Yes Radio button and clicks on Save and continue button")
      AreBeneficiariesKnownPage.clickRadioButton("Yes")

      Then("User is navigated to Select the type of beneficiary to add Page")
      SelectTypeOfBeneficiaryToAdd.navigateTo(SelectTypeOfBeneficiaryToAdd.pageUrl)
      SelectTypeOfBeneficiaryToAdd.verifyPageDetails() shouldBe true

      And("User Clicks on A trust option and continues to the next page")
      SelectTypeOfBeneficiaryToAdd.clickRadioButton("A trust")

      Then("User is navigated to Organisation detail page and Enter the name of the trust on the Page")
      BeneficiaryOrganisationDetailsPage.verifyPageDetails() shouldBe true
      BeneficiaryOrganisationDetailsPage.enterTrustName("Test Organisation & Co ltd.")

      And("User will be on Add Beneficiary page and selects No for Do you need to add another beneficiary")
      BeneficiaryNationalInsuranceNumberPage.navigateTo(AddBeneficiaryPage.pageUrl)
      AddBeneficiaryPage.verifyPageDetails() shouldBe true
      AddBeneficiaryPage.clickRadioButton("No")

      Then("User will be on CYA page")
      AddBeneficiaryPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails() shouldBe true
      CheckYourAnswersPage.verifyPageHeading() shouldBe true

      Then("User should be able to click on Change Link Button")
      CheckYourAnswersPage.ClickChangeLink()

      And("When User Clicks on Change Link Button it will navigates to enter the Inheritance Tax reference number Page")
      CheckYourAnswersPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.newUrl)
      EnterTheInheritanceTaxReferenceNumberPage.verifyNewUrl() shouldBe true

      Then("user click On save and Continue it navigates to the Check and submit the report page")
      EnterTheInheritanceTaxReferenceNumberPage.SaveAndContinueButton()
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails()

      And("User click on Save and Continue button on the Check and submit the report page ")
      CheckYourAnswersPage.SaveAndContinueButton()

      Then("User should be able to Navigates to Psp-Declaration Page")
      CheckYourAnswersPage.navigateTo(PSPDeclarationPage.pageUrl)
      PSPDeclarationPage.verifyPageDetails() shouldBe true
      PSPDeclarationPage.verifyPageHeading() shouldBe true

      And("User should be enter Administrator's id on Psp-Declaration Page")
      PSPDeclarationPage.enterAdministratorId("A2100005")

      When("click on Agree and Submit Button on Psp-Declaration Page")
      PSPDeclarationPage.AgreeAndSubmitButton()

      And("User should be able to Navigates to Submission Page")
      PSPDeclarationPage.navigateTo(ReportSubmittedPage.pageUrl)
      ReportSubmittedPage.verifyPageDetails() shouldBe true
      ReportSubmittedPage.verifyPageHeading() shouldBe true

      And("the GOV.UK footer links should be present")
      ReportSubmittedPage.verifyFooterLinksArePresent() shouldBe true

      And("the Sign out link should be displayed")
      AuthLoginPage.verifySignOutLinkText() shouldBe true
    }

  }

}
