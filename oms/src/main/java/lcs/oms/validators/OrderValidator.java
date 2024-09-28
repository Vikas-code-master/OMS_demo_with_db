package lcs.oms.validators;

import lcs.oms.model.Item;
import lcs.oms.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderValidator {

    public List<String> validateCreateOrder(Order order) {
        List<String> errors = new ArrayList<>();
        if(CollectionUtils.isEmpty(order.getItems())){
            errors.add("items should not be empty");

        } else{
            List<Item> items = order.getItems();
            for(Item entity : items){
                if(!StringUtils.hasText(entity.getName())){
                    errors.add("items name should not be empty");
                }
                if(entity.getPrice() <= 0){
                    errors.add("prices should be more than 0");
                }
            }

        }
        return errors;
    }

}
