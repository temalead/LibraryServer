package server.library.domain.dto;

import lombok.Getter;
import lombok.Setter;
import server.library.domain.Address;

@Getter
@Setter
public class CreateLibraryDto {
    private Address address;
}
