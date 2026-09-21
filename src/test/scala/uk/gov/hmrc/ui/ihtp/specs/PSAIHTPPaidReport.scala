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

class PSAIHTPPaidReport extends BaseSpec {

  Feature("Paid reports page for a PSA") {

    Scenario("1. PSA User Can go to the View Paid Reports page from the Overview Page") {

      Given("the user is logged in as an organisation user")
      AuthLoginPage.loginAsPsaOrgUserForSubmission()

      And("User is navigated to the Report Inheritance Tax on a pension (Overview) Page")
      AuthLoginPage.navigateTo(OverviewPage.pageUrl)
      OverviewPage.verifyPageDetails() shouldBe true
      OverviewPage.verifyPageHeading() shouldBe true

      Then("User clicks on View paid reports link and navigates to the View Paid Reports Page")
      OverviewPage.clickViewPaidReportsLink()
      OverviewPage.navigateTo(PaidReportsPage.pageUrl)
      PaidReportsPage.verifyPageDetails() shouldBe true
      PaidReportsPage.verifyPageHeading() shouldBe true

      Then(
        "User clicks on the Return to active reports link and navigates back to the Report Inheritance Tax on a pension (Overview) Page"
      )
      PaidReportsPage.clickReturnToActiveReportsLink()
      OverviewPage.verifyPageDetails() shouldBe true
      OverviewPage.verifyPageHeading() shouldBe true

    }
    Scenario(
      "2. PSA User successfully resumes a report after leaving it at Review And Confirm Page"
    ) {

      Given("the user is logged in as an organisation user")
      AuthLoginPage.loginAsOrgUserWithPsaEnrolment()

      When("the user navigates to the What You will need page")
      AuthLoginPage.navigateTo(WhatYouWillNeedPage.pageUrl)

      And("the What You will need page details are correct")
      WhatYouWillNeedPage.verifyPageDetails() shouldBe true
      WhatYouWillNeedPage.verifyPageHeading() shouldBe true
      WhatYouWillNeedPage.clickSaveAndContinueButton()

      Then("the user is navigated to the Enter the Inheritance Tax reference number Page")
      WhatYouWillNeedPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.pageUrl)
      EnterTheInheritanceTaxReferenceNumberPage.verifyPageDetails() shouldBe true
      EnterTheInheritanceTaxReferenceNumberPage.verifyPageHeading() shouldBe true

      And("User enters the Tax reference number and continues to next Page")
      EnterTheInheritanceTaxReferenceNumberPage.enterReferenceNumber("A123456/25A")

      // Deceased name page
      Then("User is navigated to the Deceased Name Page")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased and continues to next Page")
      DeceasedNamePage.enterDeceasedDetails(
        "Dr",
        "UniqueDeceasedFirstName",
        "UniqueDeceasedMiddleName",
        "UniqueDeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does DeceasedFirstName DeceasedSurnameName have a National Insurance number?")
      NationalInsuranceNumberPage.clickRadioButton("No")

      Then("User will navigates to Enter reason for no NI number Page")
      NationalInsuranceNumberPage.navigateTo(NoNationalInsuranceNumberReasonPage.pageUrl)

      Then("User Enters Reason for no National Insurance Number and continues to next Page")
      NoNationalInsuranceNumberReasonPage.enterReason("the deceased was not a UK citizen")

      Then("User is navigated to the Enter the birth and death dates of the user Page")
      NoNationalInsuranceNumberReasonPage.navigateTo(EnterBirthDeathPage.pageUrl)
      EnterBirthDeathPage.verifyPageDetails() shouldBe true

      And("User enters Date of Birth and Date of Death then continues to the next page")
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

      Then("User is navigated to Review and confirm Page where the information is saved and continues to next page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User user navigates away to the Overview Page and then back to the saved report")
      ReviewAndConfirmPage.navigateTo(OverviewPage.pageUrl)
      OverviewPage.verifyPageDetails() shouldBe true
      OverviewPage.verifyPageHeading() shouldBe true
      OverviewPage.clickLinkByIdAndText()

      Then("User is navigated to the Continue Check report details Page")
      ContinueAddingDetailsPage.verifyPageDetails() shouldBe true
      ContinueAddingDetailsPage.verifyPageHeading() shouldBe true

      When("User should see the saved PR Country and Address and continues the next page")
      ContinueAddingDetailsPage.verifyAddressOfPr("1 Test Street\nTesttown\nZZ01 1ZZ")
      ContinueAddingDetailsPage.verifyCountryOfUnitedKingdom("United Kingdom")
      ContinueAddingDetailsPage.clickSaveAndContinueButton()

      Then("User is navigated to Did PRFirstName PRSurnameName submit the payment notice? Page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true
      SubmitPaymentNoticePage.clickRadioButton("Yes")

      Then(
        "User is navigated to When did the scheme receive the payment notice? Page and enter date of receiving Payment notice and continues to the next page"
      )
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

      Then(
        "User is navigated to the Are the beneficiaries known Page and clicks on Yes Radio button and continues to next page"
      )
      AreBeneficiariesKnownPage.navigateTo(AreBeneficiariesKnownPage.pageUrl)
      AreBeneficiariesKnownPage.verifyPageDetails() shouldBe true
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

      Then("User will be on Does Beneficiary have a National Insurance Number Page")
      BeneficiaryNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      BeneficiaryNationalInsuranceNumberPage.clickRadioButton("Yes")

      And("User will be on Add Beneficiary page and selects No for Do you need to add another beneficiary")
      BeneficiaryNationalInsuranceNumberPage.navigateTo(AddBeneficiaryPage.pageUrl)
      AddBeneficiaryPage.verifyPageDetails() shouldBe true
      AddBeneficiaryPage.clickRadioButton("No")

      And("User will be on CYA page")
      AddBeneficiaryPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails() shouldBe true
      CheckYourAnswersPage.verifyPageHeading() shouldBe true

      Then("User clicks on Save and Continue button on the Check and submit the report page ")
      CheckYourAnswersPage.SaveAndContinueButton()

      And("User should be able to Navigates to Psa-Declaration Page")
      CheckYourAnswersPage.navigateTo(PSADeclarationPage.pageUrl)
      PSADeclarationPage.verifyPageDetails() shouldBe true
      PSADeclarationPage.verifyPageHeading() shouldBe true

      And("User should be click on Agree and Submit Button on Psa-Declaration Page")
      PSADeclarationPage.AgreeAndSubmitButton()

      And("User should be able to Navigates to Submission Page")
      PSADeclarationPage.navigateTo(ReportSubmittedPage.pageUrl)
      ReportSubmittedPage.verifyPageDetails() shouldBe true
      ReportSubmittedPage.verifyPageHeading() shouldBe true

      And("the GOV.UK footer links should be present")
      ReportSubmittedPage.verifyFooterLinksArePresent() shouldBe true

      And("the Sign out link should be displayed")
      AuthLoginPage.verifySignOutLinkText() shouldBe true
    }
  }
}
