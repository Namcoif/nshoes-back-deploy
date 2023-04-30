package com.ntn.controller;

import com.ntn.dto.OrderDTO;
import com.ntn.dto.QueryOrderDTO;
import com.ntn.entity.Order;
import com.ntn.service.IOrderService;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private JSONObject message = new JSONObject();

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getListOrders() {
        List<Order> orderList = orderService.getListOrders();
        List<OrderDTO> orderDTOList = modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {
        }.getType());
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOList);
    }

    @GetMapping(value = "/paging")
    public ResponseEntity<?> getListOrdersPaging(Pageable pageable, @PathParam("query") QueryOrderDTO queryOrderDTO) {
        try {
            Page<Order> orderPage = orderService.getListOrdersPaging(pageable, queryOrderDTO);
            List<OrderDTO> orderDTOList = modelMapper.map(orderPage.getContent(), new TypeToken<List<OrderDTO>>() {
            }.getType());

            Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderPage.getTotalElements());

            return ResponseEntity.ok(orderDTOPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @GetMapping(value = "/paging/manager")
    public ResponseEntity<?> getListOrdersPagingManager(Pageable pageable) {
        try {
            Page<Order> orderPage = orderService.getListOrdersPaging(pageable);
            List<OrderDTO> orderDTOList = modelMapper.map(orderPage.getContent(), new TypeToken<List<OrderDTO>>() {
            }.getType());

            Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderPage.getTotalElements());

            return ResponseEntity.ok(orderDTOPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            message = new JSONObject();
            message.put("textResult", "Order success!");

            Order order = modelMapper.map(orderDTO, Order.class);

            orderService.createOrder(order);

            return ResponseEntity.ok(message.toString());
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id, @PathParam("orderStatus") String orderStatus) {
        try {
            message = new JSONObject();
            message.put("textResult", "Update order successfully!");

            Order order = orderService.getOrderById(id);

            Order.OrderStatus orderStatus1 = Order.OrderStatus.toEnumOrderStatus(orderStatus);

            if (orderStatus1 == null) {
                order = order;
            } else if (orderStatus1 == Order.OrderStatus.DELIVERED) {
                order.setOrderStatus(orderStatus1);
                order.setReceivedDate(new Date());
            } else if (orderStatus1 == Order.OrderStatus.CANCELED) {
                message.remove("textResult");
                message.put("textResult", "Order cancel successfully!");

                order.setOrderStatus(orderStatus1);
            } else {
                order.setOrderStatus(orderStatus1);
            }

//            Order order = modelMapper.map(orderDTO, Order.class);

            orderService.updateOrder(order);

            return ResponseEntity.ok(message.toString());
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }
}
