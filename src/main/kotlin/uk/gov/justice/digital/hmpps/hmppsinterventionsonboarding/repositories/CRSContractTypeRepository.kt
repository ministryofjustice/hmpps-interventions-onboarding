package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories

import org.springframework.data.repository.CrudRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.CRSContractType
import java.util.UUID

interface CRSContractTypeRepository : CrudRepository<CRSContractType, UUID>
