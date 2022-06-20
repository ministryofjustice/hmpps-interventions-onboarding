package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories

import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.ServiceCategory
import java.util.UUID

interface ServiceCategoryRepository : CrudRepository<ServiceCategory, UUID> {
  fun findAll(sort: Sort): Iterable<ServiceCategory>
}
