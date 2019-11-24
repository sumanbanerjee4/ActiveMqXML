package activeMq.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRepo extends CrudRepository<QModel,String>{
	
	public QModel findByProfileId(String profileId);

}
