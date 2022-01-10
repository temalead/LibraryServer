package server.library.repository;

import server.library.domain.Genre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenreJpaConverter implements AttributeConverter<Genre,String> {
    @Override
    public String convertToDatabaseColumn(Genre attribute) {
        if (attribute!=null) {
            return attribute.toString();
        }
        else
            return null;
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return Genre.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
