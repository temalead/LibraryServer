package server.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.library.domain.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findAddressByCityAndStreetAndNumberOfHouse(String c,String s, Integer n);
}
