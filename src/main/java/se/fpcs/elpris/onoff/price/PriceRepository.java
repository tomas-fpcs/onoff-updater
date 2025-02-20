package se.fpcs.elpris.onoff.price;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PriceRepository extends MongoRepository<PriceForHour, String> {

}
