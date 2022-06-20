package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories

import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContract
import java.util.UUID

interface CRSContractRepository : CrudRepository<CRSContract, UUID> {
  fun findAll(sort: Sort): Iterable<CRSContract>
  fun findByReference(reference: String): CRSContract?
}
