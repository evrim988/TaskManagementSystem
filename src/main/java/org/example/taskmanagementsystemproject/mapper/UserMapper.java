package org.example.taskmanagementsystemproject.mapper;

import org.example.taskmanagementsystemproject.dto.request.user.RegisterRequestDto;
import org.example.taskmanagementsystemproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @Mapper: MapStruct'un bu arayüzü bir mapper olarak kullanmasını sağlar.
 * componentModel = "spring" ifadesi, bu mapper'ın bir Spring bileşeni olarak kullanılacağını belirtir.
 * unmappedTargetPolicy = ReportingPolicy.IGNORE: Haritalanmamış alanların yok sayılacağını belirtir;
 * bu, hedef nesnede tanımlanmamış alanlar varsa hata verilmemesini sağlar.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromRegisterRequestDto(RegisterRequestDto registerRequestDto);

}
