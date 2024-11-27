package ar.edu.utn.frba.dds.grupo05.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class OptionalByteArrayConverter implements AttributeConverter<Optional<byte[]>, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(Optional<byte[]> attribute) {
        return attribute.orElse(null);
    }

    @Override
    public Optional<byte[]> convertToEntityAttribute(byte[] dbData) {
        return Optional.ofNullable(dbData);
    }
}
