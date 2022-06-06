package uk.gov.justice.digital.hmpps.hmppsinterventionsonboarding

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class HmppsInterventionsOnboardingTest {
  @Test
  fun contextLoads() {
  }
}
