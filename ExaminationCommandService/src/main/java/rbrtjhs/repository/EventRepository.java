package rbrtjhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rbrtjhs.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
