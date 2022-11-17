package cybersoft.javabackend.java18.obajuecommerce.app_stock.mapper;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockDTO stockToStockDTO(Stock stock);
}
