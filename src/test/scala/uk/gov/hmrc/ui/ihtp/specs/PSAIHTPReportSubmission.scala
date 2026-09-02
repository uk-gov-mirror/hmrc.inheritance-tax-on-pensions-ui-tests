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

class PSAIHTPReportSubmission extends BaseSpec {

  Feature("PSA IHTP Report Submission") {

    Scenario(
      "1. PSA User Can Submit IHTP Application, Individual and No NI, Yes for Payment notice submission, Yes for beneficiaries known, An individual for beneficiaries known"
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
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does DeceasedFirstName DeceasedSurnameName have a National Insurance number?")
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
      LookUpPostcodePage.enterPostcode("ZZ1 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("4")

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

      Then("User should be able to click on Change Link Button")
      CheckYourAnswersPage.ClickChangeLink()

      And("When User Clicks on Change Link Button it will navigates to enter the Inheritance Tax reference number Page")
      CheckYourAnswersPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.newUrl)
      EnterTheInheritanceTaxReferenceNumberPage.verifyNewUrl() shouldBe true

      When("User clicks on Save and continue it navigates to the Check and submit the report Page")
      EnterTheInheritanceTaxReferenceNumberPage.SaveAndContinueButton()
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails()

      // Verify if fields on the Check and Submit Page

      Then("User verifies if the details for Country of PR and Address of PR are correct")
      CheckYourAnswersPage.verifyCountryOfUnitedKingdom("United Kingdom")
      CheckYourAnswersPage.verifyAddressOfPr("4 Other Place\nSome District\nAnytown\nZZ1 1ZZ")

      And("User should be able to click on Change Country of PR")
      CheckYourAnswersPage.clickChangeCountryOfPrLink()

      Then("User is navigated to the Select Country Page")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("Spain")

      Then("User is navigated to Enter Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterAddressLine1("Spanish Address 1")
      LookUpPostcodePage.enterAddressLine2("Spanish Address 2")
      LookUpPostcodePage.enterAddressLine3("Spanish Address 3")
      LookUpPostcodePage.enterPostcode("TE5710")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User verifies if the changed details for Country of PR and Address of PR are correct")
      CheckYourAnswersPage.verifyCountryOfSpain("Spain")
      CheckYourAnswersPage.verifyAddressOfPr("Spanish Address 1\nSpanish Address 2\nSpanish Address 3\nTE5710")

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

    Scenario(
      "2. PSA User Can Submit IHTP Application, Individual and No NI, Yes for Payment Notice submission, No for beneficiaries known"
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

      And("User is able to enter Tax reference number")
      EnterTheInheritanceTaxReferenceNumberPage.enterReferenceNumber("A123456/25A")

      // Deceased name page
      Then("User is navigated to the Deceased Name Page")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased and continues to next Page")
      DeceasedNamePage.enterDeceasedDetails(
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      NationalInsuranceNumberPage.verifyPageHeading() shouldBe true

      And("User selects No for Does User has National Number")
      NationalInsuranceNumberPage.clickRadioButton("No")

      Then("User will navigates to Enter reason for no NI number Page")
      NationalInsuranceNumberPage.navigateTo(EnterNationalInsuranceNumberPage.pageUrl)
      EnterNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      EnterNationalInsuranceNumberPage.verifyPageHeading() shouldBe true

      Then("User enters reason for no National Insurance Number and continues to next Page")
      NoNationalInsuranceNumberReasonPage.enterReason("the deceased was not a UK citizen")

      Then("User is navigated to the Enter the birth and death dates of the user Page")
      EnterNationalInsuranceNumberPage.navigateTo(EnterBirthDeathPage.pageUrl)
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
      LookUpPostcodePage.enterPostcode("ZZ1 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("4")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User is navigated to Did PRFirstName PRSurnameName submit the payment notice? page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true

      And("User Clicks on Yes Radio button and click on continue button")
      SubmitPaymentNoticePage.clickRadioButton("Yes")

      Then("User is navigated to When did the scheme receive the payment notice? Page")
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true

      And("User should be able to enter date of receiving payment notice and continue to the next page")
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

      Then("User is navigated to the Are the beneficiaries known page")
      AreBeneficiariesKnownPage.navigateTo(AreBeneficiariesKnownPage.pageUrl)
      AreBeneficiariesKnownPage.verifyPageDetails() shouldBe true

      And("User Clicks on No Radio button and clicks on Save and continue button")
      AreBeneficiariesKnownPage.clickRadioButton("No")

      Then("User will be on CYA page")
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

      Then("User clicks on Save and Continue button on the Check and submit the report page")
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

    Scenario(
      "3. PSA User Can Submit IHTP Application, Individual and Yes NI, No for Payment notice submission, An individual for beneficiaries to add"
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
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does DeceasedFirstName DeceasedSurnameName have a National Insurance number?")
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
      LookUpPostcodePage.enterPostcode("ZZ1 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("4")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User is navigated to Did PRFirstName PRSurnameName submit the payment notice? Page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true

      And("User Clicks on Yes Radio button and click on continue button")
      SubmitPaymentNoticePage.clickRadioButton("No")

      Then("User is navigated to When did the scheme receive the payment notice? Page")
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true

      And("User should be able to enter date of receiving payment notice and continues to the next page")
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

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
        "BeneficiarySurnameName"
      )

      Then("User will be on Does Beneficiary have a National Insurance Number Page")
      BeneficiaryNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      BeneficiaryNationalInsuranceNumberPage.clickRadioButton("Yes")

      And("User will be on Add Beneficiary page and selects Yes for Do you need to add another beneficiary")
      BeneficiaryNationalInsuranceNumberPage.navigateTo(AddBeneficiaryPage.pageUrl)
      AddBeneficiaryPage.verifyPageDetails() shouldBe true
      AddBeneficiaryPage.clickRadioButton("Yes")

      Then("User will be on New page of Select Beneficiary type and selects An individual for another Beneficiary")
      NewSelectTypeOfBeneficiaryToAdd.verifyPageDetails() shouldBe true
      NewSelectTypeOfBeneficiaryToAdd.clickRadioButton("An individual")

      Then(
        "User is navigated to the Enter the full name of the beneficiary Page and able to enter Details of the Beneficiary"
      )
      EnterNameOfNewBeneficiaryPage.verifyPageDetails() shouldBe true
      EnterNameOfNewBeneficiaryPage.enterBeneficiaryDetails(
        "Dr",
        "BeneficiaryFirstName",
        "BeneficiaryMiddleName",
        "BeneficiarySurnameName"
      )

      Then("User will be on Does Beneficiary have a National Insurance Number Page")
      NewBeneficiaryNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      NewBeneficiaryNationalInsuranceNumberPage.clickRadioButton("Yes")

      And("User will be on Add Beneficiary page and selects No for Do you need to add another beneficiary")
      BeneficiaryNationalInsuranceNumberPage.navigateTo(AddBeneficiaryPage.pageUrl)
      AddBeneficiaryPage.verifyPageDetails() shouldBe true
      AddBeneficiaryPage.clickRadioButton("No")

      And("User will be on CYA page")
      AddBeneficiaryPage.navigateTo(CheckYourAnswersPage.pageUrl)
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

    Scenario(
      "4. PSA User Can Submit IHTP Application, Organisation, and Yes for Payment Notice submission, Yes for beneficiaries known, An organisation or trust for beneficiaries to add"
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
      And("User should be able to Navigate to Deceased Name Page")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)

      Then("User is navigated to the Deceased Name Page")
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased")
      DeceasedNamePage.enterDeceasedDetails(
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does DeceasedFirstName DeceasedSurnameName have a National Insurance number?")
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

      And("User enters Date of Birth and Date of Death then continues to the next page")
      EnterBirthDeathPage.enterBirthDate("01", "01", "1990")
      EnterBirthDeathPage.enterDeathDate("11", "12", "2025")

      Then(
        "User is navigated to the Is the personal representative (PR) an individual or a member of an organisation? Page"
      )
      PRTypePage.verifyPageDetails() shouldBe true

      And("User selects Organisation for PR Type and continues to next Page")
      PRTypePage.clickRadioButton("Organisation")

      Then("User is navigated to the Enter the name of the organisation Page")
      NameOfTheOrganisationPage.verifyPageDetails() shouldBe true
      NameOfTheOrganisationPage.verifyPageHeading() shouldBe true

      And("User is able to enter Organisation name and continues to next Page")
      NameOfTheOrganisationPage.enterOrganisationName("Kapil & Sons Ltd.")

      Then("User is navigated to the Enter Name of the PR Organisation name Page")
      OrganisationRepresentativeNamePage.verifyPageDetails() shouldBe true

      And("User should able to enter PR details and continues to next Page")
      OrganisationRepresentativeNamePage.enterOrgRepresentativeDetails(
        "Mr",
        "PRFirstName",
        "PRMiddleName",
        "PRSurnameName"
      )

      Then("User is navigated to Select the country or territory of Kapil & Sons Ltd.")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("United Kingdom")

      Then("User is navigated to Look Up Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterPostcode("ZZ1 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("4")

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

      And("User Clicks on An organisation or trust option and continues to the next page")
      SelectTypeOfBeneficiaryToAdd.clickRadioButton("An organisation or trust")

      Then("User will be on CYA page")
      CheckYourAnswersPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails() shouldBe true
      CheckYourAnswersPage.verifyPageHeading() shouldBe true

      Then("User should be able to click on Change Link Button")
      CheckYourAnswersPage.ClickChangeLink()

      And("When User Clicks on Change Link Button it will navigates to enter the Inheritance Tax reference number Page")
      CheckYourAnswersPage.navigateTo(EnterTheInheritanceTaxReferenceNumberPage.newUrl)
      EnterTheInheritanceTaxReferenceNumberPage.verifyNewUrl() shouldBe true

      When("User clicks on Save and continue it navigates to the Check and submit the report Page")
      EnterTheInheritanceTaxReferenceNumberPage.SaveAndContinueButton()
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(CheckYourAnswersPage.pageUrl)
      CheckYourAnswersPage.verifyPageDetails()

      // Verify if fields on the Check and Submit Page

      Then("User verifies if the details for Country of PR and Address of PR are correct")
      CheckYourAnswersPage.verifyCountryOfUnitedKingdom("United Kingdom")
      CheckYourAnswersPage.verifyAddressOfOrganisation("4 Other Place\nSome District\nAnytown\nZZ1 1ZZ")

      And("User should be able to click on Change Country of PR")
      CheckYourAnswersPage.clickChangeCountryOfOrganisationLink()

      Then("User is navigated to the Select Country Page")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("Spain")

      Then("User is navigated to Enter Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterAddressLine1("Spanish Address 1")
      LookUpPostcodePage.enterAddressLine2("Spanish Address 2")
      LookUpPostcodePage.enterAddressLine3("Spanish Address 3")
      LookUpPostcodePage.enterPostcode("TE5710")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User verifies if the changed details for Country of PR and Address of PR are correct")
      CheckYourAnswersPage.verifyCountryOfSpain("Spain")
      CheckYourAnswersPage.verifyAddressOfOrganisation("Spanish Address 1\nSpanish Address 2\nSpanish Address 3\nTE5710")

      And("User click on Save and Continue button on the Check and submit the report page ")
      CheckYourAnswersPage.SaveAndContinueButton()

      Then("User should be able to Navigates to Psa-Declaration Page")
      CheckYourAnswersPage.navigateTo(PSADeclarationPage.pageUrl)
      PSADeclarationPage.verifyPageDetails() shouldBe true
      PSADeclarationPage.verifyPageHeading() shouldBe true

      And("User should be click on Agree and Submit Button on Psa-Declaration Page")
      PSADeclarationPage.AgreeAndSubmitButton()

      Then("User should be able to Navigates to Submission Page")
      PSADeclarationPage.navigateTo(ReportSubmittedPage.pageUrl)
      ReportSubmittedPage.verifyPageDetails() shouldBe true
      ReportSubmittedPage.verifyPageHeading() shouldBe true

      And("the GOV.UK footer links should be present")
      ReportSubmittedPage.verifyFooterLinksArePresent() shouldBe true

      Then("the Sign out link should be displayed")
      AuthLoginPage.verifySignOutLinkText() shouldBe true
    }

    Scenario(
      "5. PSA User Can Submit IHTP Application, Organisation, and No for Payment Notice submission, An organisation or trust for beneficiaries to add"
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

      And("User is able to enter Tax reference number and continues to next Page")
      EnterTheInheritanceTaxReferenceNumberPage.enterReferenceNumber(" A123456/25A ")

      // Deceased name page
      And("User should be able to Navigate to Deceased Name Page")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)

      Then("User is navigated to the Deceased Name Page")
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased")
      DeceasedNamePage.enterDeceasedDetails(
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does DeceasedFirstName DeceasedSurnameName have a National Insurance number?")
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

      And("User enters Date of Birth and Date of Death then continues to next page")
      EnterBirthDeathPage.enterBirthDate("01", "01", "1990")
      EnterBirthDeathPage.enterDeathDate("11", "12", "2025")

      Then(
        "User is navigated to the Is the personal representative (PR) an individual or a member of an organisation? Page"
      )
      PRTypePage.verifyPageDetails() shouldBe true

      And("User selects Organisation for PR Type and continues to next Page")
      PRTypePage.clickRadioButton("Organisation")

      Then("User is navigated to the Enter the name of the organisation Page")
      NameOfTheOrganisationPage.verifyPageDetails() shouldBe true
      NameOfTheOrganisationPage.verifyPageHeading() shouldBe true

      And("User is able to enter Organisation name and continues to next Page")
      NameOfTheOrganisationPage.enterOrganisationName("Test & Orgs Ltd.")

      Then("User is navigated to the Enter Name of the PR Organisation name Page")
      OrganisationRepresentativeNamePage.verifyPageDetails() shouldBe true

      And("User should able to enter PR details and continues to next Page")
      OrganisationRepresentativeNamePage.enterOrgRepresentativeDetails(
        "Mr",
        "PRFirstName",
        "PRMiddleName",
        "PRSurnameName"
      )

      Then("User is navigated to Select the country or territory of Test & Orgs Ltd.")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("United Kingdom")

      Then("User is navigated to Look Up Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterPostcode("ZZ1 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("4")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User is navigated to Did PRFirstName PRSurnameName submit the payment notice? Page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true

      And("User Clicks on No Radio button and click on continue button")
      SubmitPaymentNoticePage.clickRadioButton("No")

      Then("User is navigated to When did the scheme receive the payment notice? Page")
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true

      And("User should be able to enter date of receiving payment notice and continues to the next page")
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

      Then("User is navigated to Select the type of beneficiary to add Page")
      SelectTypeOfBeneficiaryToAdd.navigateTo(SelectTypeOfBeneficiaryToAdd.pageUrl)
      SelectTypeOfBeneficiaryToAdd.verifyPageDetails() shouldBe true

      And("User Clicks on An organisation or trust option and clicks on Save and continue button")
      SelectTypeOfBeneficiaryToAdd.clickRadioButton("An organisation or trust")

      Then("User will be on CYA page")
      CheckYourAnswersPage.navigateTo(CheckYourAnswersPage.pageUrl)
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

      Then("User should be able to Navigates to Psa-Declaration Page")
      CheckYourAnswersPage.navigateTo(PSADeclarationPage.pageUrl)
      PSADeclarationPage.verifyPageDetails() shouldBe true
      PSADeclarationPage.verifyPageHeading() shouldBe true

      And("User should be click on Agree and Submit Button on Psa-Declaration Page")
      PSADeclarationPage.AgreeAndSubmitButton()

      Then("User should be able to Navigates to Submission Page")
      PSADeclarationPage.navigateTo(ReportSubmittedPage.pageUrl)
      ReportSubmittedPage.verifyPageDetails() shouldBe true
      ReportSubmittedPage.verifyPageHeading() shouldBe true

      And("the GOV.UK footer links should be present")
      ReportSubmittedPage.verifyFooterLinksArePresent() shouldBe true

      Then("the Sign out link should be displayed")
      AuthLoginPage.verifySignOutLinkText() shouldBe true
    }

    Scenario(
      "6. PSA User Can Submit IHTP Application, Organisation, and No for Payment Notice submission, An individual for beneficiaries to add"
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

      And("User is able to enter Tax reference number and continues to next Page")
      EnterTheInheritanceTaxReferenceNumberPage.enterReferenceNumber(" A123456/25A ")

      // Deceased name page
      And("User should be able to Navigate to Deceased Name Page")
      EnterTheInheritanceTaxReferenceNumberPage.navigateTo(DeceasedNamePage.pageUrl)

      Then("User is navigated to the Deceased Name Page")
      DeceasedNamePage.verifyPageDetails() shouldBe true
      DeceasedNamePage.verifyPageHeading() shouldBe true

      And("User is able to enter Details of the Deceased")
      DeceasedNamePage.enterDeceasedDetails(
        "Mr",
        "DeceasedFirstName",
        "DeceasedMiddleName",
        "DeceasedSurnameName"
      )

      Then("User is navigated to the National Insurance Number Page")
      DeceasedNamePage.navigateTo(NationalInsuranceNumberPage.pageUrl)
      NationalInsuranceNumberPage.verifyPageDetails() shouldBe true

      And("User selects No for Does DeceasedFirstName DeceasedSurnameName have a National Insurance number?")
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

      And("User enters Date of Birth and Date of Death then continues to next page")
      EnterBirthDeathPage.enterBirthDate("01", "01", "1990")
      EnterBirthDeathPage.enterDeathDate("11", "12", "2025")

      Then(
        "User is navigated to the Is the personal representative (PR) an individual or a member of an organisation? Page"
      )
      PRTypePage.verifyPageDetails() shouldBe true

      And("User selects Organisation for PR Type and continues to next Page")
      PRTypePage.clickRadioButton("Organisation")

      Then("User is navigated to the Enter the name of the organisation Page")
      NameOfTheOrganisationPage.verifyPageDetails() shouldBe true
      NameOfTheOrganisationPage.verifyPageHeading() shouldBe true

      And("User is able to enter Organisation name and continues to next Page")
      NameOfTheOrganisationPage.enterOrganisationName("Test & Orgs Ltd.")

      Then("User is navigated to the Enter Name of the PR Organisation name Page")
      OrganisationRepresentativeNamePage.verifyPageDetails() shouldBe true

      And("User should able to enter PR details and continues to next Page")
      OrganisationRepresentativeNamePage.enterOrgRepresentativeDetails(
        "Mr",
        "PRFirstName",
        "PRMiddleName",
        "PRSurnameName"
      )

      Then("User is navigated to Select the country or territory of Test & Orgs Ltd.")
      CountryPickerPage.verifyPage()
      CountryPickerPage.enterCountry("United Kingdom")

      Then("User is navigated to Look Up Address Page")
      LookUpPostcodePage.verifyPage()
      LookUpPostcodePage.enterPostcode("ZZ1 1ZZ")

      And("User is navigated to Choose Address Page")
      ChooseAddressPage.verifyPage()
      ChooseAddressPage.clickRadioButton("4")

      Then("User is navigated to Review and confirm Page")
      ReviewAndConfirmPage.verifyPageHeading() shouldBe true
      ReviewAndConfirmPage.verifyPage()
      ReviewAndConfirmPage.confirmAddressButton()

      Then("User is navigated to Did PRFirstName PRSurnameName submit the payment notice? Page")
      SubmitPaymentNoticePage.verifyPageDetails() shouldBe true

      And("User Clicks on No Radio button and click on continue button")
      SubmitPaymentNoticePage.clickRadioButton("No")

      Then("User is navigated to When did the scheme receive the payment notice? Page")
      SchemeReceivePaymentNoticePage.verifyPageDetails() shouldBe true

      And("User should be able to enter date of receiving payment notice and continues to the next page")
      SchemeReceivePaymentNoticePage.dateOfReceivingPaymentNotice("01", "01", "2026")

      Then("User is navigated to Select the type of beneficiary to add Page")
      SelectTypeOfBeneficiaryToAdd.navigateTo(SelectTypeOfBeneficiaryToAdd.pageUrl)
      SelectTypeOfBeneficiaryToAdd.verifyPageDetails() shouldBe true

      And("User Clicks on An organisation or trust option and clicks on Save and continue button")
      SelectTypeOfBeneficiaryToAdd.clickRadioButton("An individual")

      Then("User is navigated to the Enter the full name of the beneficiary Page")
      EnterNameOfBeneficiaryPage.navigateTo(EnterNameOfBeneficiaryPage.pageUrl)
      EnterNameOfBeneficiaryPage.verifyPageDetails() shouldBe true

      And("User is able to enter Details of the Beneficiary and continues to next Page")
      EnterNameOfBeneficiaryPage.enterBeneficiaryDetails(
        "Dr",
        "BeneficiaryFirstName",
        "BeneficiaryMiddleName",
        "BeneficiarySurnameName"
      )

      Then("User will be on Does Beneficiary have a National Insurance Number Page")
      BeneficiaryNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      BeneficiaryNationalInsuranceNumberPage.clickRadioButton("Yes")

      And("User will be on Add Beneficiary page and selects Yes for Do you need to add another beneficiary")
      BeneficiaryNationalInsuranceNumberPage.navigateTo(AddBeneficiaryPage.pageUrl)
      AddBeneficiaryPage.verifyPageDetails() shouldBe true
      AddBeneficiaryPage.clickRadioButton("Yes")

      Then("User will be on New page of Select Beneficiary type and selects An individual for another Beneficiary")
      NewSelectTypeOfBeneficiaryToAdd.verifyPageDetails() shouldBe true
      NewSelectTypeOfBeneficiaryToAdd.clickRadioButton("An individual")

      Then(
        "User is navigated to the Enter the full name of the beneficiary Page and able to enter Details of the Beneficiary"
      )
      EnterNameOfNewBeneficiaryPage.verifyPageDetails() shouldBe true
      EnterNameOfNewBeneficiaryPage.enterBeneficiaryDetails(
        "Dr",
        "BeneficiaryFirstName",
        "BeneficiaryMiddleName",
        "BeneficiarySurnameName"
      )

      Then("User will be on Does Beneficiary have a National Insurance Number Page")
      NewBeneficiaryNationalInsuranceNumberPage.verifyPageDetails() shouldBe true
      NewBeneficiaryNationalInsuranceNumberPage.clickRadioButton("Yes")

      And("User will be on Add Beneficiary page and selects remove Hyperlink for first Beneficiary to removed")
      AddBeneficiaryPage.selectHyperlink("Remove Hyperlink")

      RemoveBeneficiaryPage.remove(false)
      And("User will be on Add Beneficiary page and selects No for Do you need to add another beneficiary")
      AddBeneficiaryPage.verifyPageDetails() shouldBe true
      AddBeneficiaryPage.clickRadioButton("No")

      And("User will be on CYA page")
      CheckYourAnswersPage.navigateTo(CheckYourAnswersPage.pageUrl)
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

      Then("User should be able to Navigates to Psa-Declaration Page")
      CheckYourAnswersPage.navigateTo(PSADeclarationPage.pageUrl)
      PSADeclarationPage.verifyPageDetails() shouldBe true
      PSADeclarationPage.verifyPageHeading() shouldBe true

      And("User should be click on Agree and Submit Button on Psa-Declaration Page")
      PSADeclarationPage.AgreeAndSubmitButton()

      Then("User should be able to Navigates to Submission Page")
      PSADeclarationPage.navigateTo(ReportSubmittedPage.pageUrl)
      ReportSubmittedPage.verifyPageDetails() shouldBe true
      ReportSubmittedPage.verifyPageHeading() shouldBe true

      And("the GOV.UK footer links should be present")
      ReportSubmittedPage.verifyFooterLinksArePresent() shouldBe true

      Then("the Sign out link should be displayed")
      AuthLoginPage.verifySignOutLinkText() shouldBe true
    }

  }

}
