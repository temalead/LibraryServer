package server.library.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import server.library.domain.Address;

@Getter
@Setter
@Accessors(chain = true)
public class CreateLibraryDto {
    private Address address;
}
