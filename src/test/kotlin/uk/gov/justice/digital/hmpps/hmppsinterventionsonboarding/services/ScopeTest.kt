package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.services

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Complexity
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories.ServiceCategoryRepository

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ScopeTest @Autowired constructor(private val repository: ServiceCategoryRepository) {
  val scope: Scope = Scope(repository)

  @Test
  fun `allServiceCategories() returns all service categories in name order`() {
    val result = scope.allServiceCategories()
    Assertions.assertThat(result).isNotEmpty
    Assertions.assertThat(result.map { it.name }).isSorted
  }

  @Test
  fun `allServiceCategories() loads related complexity levels`() {
    val accommodation = scope.allServiceCategories().find { it.name == "Accommodation" }

    Assertions.assertThat(accommodation?.complexityLevels).isNotEmpty
    Assertions.assertThat(accommodation?.complexityLevels?.map { it.complexity }).isSorted.containsExactly(
      Complexity.LOW,
      Complexity.MEDIUM,
      Complexity.HIGH,
    )
  }

  @Test
  fun `allServiceCategories() loads related desired outcomes`() {
    val accommodation = scope.allServiceCategories().find { it.name == "Accommodation" }

    Assertions.assertThat(accommodation?.desiredOutcomes).isNotEmpty
    Assertions.assertThat(accommodation?.desiredOutcomes?.map { it.description }).containsExactlyInAnyOrder(
      "All barriers as identified in the Service user action plan, for example financial, behavioural, physical, mental or offence-type related), to obtaining or sustaining accommodation, are successfully removed.",
      "Service user makes progress in obtaining accommodation.",
      "Service user is helped to secure social or supported housing.",
      "Service user is helped to secure a tenancy in the private rented sector (PRS).",
      "Service user is helped to sustain existing accommodation.",
      "Service user is prevented from becoming homeless.",
      "Service user at risk of losing their tenancy are successfully helped to retain it.",
      "Intervention ends before the Service user has sustained accommodation for three months, but s/he has a strong prospect of sustaining it for at least three months (including for those serving custodial sentences of less than 6 months).",
    )
  }
}
