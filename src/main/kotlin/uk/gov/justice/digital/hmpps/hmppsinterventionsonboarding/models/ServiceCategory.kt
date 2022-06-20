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
import javax.persistence.OrderBy
import javax.persistence.Table

@Entity
@Table(name = "service_categories")
data class ServiceCategory(
  @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

  @Column val name: String = "",
  @OneToMany @JoinColumn(name = "service_category_id") val desiredOutcomes: Collection<DesiredOutcome> = listOf(),
  @OneToMany @JoinColumn(name = "service_category_id") @OrderBy("complexity") val complexityLevels: Collection<ComplexityLevel> = listOf(),

  @Column val createdAt: OffsetDateTime = OffsetDateTime.now(),
  @Column val updatedAt: OffsetDateTime? = OffsetDateTime.now(),
)
