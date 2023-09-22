package fc.server.palette.secondhand.repository;

import fc.server.palette.secondhand.entity.Secondhand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondhandRespository extends JpaRepository<Secondhand, Long> {
}
