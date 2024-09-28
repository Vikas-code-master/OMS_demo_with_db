package lcs.oms.service;

import lcs.oms.enums.OrderStatus;
import lcs.oms.model.Order;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong orderIdGenerator = new AtomicLong(0);

    public Order createOrder(Order order) {
        Long id = orderIdGenerator.incrementAndGet();
        order.setId(id);
        order.setStatusCode(OrderStatus.CREATED.ordinal());
        orders.put(id, order);
        return order;
    }
    // Retrieve order by ID
    public Order getOrderById(Long id) {
        return orders.get(id);
    }

    // Update order status
    public synchronized Order updateOrderStatus(Long id, int statusCode) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatusCode(statusCode);
        }
        return order;
    }

    // Cancel order
    public synchronized void cancelOrder(Long id) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatusCode(OrderStatus.CANCELED.ordinal());
        }
    }
}

