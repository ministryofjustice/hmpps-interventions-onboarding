package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models

import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "crs_contract_types")
data class CRSContractType(
  @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

  @Column val code: String = "",
  @Column val name: String = "",
  @OneToMany @JoinColumn(name = "crs_contract_type_id") val serviceCategories: Collection<ServiceCategory> = listOf(),

  @Column val createdAt: OffsetDateTime = OffsetDateTime.now(),
  @Column val updatedAt: OffsetDateTime? = OffsetDateTime.now(),
)
