package cybersoft.javabackend.java18.obajuecommerce.app_stock.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.service.StockService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<ResponseDTO> findStocks() {
        return ResponseUtils.get(stockService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/stocks")
    public ResponseEntity<ResponseDTO> createStock(@RequestBody @Valid StockCreateDTO stockCreateDTO) {
        return ResponseUtils.get(stockService.save(stockCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable("id") UUID id) {
        stockService.deleteById(id);
        return ResponseUtils.get("", HttpStatus.OK);
    }
}
