package lcs.oms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lcs.oms.enums.ResponseStatus;
import lcs.oms.model.Order;
import lcs.oms.service.OrderService;
import lcs.oms.validators.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderValidator orderValidator;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = new ArrayList<>();
        try {
            errors = orderValidator.validateCreateOrder(order);
            if(errors.isEmpty()){
                return ResponseEntity.ok(new ObjectMapper().convertValue(orderService.createOrder(order), Map.class));
            }else{
                response.put("STATUS_MSG", ResponseStatus.FAILED);
                response.put("ERRORS", errors);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam("statusCode") int statusCode) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, statusCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}

