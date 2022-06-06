package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.repositories

import org.springframework.data.repository.CrudRepository
import uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models.Provider
import java.util.UUID

interface ProviderRepository : CrudRepository<Provider, UUID>
