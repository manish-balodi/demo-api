package glaucus.api.service.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    public static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
