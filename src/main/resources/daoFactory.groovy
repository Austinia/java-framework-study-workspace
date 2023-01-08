import com.austinia.user.UserDao
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource

beans {
    dataSource(SimpleDriverDataSource) {
        driverClass = System.getenv("DB_CLASSNAME")
        url = System.getenv("DB_URL")
        username = System.getenv("DB_USERNAME")
        password = System.getenv("DB_PASSWORD")
    }

    jdbcTemplate(JdbcTemplate, dataSource) {

    }

    // 빈이름(타입, 생성자 의존성 빈)
    userDao(UserDao, jdbcTemplate) {

    }
}