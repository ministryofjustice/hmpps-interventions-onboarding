package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding.models

import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "crs_contract_type_extra_desired_outcomes")
data class ExtraDesiredOutcome(
  @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

  @ManyToOne val serviceCategory: ServiceCategory? = null,
  @Column val description: String = "",

  @Column val createdAt: OffsetDateTime = OffsetDateTime.now(),
  @Column val updatedAt: OffsetDateTime? = OffsetDateTime.now(),
)
