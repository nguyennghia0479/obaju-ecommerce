package cybersoft.javabackend.java18.obajuecommerce.app_stock.controller;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockUpdateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.service.StockService;
import cybersoft.javabackend.java18.obajuecommerce.common.model.ResponseDTO;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.DeleteMessageUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.OperationUtils;
import cybersoft.javabackend.java18.obajuecommerce.common.utils.ResponseUtils;
import cybersoft.javabackend.java18.obajuecommerce.role.model.Operation;
import cybersoft.javabackend.java18.obajuecommerce.security.authorization.SecurityOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/stocks")
    public ResponseEntity<ResponseDTO> findStocks() {
        return ResponseUtils.get(stockService.findAll(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/stocks/{id}")
    public ResponseEntity<ResponseDTO> findStockById(@PathVariable("id") UUID id) {
        return ResponseUtils.get(stockService.findById(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.CREATE_NEW, type = Operation.Type.SAVE_OR_UPDATE)
    @PostMapping("/admin/stocks")
    public ResponseEntity<ResponseDTO> createStock(@RequestBody @Valid StockCreateDTO stockCreateDTO) {
        return ResponseUtils.get(stockService.save(stockCreateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.UPDATE, type = Operation.Type.SAVE_OR_UPDATE)
    @PutMapping("/admin/stocks")
    public ResponseEntity<ResponseDTO> updateStock(@RequestBody @Valid StockUpdateDTO stockUpdateDTO) {
        return ResponseUtils.get(stockService.update(stockUpdateDTO), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @SecurityOperation(name = OperationUtils.DELETE_BY_ID, type = Operation.Type.REMOVE)
    @DeleteMapping("/admin/stocks/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable("id") UUID id) {
        stockService.deleteById(id);
        return ResponseUtils.get(DeleteMessageUtils.DELETE_STOCK_SUCCESS, HttpStatus.OK);
    }
}
