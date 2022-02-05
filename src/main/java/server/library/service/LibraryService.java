package server.library.service;

import server.library.domain.dto.CreateLibraryDto;

public interface LibraryService {
    CreateLibraryDto addLibrary(CreateLibraryDto libraryDto);
}
