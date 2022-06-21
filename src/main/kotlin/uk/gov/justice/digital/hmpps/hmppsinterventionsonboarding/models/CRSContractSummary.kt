package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models

data class CRSContractSummary(
  val serviceCategories: Collection<String> = listOf(),
) {
  constructor(contract: CRSContract) : this(
    serviceCategories = contract.type?.serviceCategories?.map { it.name }.orEmpty().sorted()
  )
}
