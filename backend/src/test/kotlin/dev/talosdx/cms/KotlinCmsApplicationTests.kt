
package dev.talosdx.cms
import dev.talosdx.cms.model.service.AuthServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.images.PullPolicy
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class KotlinCmsApplicationTests {

    @Autowired
    lateinit var userDetailsService : UserDetailsService

    companion object {
        @Container
        private val psqlContainer = KPostgreSQLContainer(image = "postgres:14.2-alpine")
            .withImagePullPolicy(PullPolicy.alwaysPull())
            .withDatabaseName("cms")
            .apply { binds = emptyList() }

        @Suppress("SpellCheckingInspection")
        @Container
        private val mailHog = KGenericContainer(DockerImageName.parse("mailhog/mailhog"))
            .withExposedPorts(1025, 1025)
        @JvmStatic
        @DynamicPropertySource
        fun postgresqlProperties(registry: DynamicPropertyRegistry) {
            psqlContainer.start()
            registry.add("spring.datasource.url", psqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", psqlContainer::getPassword)
            registry.add("spring.datasource.username", psqlContainer::getUsername)
        }
        @JvmStatic
        @DynamicPropertySource
        fun mailHogProperties(registry: DynamicPropertyRegistry) {
            //wat?
            mailHog.start()
            registry.add("spring.mail.host", mailHog::getHost)
            registry.add("spring.mail.port", mailHog::getFirstMappedPort)
            registry.add("spring.mail.username") { "" }
            registry.add("spring.mail.password") { "" }
        }
    }
    @Test
    fun dbUp() {
        assertTrue(psqlContainer.isRunning)
    }
    @Test
    fun contextLoads() {}

    @Test
    fun userDetailsServiceIsCorrect() {
        Assertions.assertNotNull(userDetailsService)
        Assertions.assertInstanceOf(AuthServiceImpl::class.java, userDetailsService)
    }
}
internal class KPostgreSQLContainer(val image: String) : PostgreSQLContainer<KPostgreSQLContainer>(image)
internal class KGenericContainer(image: DockerImageName) : GenericContainer<KGenericContainer>(image)